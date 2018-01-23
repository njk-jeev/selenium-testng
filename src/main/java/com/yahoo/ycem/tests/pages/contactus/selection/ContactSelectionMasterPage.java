package com.yahoo.ycem.tests.pages.contactus.selection;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yahoo.ycem.tests.beans.contact.Category;
import com.yahoo.ycem.tests.beans.contact.ChannelMapping;
import com.yahoo.ycem.tests.beans.contact.SubCategory;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;
import com.yahoo.ycem.tests.components.UniversalHeaderMaster;
import com.yahoo.ycem.tests.pages.BaseMasterPage;
import com.yahoo.ycem.tests.util.CacheManager;

public class ContactSelectionMasterPage extends BaseMasterPage implements
		ContactSelectionAttributes {

	private UniversalHeaderMaster uhMaster;
	
	private static final String pageDescTextKey = "contact.selection.page.desc";
	
	private static final String topicTextKey = "contact.selection.topic";
	
	private static final String subtopicTextKey = "contact.selection.subtopic";
	
	private static final String recommendedOptionsKey = "contact.selection.recommended.options";
	
	private String product;
	private String version;
	private ChannelMapping channelMapping;
	
	
	public ContactSelectionMasterPage(String locale) {
		super(locale);
		uhMaster = new UniversalHeaderMaster(locale);
	}

	@Override
	public UniversalHeaderAttributes getUHAttributes() {
		return uhMaster;
	}

	@Override
	public Map<String, Category> getTopics() {
		
		return CacheManager.getCategoriesFromProductLocaleVersion(locale, getProduct(), getVersion());
		
//		Map<String, Category> topics = new HashMap<String, Category>();
//		Category cat = null;
		
		//deflection
//		cat = new Category();
//		cat.setDescription("Sending and receiving messages");
//		cat.setRefKey("TOP_SENDRCV");
//		topics.put("TOP_SENDRCV", cat);
		
		//stopbox
//		cat = new Category();
//		cat.setDescription("Contacts");
//		cat.setRefKey("TOP_CONTACT");
//		topics.put("TOP_CONTACT", cat);
		
//		cat = new Category();
//		cat.setDescription("Password and sign in");
//		cat.setRefKey("TOP_PASSWD");
//		topics.put("TOP_PASSWD", cat);
		
//		cat = new Category();
//		cat.setDescription("Hacked accounts");
//		cat.setRefKey("TOP_HACKACCT");
//		topics.put("TOP_HACKACCT", cat);
//		return topics;
		
	}
	
	public void populateChannelMapping(String catRefKey, String subCatId){
		channelMapping = CacheManager.getMapping(locale, getProduct(), getVersion(), catRefKey, subCatId);
//		channelMapping = new ChannelMapping();
		
		//deflection (staging)
		//channelMapping.setDeflectionRequired(true);
		//channelMapping.setDeflectionContentID("585c79ac4a9946d3898cd96301d476a6");
		
		//stopbox (staging)
		//channelMapping.setDeflectionRequired(false);
		//channelMapping.setUnsupportedDocId("UNS39");
		
		//channel mapping (staging) - password & sign in
//		channelMapping.setDeflectionRequired(true);
//		channelMapping.setDeflectionContentID("c5c013df2089431fa167f20b4187d431");
//		channelMapping.setEmail(true);
//		channelMapping.setVoice(true);
		
		//channel mapping (staging) - Hacked account
//		channelMapping.setDeflectionRequired(false);
//		channelMapping.setEmail(true);
//		channelMapping.setVoice(true);
//		channelMapping.setRntChat(false);
//		channelMapping.setLpChat(true);
//		channelMapping.setCommunity(true);
	}
	
	@Override
	public String getPageDescText() {
		return getLocalizedText(pageDescTextKey);
	}

	@Override
	public String getTopicText() {
		return getLocalizedText(topicTextKey);
	}

	@Override
	public String getSubtopicText() {
		return getLocalizedText(subtopicTextKey);
	}

	@Override
	public String getRecommendedOptions() {
		return getLocalizedText(recommendedOptionsKey);
	}

	@Override
	public Map<String, SubCategory> getSubtopicsForTopic(String topicKey) {
		
		return CacheManager.getSubCategoriesFromProductLocaleVersionCategory(locale, getProduct(), getVersion(), topicKey);
//		Map<String, SubCategory> subTopics = new HashMap<String, SubCategory>();
//		SubCategory subTopic = null;
		
		//deflection
//		subTopic = new SubCategory();
//		subTopic.setSubCategoryId("E856038DBF8BCB0BE040C343862A043D");
//		subTopic.setDescription("Unable to receive messages");
//		subTopics.put("E856038DBF8BCB0BE040C343862A043D", subTopic);
		
		//stopbox
//		subTopic = new SubCategory();
//		subTopic.setSubCategoryId("E856038DBF99CB0BE040C343862A043D");
//		subTopic.setDescription("Restore lost contacts");
//		subTopics.put("E856038DBF99CB0BE040C343862A043D", subTopic);
		
		//channelmapping icons (staging) - Password and sign in
//		subTopic = new SubCategory();
//		subTopic.setSubCategoryId("E856038DBF74CB0BE040C343862A043D");
//		subTopic.setDescription("Change password");
//		subTopics.put("E856038DBF74CB0BE040C343862A043D", subTopic);
		
		//channelmapping icons (staging) - hacked accounts
//		subTopic = new SubCategory();
//		subTopic.setSubCategoryId("E856038DBDBCCB0BE040C343862A043D");
//		subTopic.setDescription("Don't have access to alternate email");
//		subTopics.put("E856038DBDBCCB0BE040C343862A043D", subTopic);
//		
//		return subTopics;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public boolean isQuickAnswersDisplayed() {
		
		boolean showQuickAnswers = false;
		if(!channelMapping.isDeflectionRequired() || channelMapping.getDeflectionContentID() == null){
			showQuickAnswers = true;	
		}	
		return showQuickAnswers;
	}
	
	public boolean hasNoChannels() {
		boolean hasNoChannels = false;
		if(!channelMapping.hasCommunity() && !channelMapping.hasEmail()&& !channelMapping.hasLpChat() && !channelMapping.hasRntChat() && !channelMapping.hasVoice()) {
			hasNoChannels = true;
		}
		return hasNoChannels;
	}
	
	public boolean hasChat() {
		
		boolean hasChat = false;
		if(channelMapping.hasLpChat() || channelMapping.hasRntChat()){
			hasChat = true;
		}
		return hasChat;
	}

	@Override
	public boolean isDeflectionArticleDisplayed() {
		return channelMapping.isDeflectionRequired() && channelMapping.getDeflectionContentID() != null;
	}

	@Override
	public boolean isStopboxDisplayed() {
		return hasNoChannels() && channelMapping.getUnsupportedDocId() != null;
	}

	@Override
	public boolean isCallUsChannelDisplayed() {
		return channelMapping.hasVoice();
	}

	@Override
	public boolean isVHTChannelDisplayed() {
		//FIXME: change to VHT
		return false;
	}

	@Override
	public boolean isEmailChannelDisplayed() {
		return channelMapping.hasEmail();
	}

	@Override
	public boolean isCommunityChannelDisplayed() {
		return channelMapping.hasCommunity();
	}

	@Override
	public boolean isChatChannelDisplayed() {
		return hasChat();
	}
	@Override
	public List<String> getArticleList() {
		List<String> articles = new ArrayList<String>();
		articles.add("SLN2340");
        articles.add("SLN3642");
        articles.add("SLN8951");
        articles.add("SLN2364");
        articles.add("SLN2300");
        articles.add("SLN2310");
		articles.add("SLN2361");
		return articles;
	}
}

