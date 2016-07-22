package com.hkamran.jdbcrecorder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

	private List<String> queries;
	private Integer index = 0;
	private Matcher matcher;

	public enum MatchType {
		CONTENT, CONTAINS, CONTENT_OR_CONTAINS;
	}
	
	public static class Matcher {
	
		MatchType type = MatchType.CONTENT;
		String contains = "";
		Request request;
		
		private Matcher(MatchType type, Request request) {
			this.type = type;
			this.request = request;
		}
		
		public void setType(MatchType type) {
			this.type = type;
		}
		
		public void setType(MatchType type, String str) {
			this.type = type;
			if (type == MatchType.CONTAINS) {
				this.contains = str;
			} else if (type == MatchType.CONTENT_OR_CONTAINS) {
				this.contains = str;
			}
		}
		
		public Boolean isMatchingTo(Object obj) {
			if (!(obj instanceof Request)) {
				return false;
			}
			return isMatchingTo((Request) obj);
		}
		
		public Boolean isMatchingTo(Request other) {
			Boolean contentEqual = request.toString().equalsIgnoreCase(other.toString());
			Boolean regexEqual = other.toString().toLowerCase().contains(contains);
			
			if (type == MatchType.CONTENT) {
				return contentEqual;
			} else if (type == MatchType.CONTAINS) {
				return regexEqual;
			} 
			
			return contentEqual | regexEqual;
		}
		
	}
	
	public Request(String... queries) {
		this.queries = new ArrayList<String>();
		for (String query : queries) {
			this.queries.add(query);
		}
		this.matcher = new Matcher(MatchType.CONTENT, this);
	}
	
	public Request(List<QueryWrapper> queries) {
		this.queries = new ArrayList<String>();
		for (QueryWrapper query : queries) {
			this.queries.add(query.toString());
		}	
		this.matcher = new Matcher(MatchType.CONTENT, this);
	}
	
	public String toString() {
		StringBuilder content = new StringBuilder();
		for (String query : queries) {
			content.append(query.toString());
		}
		return content.toString();
	}
	
	public List<String> getQueries() {
		List<String> copy = new ArrayList<String>(queries);
		Collections.copy(copy, queries);
		return copy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		String content = toString();
		int charValue = 0;
		int counter = 1;
		for (char c : content.toCharArray()) {
			charValue += c * counter;
			counter++;
		}
		result = prime * result + charValue;
		return result;
	}
	
	public Integer getIndex() {
		Integer index = this.index;
		this.index++;
		return index;
	}
	
	public void setMatchType(MatchType type, String str) {
		matcher.setType(type, str);
	}
	
	public void setMatchType(MatchType type) {
		matcher.setType(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (queries == null) {
			if (other.queries != null)
				return false;
		} else if (!this.matcher.isMatchingTo(obj))
			return false;
		return true;
	}
	
	public JSONObject toJSON() {
		JSONObject jreq = new JSONObject();
		jreq.put("query", this.queries);
		jreq.put("matchtype", this.matcher.type);
		jreq.put("contains", this.matcher.contains);
		return jreq;
	}
	
	public static Request parseJSON(JSONObject json) {
		JSONArray jquery = json.getJSONArray("query");
		String[] queries = new String[jquery.length()];
		for (int i = 0; i < jquery.length(); i++) {
			queries[i] = jquery.getString(i);
		}
		
		MatchType matchType = MatchType.valueOf(json.getString("matchtype"));
		String contains = json.getString("contains");
		
		Request request = new Request(queries);
		request.matcher.type = matchType;
		request.matcher.contains = contains;
		return request;
	}
	
}
