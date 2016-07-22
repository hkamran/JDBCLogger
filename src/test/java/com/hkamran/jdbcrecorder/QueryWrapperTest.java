package com.hkamran.jdbcrecorder;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.Assert;

public class QueryWrapperTest {

	
	@Test
	public void setInt_Test() {
		QueryWrapper query = new QueryWrapper("?");
		query.setInt(1, 5);
		
		Assert.assertEquals("Testing if Int is set", "5", query.toString());
	}
	
	@Test
	public void setString_Test() {
		QueryWrapper query = new QueryWrapper("?");
		query.setString(1, "str");
		
		Assert.assertEquals("Testing if string is set", "'STR'", query.toString());
	}
	
	@Test
	public void setBoolean_Test() {
		QueryWrapper query = new QueryWrapper("?");
		query.setBoolean(1, true);
		
		Assert.assertEquals("Testing if boolean is set", "TRUE", query.toString());
	}
	
	@Test
	public void setByte_Test() {
		QueryWrapper query = new QueryWrapper("?");
		query.setByte(1, new Byte("1"));
		
		Assert.assertEquals("Testing if byte is set", "1", query.toString());
	}
	
	@Test
	public void setShort_Test() {
		QueryWrapper query = new QueryWrapper("?");
		query.setShort(1, new Short("1"));
		
		Assert.assertEquals("Testing if short is set", "1", query.toString());
	}
	
	@Test
	public void setBigDecimal_Test() {
		QueryWrapper query = new QueryWrapper("?");
		query.setBigDecimal(1, new BigDecimal("1"));
		
		Assert.assertEquals("Testing if bigdecimal is set", "1", query.toString());
	}
	
	public static void main(String[] args) {
		QueryWrapperTest query = new QueryWrapperTest();
		
	}
	
	
	
}
