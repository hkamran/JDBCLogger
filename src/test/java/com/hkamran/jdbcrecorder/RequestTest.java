package com.hkamran.jdbcrecorder;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.hkamran.jdbcrecorder.Request.MatchType;

public class RequestTest {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void equalsContent_Test() {
		Request request1 = new Request("test");
		Request request2 = new Request("test");

		Assert.assertEquals(request1.hashCode(), request2.hashCode());
	}
	
	@Test
	public void equalsRegex_Test() {
		Request request1 = new Request("test");
		request1.setMatchType(MatchType.CONTAINS, "test");
		Request request2 = new Request("test3333");

		Assert.assertTrue(request1.equals(request2));
	}
	
	@Test
	public void notEqualsRegex_Test() {
		Request request1 = new Request("test");
		request1.setMatchType(MatchType.CONTAINS, "test");
		Request request2 = new Request("tet3333");

		Assert.assertFalse(request1.equals(request2));
	}
	
	@Test
	public void notEqualsRegexAndContent_Test() {
		Request request1 = new Request("dasdasdas");
		request1.setMatchType(MatchType.CONTENT_OR_CONTAINS, "test");
		Request request2 = new Request("test3333");

		Assert.assertTrue(request1.equals(request2));
	}

	@Test
	public void hashcode_Test() {
		Request request = new Request("test");

		Assert.assertEquals(request.hashCode(), 1158);
	}

	@Test
	public void addQueriesException_Test() {
		Request request = new Request("test");

		request.getQueries().add("test");
		Assert.assertEquals("test", request.toString());
	}
	

}
