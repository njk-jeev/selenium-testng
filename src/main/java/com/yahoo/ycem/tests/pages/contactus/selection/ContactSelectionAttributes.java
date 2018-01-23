package com.yahoo.ycem.tests.pages.contactus.selection;

import java.util.List;
import java.util.Map;

import com.yahoo.ycem.tests.beans.contact.Category;
import com.yahoo.ycem.tests.beans.contact.SubCategory;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;

public interface ContactSelectionAttributes {
	
	UniversalHeaderAttributes getUHAttributes();

	Map<String,Category> getTopics();

	String getPageDescText();

	String getTopicText();
	
	String getSubtopicText();

	String getRecommendedOptions();

	Map<String, SubCategory> getSubtopicsForTopic(String topicKey);

	boolean isQuickAnswersDisplayed();

	boolean isDeflectionArticleDisplayed();

	boolean isStopboxDisplayed();

	boolean isCallUsChannelDisplayed();

	boolean isVHTChannelDisplayed();

	boolean isEmailChannelDisplayed();

	boolean isCommunityChannelDisplayed();

	boolean isChatChannelDisplayed();
	
	public List<String> getArticleList(); 
}

