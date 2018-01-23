package com.yahoo.ycem.tests.contact;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yahoo.ycem.tests.beans.contact.Category;
import com.yahoo.ycem.tests.beans.contact.SubCategory;
import com.yahoo.ycem.tests.common.BaseContactTest;
import com.yahoo.ycem.tests.pages.contactus.selection.ContactSelectionMasterPage;
import com.yahoo.ycem.tests.pages.contactus.selection.ContactSelectionPage;

public class ContactSelectionTest extends BaseContactTest {

	@Test (dataProvider="selectionData", enabled=true, groups={"contactSelection"})
	public void allTopicsTest(String product, String version){
		
		driverThread.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		ContactSelectionPage actualPage = new ContactSelectionPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactSelectionMasterPage expectedPage = new ContactSelectionMasterPage(System.getProperty("LOCALE"));
		
		Map<String, Category> actualTopics = actualPage.getTopics();
		expectedPage.setProduct(product);
		expectedPage.setVersion(version);
		Map<String, Category> expectedTopics = expectedPage.getTopics();
		
		Category expTopic = null;
		Category actTopic = null;
		
		Assert.assertNotNull(actualTopics);
		
		Assert.assertEquals(expectedTopics.size(), actualTopics.size());
		
		for(String topicKey : expectedTopics.keySet()){
			
			expTopic = expectedTopics.get(topicKey);
			actTopic = actualTopics.get(topicKey);
			
			Assert.assertEquals(expTopic.getRefKey(), actTopic.getRefKey());
			Assert.assertEquals(expTopic.getDescription(), actTopic.getDescription());
		}
	}
	
	@DataProvider (name="selectionData")
	public Object[][] getTestData(){
		
		String product = "PROD_ACCT";
		String version = null;
		
		return new Object[][]{
				   new Object[] { product, version }
		};
	}
	
	@Test (enabled=false, groups={"contactSelection"})
	public void contactSelectionPageTextTest(){
		
		ContactSelectionPage csPage = new ContactSelectionPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactSelectionMasterPage csMasterPage = new ContactSelectionMasterPage(System.getProperty("LOCALE"));
		
		Assert.assertEquals(csMasterPage.getPageDescText(), csPage.getPageDescText());
		Assert.assertEquals(csMasterPage.getTopicText(), csPage.getTopicText());
		Assert.assertEquals(csMasterPage.getSubtopicText(), csPage.getSubtopicText());
		Assert.assertEquals(csMasterPage.getRecommendedOptions(), csPage.getRecommendedOptions());
		
	}
	
	@Test (dataProvider="selectionData", enabled=false, groups={"contactSelection"})
	public void subTopicsForTopicsTest(String product, String version){
		
		driverThread.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		ContactSelectionPage actualPage = new ContactSelectionPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactSelectionMasterPage expectedPage = new ContactSelectionMasterPage(System.getProperty("LOCALE"));
		
		expectedPage.setProduct(product);
		expectedPage.setVersion(version);
		Map<String, Category> expectedTopics = expectedPage.getTopics();
		
		SubCategory expSubtopic = null;
		SubCategory actSubtopic = null;
		Map<String, SubCategory> expectedSubtopics = null;
		Map<String, SubCategory> actualSubtopics = null;
		int index = -1;
		String zzConstant = "zzzz_";
		
		driverThread.get().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		
		for(String topicKey : expectedTopics.keySet()){
			
			expectedSubtopics = expectedPage.getSubtopicsForTopic(topicKey);
			actualSubtopics = actualPage.getSubtopicsForTopic(topicKey);
			
			for(String subtopicKey : expectedSubtopics.keySet()){
			
				expSubtopic = expectedSubtopics.get(subtopicKey);
				actSubtopic = actualSubtopics.get(subtopicKey);
				
				Assert.assertNotNull(actSubtopic);
				Assert.assertEquals(expSubtopic.getSubCategoryId(), actSubtopic.getSubCategoryId());
				if(expSubtopic.getDescription().startsWith(zzConstant)){
					
					index = expSubtopic.getDescription().indexOf(zzConstant);
					expSubtopic.setDescription(expSubtopic.getDescription().substring(index + zzConstant.length(), expSubtopic.getDescription().length()));
				}
				Assert.assertEquals(expSubtopic.getDescription(), actSubtopic.getDescription());
			}
		}
	}
	
	@Test (dataProvider="selectionData", enabled=true, groups={"contactSelection"})
	public void channelMappingForTopicSubTopicTest(String product, String version){
		
		driverThread.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		ContactSelectionPage actualPage = new ContactSelectionPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactSelectionMasterPage expectedPage = new ContactSelectionMasterPage(System.getProperty("LOCALE"));
		
		expectedPage.setProduct(product);
		expectedPage.setVersion(version);
		Map<String, Category> expectedTopics = expectedPage.getTopics();
		Map<String, SubCategory> expectedSubtopics = null;

		for(String topicKey : expectedTopics.keySet()){
			expectedSubtopics = expectedPage.getSubtopicsForTopic(topicKey);
			for(String subtopicKey : expectedSubtopics.keySet()){
				actualPage.selectCombination(topicKey, subtopicKey);
				expectedPage.populateChannelMapping(topicKey, subtopicKey);
				Assert.assertEquals(expectedPage.isQuickAnswersDisplayed(), actualPage.isQuickAnswersDisplayed());
				Assert.assertEquals(expectedPage.isDeflectionArticleDisplayed(), actualPage.isDeflectionArticleDisplayed());
				Assert.assertEquals(expectedPage.isStopboxDisplayed(), actualPage.isStopboxDisplayed());
				Assert.assertEquals(expectedPage.isCallUsChannelDisplayed(), actualPage.isCallUsChannelDisplayed());
				Assert.assertEquals(expectedPage.isVHTChannelDisplayed(), actualPage.isVHTChannelDisplayed());
				Assert.assertEquals(expectedPage.isEmailChannelDisplayed(), actualPage.isEmailChannelDisplayed());
				Assert.assertEquals(expectedPage.isChatChannelDisplayed(), actualPage.isChatChannelDisplayed());
				Assert.assertEquals(expectedPage.isCommunityChannelDisplayed(), actualPage.isCommunityChannelDisplayed());
				
				if(expectedPage.isCallUsChannelDisplayed()){
					Assert.assertTrue(actualPage.isCallUsOverlayDisplayed());
				}
				if(expectedPage.isCommunityChannelDisplayed()){
					Assert.assertTrue(actualPage.isCommunityOverlayDisplayed());
				}
			}
		}
	
	}
	
	@Test (enabled=false, groups={"contactSelection"})
	public void quickAnswersForTopicSubTopicTest(){
	
		driverThread.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		ContactSelectionPage actualPage = new ContactSelectionPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactSelectionMasterPage expectedPage = new ContactSelectionMasterPage(System.getProperty("LOCALE"));	

		Assert.assertEquals(actualPage.getArticleList().size(), expectedPage.getArticleList().size());
		List<String> actualArticles = actualPage.getArticleList();
		Iterator<String> iter = expectedPage.getArticleList().iterator();
		while(iter.hasNext())
		{
			Assert.assertTrue(actualArticles.contains(iter.next()));
		}

	}
}
