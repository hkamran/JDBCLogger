package com.hkamran.jdbclogger.instrument;

import java.lang.instrument.Instrumentation;
import java.sql.Connection;

import com.hkamran.jdbclogger.instrument.transformers.DriverManagerTransformer;
import com.hkamran.jdbclogger.sql.wrappers.ConnectionWrapper;

public class Agent {

	public static void premain(String args, Instrumentation inst) {
		inst.addTransformer(new DriverManagerTransformer());

	}

	public static Connection call() {
		return new ConnectionWrapper(null);
	}
}
