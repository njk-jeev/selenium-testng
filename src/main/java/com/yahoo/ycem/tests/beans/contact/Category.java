package com.yahoo.ycem.tests.beans.contact;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class Category implements Serializable, JSONAware {

	private static final long serialVersionUID = -2239936069572070549L;
	private String refKey;
	private String description;
	private boolean subCategories;
	private String starId;
	private List<SubCategory> subcatList;
	
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
	public void setSubCategories(boolean subCategories) {
		this.subCategories = subCategories;
	}
	public boolean hasSubCategories() {
		return subCategories;
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

        sb.append("\"" + JSONObject.escape("refKey") + "\"");
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
        
        sb.append("\"" + JSONObject.escape("hasSubCategories") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasSubCategories())) + "\"");
        
        sb.append(",");
        
        sb.append("\"" + JSONObject.escape("SubCategoriesList") + "\"");
        sb.append(":");
        sb.append(getSubcatList());

        sb.append("}");
        
        return sb.toString();		
	}
	public List<SubCategory> getSubcatList() {
		return subcatList;
	}
	public void setSubcatList(List<SubCategory> subcatList) {
		this.subcatList = subcatList;
	}
		
}
