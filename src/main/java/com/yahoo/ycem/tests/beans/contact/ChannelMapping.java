package com.yahoo.ycem.tests.beans.contact;

import java.io.Serializable;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

public class ChannelMapping implements Serializable, JSONAware {

	private static final long serialVersionUID = 3400216130464219640L;
	private boolean udbCheckRequired;
	private boolean deflectionRequired;
	private String deflectionDocId;
	private String unsupportedDocId;
	private boolean rntChat;
	private boolean lpChat;
	private boolean email;
	private boolean voice;
	private boolean community;
	private boolean logonRequired;
	private String[] dynamicFields;
	private String localMappingId;
	private YChannelMappingTO channels;
	private String deflectionContentID;
	private String localeCode;
	private String l1CategoryID;
	private String prodRefKey;

	public void setUdbCheckRequired(boolean udbCheckRequired) {
		this.udbCheckRequired = udbCheckRequired;
	}
	public boolean isUdbCheckRequired() {
		return udbCheckRequired;
	}
	public void setDeflectionRequired(boolean deflectionRequired) {
		this.deflectionRequired = deflectionRequired;
	}
	public boolean isDeflectionRequired() {
		return deflectionRequired;
	}
	public void setDeflectionDocId(String deflectionDocId) {
		this.deflectionDocId = deflectionDocId;
	}
	public String getDeflectionDocId() {
		return deflectionDocId;
	}
	public void setUnsupportedDocId(String unsupportedDocId) {
		this.unsupportedDocId = unsupportedDocId;
	}
	public String getUnsupportedDocId() {
		return unsupportedDocId;
	}
	public void setLogonRequired(boolean logonRequired) {
		this.logonRequired = logonRequired;
	}
	public boolean isLogonRequired() {
		return logonRequired;
	}
	public void setDynamicFields(String[] dynamicFields) {
		this.dynamicFields = dynamicFields;
	}
	public String[] getDynamicFields() {
		return dynamicFields;
	}
	public void setRntChat(boolean rntChat) {
		this.rntChat = rntChat;
	}
	public boolean hasRntChat() {
		return rntChat;
	}
	public void setLpChat(boolean lpChat) {
		this.lpChat = lpChat;
	}
	public boolean hasLpChat() {
		return lpChat;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public boolean hasEmail() {
		return email;
	}
	public void setVoice(boolean voice) {
		this.voice = voice;
	}
	public boolean hasVoice() {
		return voice;
	}
	public void setCommunity(boolean community) {
		this.community = community;
	}
	public boolean hasCommunity() {
		return community;
	}
	public String getLocalMappingId(){
		return localMappingId;
	}
	public void setLocalMappingId(String localMappingId){
		this.localMappingId = localMappingId;
	}
	public YChannelMappingTO getChannels(){
		return channels;
	}
	public void setChannels(YChannelMappingTO channels){
		this.channels= channels;
	}
	public String getDeflectionContentID(){
		return deflectionContentID;
	}
	public void setDeflectionContentID(String deflectionContentID){
		this.deflectionContentID = deflectionContentID;
	}
	/**
	 * @return the localeCode
	 */
	public String getLocaleCode() {
		return localeCode;
	}
	/**
	 * @param localeCode the localeCode to set
	 */
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	/**
	 * @return the l1CategoryID
	 */
	public String getL1CategoryID() {
		return l1CategoryID;
	}
	/**
	 * @param l1CategoryID the l1CategoryID to set
	 */
	public void setL1CategoryID(String l1CategoryID) {
		this.l1CategoryID = l1CategoryID;
	}
	/**
	 * @return the prodRefKey
	 */
	public String getProdRefKey() {
		return prodRefKey;
	}
	/**
	 * @param prodRefKey the prodRefKey to set
	 */
	public void setProdRefKey(String prodRefKey) {
		this.prodRefKey = prodRefKey;
	}
	@Override
	public String toString() {
		
		return toJSONString();
	}
	@Override
	public String toJSONString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        sb.append("\"" + JSONObject.escape("hasVoice") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasVoice())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasEmail") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasEmail())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasCommunity") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasCommunity())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasLpChat") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasLpChat())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("hasRightNowChat") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(hasRntChat())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("isLoginRequired") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(isLogonRequired())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("isUDBCheckRequired") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(isUdbCheckRequired())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("isDeflectionRequired") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(String.valueOf(isDeflectionRequired())) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("deflectionDocID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getDeflectionDocId()) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("deflectionContentID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getDeflectionContentID()) + "\"");
        
        sb.append(",");

        sb.append("\"" + JSONObject.escape("unsupportedDocID") + "\"");
        sb.append(":");
        sb.append("\"" + JSONObject.escape(getUnsupportedDocId()) + "\"");

        sb.append("}");
        
        return sb.toString();		
	}
	
}
