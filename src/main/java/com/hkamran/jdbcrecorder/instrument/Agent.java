package com.hkamran.jdbcrecorder.instrument;

import java.lang.instrument.Instrumentation;
import java.sql.Connection;

import com.hkamran.jdbcrecorder.instrument.transformers.DriverManagerTransformer;
import com.hkamran.jdbcrecorder.sql.wrappers.ConnectionWrapper;

public class Agent {

	public static void premain(String args, Instrumentation inst) {
		inst.addTransformer(new DriverManagerTransformer());

	}

	public static Connection call() {
		return new ConnectionWrapper(null);
	}
}
