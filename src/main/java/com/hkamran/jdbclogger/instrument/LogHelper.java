package com.hkamran.jdbclogger.instrument;

import java.util.List;

import org.apache.log4j.Logger;

import com.hkamran.jdbclogger.sql.wrappers.QueryWrapper;

public class LogHelper {

	private static final int START_INDEX = 3;
	
	public static void execution(String start, List<QueryWrapper> queries, Logger log) {
		StringBuffer callStack = new StringBuffer();
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		callStack.append(System.lineSeparator());
		for (int i = elements.length - 1; i >= START_INDEX; i--) {
			StackTraceElement element = elements[i];
			
			String newLine = System.lineSeparator();
			if (i == START_INDEX) {
				newLine = "";
			}
			
			callStack.append("	at " + element.getClassName() + "." + element.getMethodName() + ":" + element.getLineNumber() + newLine);
		}
		
		log.info(start + System.lineSeparator() + "	" + queries.toString() + callStack.toString());
	}
	


}
