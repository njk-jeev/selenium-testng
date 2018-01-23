package com.yahoo.ycem.tests.beans.contact;

import java.io.Serializable;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Product implements Serializable, JSONAware {

	private static final long serialVersionUID = -3984512164997035780L;
	private boolean communities;
	private boolean eol;
	private boolean versions;
	private String locale;
	private String refKey;
	private String description;
	private String starId;
	
	public boolean isEol() {
		return eol;
	}
	public void setEol(boolean eol) {
		this.eol = eol;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getRefKey() {
		return refKey;
	}
	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCommunities(boolean communities) {
		this.communities = communities;
	}
	public boolean hasCommunities() {
		return communities;
	}
	public void setVersions(boolean versions) {
		this.versions = versions;
	}
	public boolean hasVersions() {
		return versions;
	}
	public void setStarId(String starId) {
		this.starId = starId;
	}
	public String getStarId() {
		return starId;
	}
	@Override
	public String toString() {
		
		return toJSONString();
	}
	@Override
	public String toJSONString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        sb.append("\"" + JSONObject.escape("prodRefKey") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getRefKey()) + "\"");
        
        sb.append(",");
        
        sb.append("\"" + JSONObject.escape("description") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(StringEscapeUtils.escapeHtml(getDescription())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("locale") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getLocale()) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasVersions") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasVersions())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasCommunities") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasCommunities())) + "\"");

        sb.append("}");
        
        return sb.toString();		
	}	
	
	@Override
	public boolean equals(Object another){
		return this.getRefKey().equals(another);
	}
	
	@Override
	public int hashCode(){
		
		return this.getRefKey().hashCode();
	}

}
