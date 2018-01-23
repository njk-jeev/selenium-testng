package com.yahoo.ycem.tests.beans.contact;

import java.io.Serializable;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Version implements Serializable, JSONAware {

	private static final long serialVersionUID = -7068919724649928952L;
	private boolean communities;
	private boolean eol;
	private boolean categories;
	private String refKey;
	private String description;
	private Product product;
	private String starId;
	
	public boolean isEol() {
		return eol;
	}
	public void setEol(boolean eol) {
		this.eol = eol;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setCommunities(boolean communities) {
		this.communities = communities;
	}
	public boolean hasCommunities() {
		return communities;
	}
	public void setCategories(boolean categories) {
		this.categories = categories;
	}
	public boolean hasCategories() {
		return categories;
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

        sb.append("\"" + JSONObject.escape("versionRefKey") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getRefKey()) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("description") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(StringEscapeUtils.escapeHtml(getDescription())) + "\"");

        sb.append(",");

        sb.append("\"" + JSONObject.escape("starID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getStarId()) + "\"");

        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasCommunities") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasCommunities())) + "\"");

        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasCategories") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasCategories())) + "\"");

        sb.append("}");
        
        return sb.toString();		
	}
	
}
