package com.hkamran.jdbclogger.instrument;

import java.lang.instrument.Instrumentation;

import org.apache.log4j.Logger;

import com.hkamran.jdbclogger.instrument.transformers.DriverManagerTransformer;

/**
 * This class represents the agent.
 * 
 * @author Hooman Kamran
 */
public class Agent {
	
	private final static Logger log = Logger.getLogger(Agent.class);
	
	public static void premain(String args, Instrumentation inst) {
		Agent.starting();
		inst.addTransformer(new DriverManagerTransformer());

	}
	
	public static void starting() {
		log.info("JDBCLogger is starting up!");
	}
	
	public static void running() {
		log.info("JDBCLogger is now running!");
	}

	
}
