package com.yahoo.ycem.tests.pages.contactus.selection;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yahoo.ycem.tests.beans.contact.Category;
import com.yahoo.ycem.tests.beans.contact.SubCategory;
import com.yahoo.ycem.tests.components.UniversalHeader;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;
import com.yahoo.ycem.tests.pages.BasePage;
import com.yahoo.ycem.tests.util.LinksChecker;

public class ContactSelectionPage extends BasePage implements ContactSelectionAttributes {

	private static final Logger LOGGER = LogManager.getLogger(ContactSelectionPage.class);
	
	private UniversalHeader uh;
	
	@FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class, ' '),' dsc ')]//p")
	private WebElement pageDescText;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ct1']//h3")
	private WebElement topicText;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ct2']//h3")
	private WebElement subtopicText;
	
	@FindBy(how = How.XPATH, using = "//div[@id='ct3']//h3")
	private WebElement recommendedOptions;
	
	@FindBy(how = How.XPATH, using = "//div[@id='productsandservices']//li[contains(concat(' ',@class, ' '),' yuimenuitem-hassubmenu ')]//a")
	private List<WebElement> topics;
	
	@FindBy(how = How.XPATH, using = "//div[@id='lrl']")
	private WebElement quickAnswersDiv;
	
	@FindBy(how = How.XPATH, using = "//div[@id='lra']")
	private WebElement deflectionArticleDiv;
	
	@FindBy(how = How.XPATH, using = "//div[@id='uns']")
	private WebElement stopboxArticleDiv;
	
	@FindBy(how = How.XPATH, using = "//div[@id='lrl']//li//a")
	private List<WebElement> quickAnswers;
	
	@FindBy(how = How.XPATH, using = "//li[@id='voice-icon']")
	private WebElement voiceIconLi;
	
	@FindBy(how = How.XPATH, using = "//li[@id='vht-icon']")
	private WebElement vhtIconLi;
	
	@FindBy(how = How.XPATH, using = "//li[@id='email-icon']")
	private WebElement emailIconLi;
	
	@FindBy(how = How.XPATH, using = "//li[@id='chat-icon']")
	private WebElement chatIconLi;
	
	@FindBy(how = How.XPATH, using = "//li[@id='community-icon']")
	private WebElement communityIconLi;
	
	@FindBy(how = How.XPATH, using = "//div[@id='cUscon']")
	private WebElement channelMappingOverlayDiv;
	
	
	public ContactSelectionPage(WebDriver driver, String url) {
		super(driver, url);
		//uh = new UniversalHeader(driver, url);
	}
	
     public UniversalHeader getUH() {
		
		return new UniversalHeader(driver, url);
	}
	

	@Override
	public UniversalHeaderAttributes getUHAttributes() {
		return getUH();
	}

	@Override
	public Map<String, Category> getTopics() {
		
		LOGGER.info("inside getTopics.");
		Category cat = null;
		Map<String, Category> actualCategories = new HashMap<String, Category>();
		for(WebElement topicAnchor : topics){
			if(topicAnchor.isDisplayed()){
				cat = new Category();
				cat.setDescription(topicAnchor.getText());
				String[] href = topicAnchor.getAttribute("href").split("#");
				if(href != null && href.length > 1){
					cat.setRefKey(href[1]);
					actualCategories.put(href[1], cat);
				}
			}
		}
		return actualCategories;
	}
	@Override
	public Map<String, SubCategory> getSubtopicsForTopic(String topicKey) {

		LOGGER.info("inside getSubtopicsForTopic. " + topicKey);
		SubCategory subcat = null;
		Map<String, SubCategory> actualSubtopics = new HashMap<String, SubCategory>();
		String[] href = null;
		List<WebElement> subTopicLis = null;
		for(WebElement topicAnchor : topics){
			if(topicAnchor.isDisplayed() && topicAnchor.getAttribute("href").endsWith(topicKey)){
				topicAnchor.click();
				href = topicAnchor.getAttribute("href").split("#");
				if(href != null && href.length > 1){
					subTopicLis = driver.findElements(By.xpath("//div[@id='" + href[1] + "']//li"));
					if(subTopicLis != null && !subTopicLis.isEmpty()){
						for(WebElement subTopicLi : subTopicLis){
							subcat = new SubCategory();
							subcat.setSubCategoryId(subTopicLi.getAttribute("id"));
							subcat.setDescription(subTopicLi.findElement(By.xpath(".//a")).getText());
							actualSubtopics.put(subTopicLi.getAttribute("id"), subcat);
					
						}
					}
				}
			}
		}
		
		return actualSubtopics;
	}

	public void selectCombination(String topicKey, String subCatId){
		
		LOGGER.info("inside selectCombination: " + " -> " + topicKey + " -> " + subCatId);
		
		String[] href = null;
		List<WebElement> subTopicLis = null;
		WebElement subTopicAnchor = null;
		for(WebElement topicAnchor : topics){
			if(topicAnchor.isDisplayed() && topicAnchor.getAttribute("href").endsWith(topicKey)){
				topicAnchor.click();
				href = topicAnchor.getAttribute("href").split("#");
				if(href != null && href.length > 1){
					subTopicLis = driver.findElements(By.xpath("//div[@id='" + href[1] + "']//li"));
					if(subTopicLis != null && !subTopicLis.isEmpty()){
						for(WebElement subTopicLi : subTopicLis){
							subTopicAnchor = subTopicLi.findElement(By.xpath(".//a"));
							if(subTopicAnchor.isDisplayed() && subTopicLi.getAttribute("id").equals(subCatId)){
								subTopicAnchor.click();
								try {
									Thread.sleep(10000); //have to wait for fetch quick answers request to complete.
								} catch (InterruptedException ex) {
									LOGGER.error("interrupted exception for the thread while waiting after subtopic click: " + ex);
									ex.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	public boolean isQuickAnswersDisplayed(){
		
		LOGGER.info("inside isQuickAnswersDisplayed: ");
		boolean quickAnswersDisplayed = false;
		if(quickAnswersDiv.isDisplayed()){
			List<WebElement> quickAnswersLis = quickAnswersDiv.findElements(By.xpath(".//ul[contains(concat(' ',@class, ' '),' q-ans ')]//li"));
			WebElement quickAnswerAnchor = null;
			boolean quickAnswerLinkResponse = false;
			if(quickAnswersLis != null && !quickAnswersLis.isEmpty()){
				quickAnswersDisplayed = true;
				for(WebElement quickAnswerLi : quickAnswersLis){
					quickAnswerAnchor = quickAnswerLi.findElement(By.xpath(".//a"));
					quickAnswerLinkResponse = LinksChecker.validateResponseCode(quickAnswerAnchor.getAttribute("href"), quickAnswerAnchor.getText());
					quickAnswersDisplayed = quickAnswersDisplayed && quickAnswerLinkResponse;
				}
			}
		}
		return quickAnswersDisplayed;
	}
	
	@Override
	public boolean isDeflectionArticleDisplayed(){
		
		LOGGER.info("inside isDeflectionArticleDisplayed: ");
		boolean deflectionDisplayed = false;
		if(deflectionArticleDiv.isDisplayed()){
			deflectionDisplayed = true;
			try{
				
				WebElement deflectionButton = deflectionArticleDiv.findElement(By.xpath(".//a"));
				if(deflectionButton != null && deflectionButton.isDisplayed()){
					deflectionDisplayed = deflectionDisplayed && LinksChecker.validateResponseCode(deflectionButton.getAttribute("href"), deflectionButton.getText());
				}
				
			}catch(NoSuchElementException nse){
				//do nothing
			}
			
		}
		return deflectionDisplayed;
	}
	
	@Override
	public boolean isStopboxDisplayed(){
		
		LOGGER.info("inside isStopboxDisplayed: ");
		boolean stopboxDisplayed = false;
		if(stopboxArticleDiv.isDisplayed()){
			stopboxDisplayed = true;
			WebElement stopboxButton = deflectionArticleDiv.findElement(By.xpath(".//a"));
			if(stopboxButton != null && stopboxButton.isDisplayed()){
				stopboxDisplayed = stopboxDisplayed && LinksChecker.validateResponseCode(stopboxButton.getAttribute("href"), stopboxButton.getText());
			}
		}
		return stopboxDisplayed;
	}
	
	@Override
	public boolean isCallUsChannelDisplayed(){
		
		LOGGER.info("inside isCallUsChannelDisplayed: ");
		return voiceIconLi.isDisplayed();
	}
	
	@Override
	public boolean isVHTChannelDisplayed(){
		LOGGER.info("inside isVHTChannelDisplayed: ");
		return vhtIconLi.isDisplayed();
	}
	
	@Override
	public boolean isEmailChannelDisplayed(){
		LOGGER.info("inside isEmailChannelDisplayed: ");
		return emailIconLi.isDisplayed();
	}
	
	@Override
	public boolean isCommunityChannelDisplayed(){
		LOGGER.info("inside isCommunityChannelDisplayed: ");
		return communityIconLi.isDisplayed();
	}
	
	@Override
	public boolean isChatChannelDisplayed(){
		LOGGER.info("inside isChatChannelDisplayed: ");
		return chatIconLi.isDisplayed();
	}
	
	@Override
	public String getPageDescText() {
		return pageDescText.getText();
	}

	@Override
	public String getTopicText() {
		return topicText.getText();
	}

	@Override
	public String getSubtopicText() {
		return subtopicText.getText();
	}

	@Override
	public String getRecommendedOptions() {
		return recommendedOptions.getText();
	}
	
	@Override
	public List<String> getArticleList() {
		List<String> articles = new ArrayList<String>();
		WebElement abuseCategoryElement = null;
		for(WebElement topicAnchor : topics){
			
			if(topicAnchor.isDisplayed()){
				
				String[] href = topicAnchor.getAttribute("href").split("#");
				if(href != null && href.length > 1){
					if("TOP_ABS".equals(href[1])) 
					{
						abuseCategoryElement = topicAnchor;
						abuseCategoryElement.click();
						
						List<WebElement> subTopicLis = driver.findElements(By.xpath("//div[@id='" + href[1] + "']//li"));
						
						if(subTopicLis != null && !subTopicLis.isEmpty()){
							
							for(WebElement subTopicLi : subTopicLis){
								WebElement subCatAnchor = subTopicLi.findElement(By.xpath(".//a"));
								if(subCatAnchor != null && ResourceBundle.getBundle("ycem/YCEMResources", 
										new Locale(System.getProperty("LOCALE"))).getString("contact.selection.artilelisttest.l3").equals(subCatAnchor.getText()))
								{
									subCatAnchor.click();
								    WebDriverWait wait = new WebDriverWait(driver,10);
								    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='lrl']")));											     
									List<WebElement> quickAnswers = driver.findElements(By.xpath("//div[@id='lrl']//li//a"));
									for(WebElement quickAnswerAnchor : quickAnswers){
										String quickAnswerHref;
										try {
											quickAnswerHref = URLDecoder.decode(quickAnswerAnchor.getAttribute("href"),"UTF-8");
										} catch (UnsupportedEncodingException e) {
											e.printStackTrace();
											quickAnswerHref = quickAnswerAnchor.getAttribute("href");
										}										
										if(quickAnswerHref != null)
										{
											int slnStart = quickAnswerHref.indexOf("&id=SLN");
											if(slnStart >= 0)
											{
												int slnEnd = quickAnswerHref.indexOf("&", slnStart + "&id=SLN".length());
											    if(slnEnd >= 0)
											    {
											    	String sln = quickAnswerHref.substring(slnStart + "&id=".length(), slnEnd);
											    	articles.add(sln);
											    	LOGGER.debug("sln = " +  sln); 
											    }
											}
										}
									}								
									break;
								}
							}
						}	
						break;
					}
				}
			}
		}		

		
		return articles;
	}

	public boolean isCallUsOverlayDisplayed() {
		
		boolean callUsOverlayDisplayed = false;
		WebElement callUsAnchor = voiceIconLi.findElement(By.xpath(".//a"));
		
		callUsAnchor.click();
		
		if(channelMappingOverlayDiv.isDisplayed()){
			callUsOverlayDisplayed = true;
		}
		//close the overlay
		channelMappingOverlayDiv.findElement(By.xpath(".//div[contains(concat(' ',@class, ' '),' yui3-widget-hd ')]//button")).click();
		
		return callUsOverlayDisplayed;
	}

	public boolean isCommunityOverlayDisplayed() {
		
		boolean communityOverlayDisplayed = false;
		WebElement communityAnchor = communityIconLi.findElement(By.xpath(".//a"));
		
		communityAnchor.click();
		
		if(channelMappingOverlayDiv.isDisplayed()){
			communityOverlayDisplayed = true;
			
			WebElement commButton = channelMappingOverlayDiv.findElement(By.xpath(".//a"));
			if(commButton != null && commButton.isDisplayed()){
				communityOverlayDisplayed = communityOverlayDisplayed && LinksChecker.validateResponseCode(commButton.getAttribute("href"), commButton.getText());
			}
		}
		//close the overlay
		channelMappingOverlayDiv.findElement(By.xpath(".//div[contains(concat(' ',@class, ' '),' yui3-widget-hd ')]//button")).click();
		
		return communityOverlayDisplayed;
	}
}
