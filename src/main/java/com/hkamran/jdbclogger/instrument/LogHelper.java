package com.hkamran.jdbclogger.instrument;

import java.util.List;

import org.apache.log4j.Logger;

import com.hkamran.jdbclogger.sql.wrappers.QueryWrapper;

public class LogHelper {

	private static final int START_INDEX = 4;
	
	public static void execution(String start, List<QueryWrapper> queries, Logger log) {
		boolean printCallStack = doPrintCallStack();
		StringBuffer callStack = new StringBuffer();
		if (printCallStack) callStack = getCallStack();
		log.info(start + System.lineSeparator() + "	" + queries.toString() + callStack.toString());
	}

	private static boolean doPrintCallStack() {
		return (boolean) System.getProperties().getOrDefault("printCallStack", true);
	}
	
	public static void info(String start, Logger log) {		
		boolean printCallStack = doPrintCallStack();
		StringBuffer callStack = new StringBuffer();
		if (printCallStack) callStack = getCallStack();	
		log.info(start + "	" + callStack.toString());
	}

	public static StringBuffer getCallStack() {
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
		return callStack;
	}

}
