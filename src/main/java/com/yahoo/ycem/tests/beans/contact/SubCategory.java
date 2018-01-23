package com.yahoo.ycem.tests.beans.contact;

import java.io.Serializable;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class SubCategory implements Serializable, JSONAware {

	private static final long serialVersionUID = -5363527933705905608L;
	private String subCategoryId;
	private String description;
	private Category parentCategory;
	private String starId;
	private String level1StarId;
	
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public void setStarId(String starId) {
		this.starId = starId;
	}
	public String getStarId() {
		return starId;
	}
	public void setLevel1StarId(String level1StarId) {
		this.level1StarId = level1StarId;
	}
	public String getLevel1StarId() {
		return level1StarId;
	}
//	@Override
//	public String toString() {
//		return "SubCategory { SubCategoryID = " + getSubCategoryId()
//		+ ", Description = " + getDescription()
//		+ ", StarID = " + getStarId()
//		+ ", Level1StarID = " + getLevel1StarId() + " }";
//	}
	
	@Override
	public String toString() {
        return toJSONString();		
	}

	
	@Override
	public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("\"" + JSONObject.escape("subCategoryID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getSubCategoryId()) + "\"");
        
        sb.append(",");
        sb.append("\"" + JSONObject.escape("description") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(StringEscapeUtils.escapeHtml(getDescription())) + "\"");
        
        sb.append(",");
        sb.append("\"" + JSONObject.escape("starID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getStarId()) + "\"");
        
        sb.append(",");
        sb.append("\"" + JSONObject.escape("level1StarID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getLevel1StarId()) + "\"");

        sb.append("}");

        return sb.toString();		
	}
	
	@Override
	public boolean equals(Object another){
		boolean result = false;
		
		if(another != null){
			result = this.getSubCategoryId().equals(((SubCategory)another).getSubCategoryId());
		}
		
		return result;
	}
	
	@Override
	public int hashCode(){
		
			return this.getSubCategoryId().hashCode();
		
	}

}
