package com.yahoo.ycem.tests.pages.helpcentral;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

import com.yahoo.ycem.tests.components.UniversalHeader;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;
import com.yahoo.ycem.tests.pages.BasePage;
import com.yahoo.ycem.tests.util.ActionUtil;

public class HelpCentralPage extends BasePage implements HelpCentralPageAttributes {
	
	

//	protected final String EE_DETAILS_GV = "SEE_DETAILS_GV";
	

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Yahoo! Help')]")
	private WebElement title;
	
	@FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class, ' '),' ttl ')]//h2")
	private WebElement helpByTopicText;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[1]")
	private WebElement carouselFirstLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[2]")
	private WebElement carouselSecondLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[3]")
	private WebElement carouselThirdLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[4]")
	private WebElement carouselFourthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[5]")
	private WebElement carouselFifthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[6]")
	private WebElement carouselSixthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[7]")
	private WebElement carouselSeventhLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[8]")
	private WebElement carouselEighthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[9]")
	private WebElement carouselNinethLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[10]")
	private WebElement carouselTenthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[11]")
	private WebElement carouselEleventhLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[12]")
	private WebElement carouselTwelvethLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[13]")
	private WebElement carouselThirteenthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[14]")
	private WebElement carouselFourteenthLi;
	
	@FindBy(how = How.XPATH, using = "//ol[@id='carousel']//li[15]")
	private WebElement carouselFifteenthLi;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div[4]/div/h2")
	private WebElement helpByProductText;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/ol/li/a/span")
	private WebElement myAccount;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/ol/li[2]/a/span")
	private WebElement yahooMail;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/ol/li[3]/a/span")
	private WebElement messenger;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/ol/li[4]/a/span")
	private WebElement sports;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/ol/li[5]/a/span")
	private WebElement search;
	
	@FindBy(how = How.XPATH, using = "//div[2]/div/ol/li[6]/a/span")
	private WebElement moreproducts;
	
	@FindBy(how = How.TAG_NAME, using = "//div[2]/p/span/a")
	private WebElement chooselanguage;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='top_passwd']")
	public WebElement brokenLinks;
	
	
	
	
	//private UniversalHeader uh;
	
	
	
	public HelpCentralPage(WebDriver driver, String url) {
		super(driver, url);
	//	uh = new UniversalHeader(driver, url);
	}
	
//	public UniversalHeader getUH(){
	//	return uh;
//	}
	
	public UniversalHeader getUH() {
		
		return new UniversalHeader(driver, url);
	}
	
	public boolean isPageTitleDisplayed(){
		return title.isDisplayed();
	}

	public String getpageTitle(){
		return title.getText();
	}
	
	
	public boolean isHelpByTopicDisplayed(){
		return helpByTopicText.isDisplayed();
	}

	public String getHelpByTopicText(){
		return helpByTopicText.getText();
	}
	
	public boolean isCantAccessAccountDisplayed(){
		return carouselFirstLi.isDisplayed();
	}

	public String getCantAccessAccountText(){
		return carouselFirstLi.getText();
	}
	
	public boolean isSuspiciousActivityAndSpamDisplayed(){
		return carouselSecondLi.isDisplayed();
	}

	public String getSuspiciousActivityAndSpamText(){
		return carouselSecondLi.getText();
	}
	
	public boolean isOrganizeAndRestoreMessagesDisplayed(){
		return carouselThirdLi.isDisplayed();
	}

	public String getOrganizeAndRestoreMessagesText(){
		return carouselThirdLi.getText();
	}
	
	public boolean isDownloadAndInstallDisplayed(){
		return carouselFourthLi.isDisplayed();
	}

	public String getDownloandAndInstallText(){
		return carouselFourthLi.getText();
	}
	
	public boolean isBillingDisplayed(){
		return carouselFifthLi.isDisplayed();
	}

	public String getBillingText(){
		return carouselFifthLi.getText();
	}
	
	public boolean isSecurityAndSafetyDisplayed(){
		return carouselSixthLi.isDisplayed();
	}

	public String getSecurityAndSafetyText(){
		return carouselSixthLi.getText();
	}
	
	public boolean isSendingAndReceivingEmailChatDisplayed(){
		return carouselSeventhLi.isDisplayed();
	}

	public String getSendingAndReceivingEmailChatText(){
		return carouselSeventhLi.getText();
	}
	
	public boolean isCommentsAndSharingDisplayed(){
		return carouselEighthLi.isDisplayed();
	}

	public String getCommentsAndSharingText(){
		return carouselEighthLi.getText();
	}
	
	public boolean isErrorAndAlertsDisplayed(){
		return carouselNinethLi.isDisplayed();
	}

	public String getErrorAndAlertsText(){
		return carouselNinethLi.getText();
	}
	
	public boolean isFantasyteamManagementAndScoringDisplayed(){
		return carouselTenthLi.isDisplayed();
	}

	public String getFantasyteamManagementAndScoringText(){
		return carouselTenthLi.getText();
	}
	
	public boolean isPopAndImapDisplayed(){
		return carouselEleventhLi.isDisplayed();
	}

	public String getPopAndImapText(){
		return carouselEleventhLi.getText();
	}
	
	public boolean isAttachmentsAndPhotosDisplayed(){
		return carouselTwelvethLi.isDisplayed();
	}

	public String getAttachmentsAndPhotosText(){
		return carouselTwelvethLi.getText();
	}
	
	public boolean isAccessibilityDisplayed(){
		return carouselThirteenthLi.isDisplayed();
	}

	public String getAccessibilityText(){
		return carouselThirteenthLi.getText();
	}
	
	public boolean isSettingsAndPreferencesDisplayed(){
		return carouselFourteenthLi.isDisplayed();
	}

	public String getSettingsAndPreferencesText(){
		return carouselFourteenthLi.getText();
	}
	
	public boolean isVideoDisplayed(){
		return carouselFifteenthLi.isDisplayed();
	}

	public String getVideoText(){
		return carouselFifteenthLi.getText();
	}
	
	public boolean isHelpByProductDisplayed(){
		return helpByProductText.isDisplayed();
	}

	public String getHelpByProductText(){
		return helpByProductText.getText();
	}
	
	public boolean isMyAccountDisplayed(){
		return myAccount.isDisplayed();
	}

	public String getMyAccountText(){
		return myAccount.getText();
	}
	
	public boolean isYahooMailDisplayed(){
		return yahooMail.isDisplayed();
	}

	public String getYahooMailText(){
		return yahooMail.getText();
	}
	
	public boolean isMessengerDisplayed(){
		return messenger.isDisplayed();
	
	}

	public String getMessengerText(){
		return messenger.getText();
	}
	
	public boolean isSportsDisplayed(){
		return sports.isDisplayed();
	}

	public String getSportsText(){
		return sports.getText();
	}
	
	public boolean isSearchDisplayed(){
		return search.isDisplayed();
	}

	public String getSearchText(){
		return search.getText();
	}
	
	public boolean isMoreProductsDisplayed(){
		return moreproducts.isDisplayed();
	}

	public String getMoreProductsText(){
		return moreproducts.getText();
	}
	
	public void more() throws InterruptedException {
		 moreproducts.click();
		 Thread.sleep(5000);
		  brokenLinks = driver.findElement(By.xpath("//div[@class='alp']"));
          List<WebElement> elements=brokenLinks.findElements(By.tagName("a"));
          for(int j = 0;j<elements.size();j++){    //create loop based upon number of links to traverse all links 
        	  brokenLinks= driver.findElement(By.xpath("//div[@class='alp']"));  // I'm re-creating "brokenLinks" webelement as DOM changes after driver navigate back.
        	  brokenLinks.getSize();
        	  brokenLinks.findElements(By.tagName("a")).get(j).getText();
              brokenLinks.findElements(By.tagName("a")).get(j).click();
              Thread.sleep(3000);
              driver.navigate().back();
              Thread.sleep(3000);
              moreproducts.click();
     		  Thread.sleep(5000);
          } 
	}
	
	public boolean isChooseLanguageDisplayed(){
		return chooselanguage.isDisplayed();
	}

	public String getChooseLanguageText(){
		return chooselanguage.getText();
	}
	
	
	public void language() throws InterruptedException {
		
	    chooselanguage.click();
	//	chooselanguage = driver.findElement(By.xpath("//div[@class='lpi']"));
		ActionUtil.waitAndClick(driver,(By.xpath("//div[@class='lpi']")));
		//ActionUtil.
		// Thread.sleep(5000);
		 brokenLinks = driver.findElement(By.xpath("//div[@class='lpi']"));
         List<WebElement> elements=brokenLinks.findElements(By.tagName("a"));
         for(int j = 0;j<elements.size();j++){    //create loop based upon number of links to traverse all links 
       	  brokenLinks= driver.findElement(By.xpath("//div[@class='lpi']"));  // I'm  re-creating "brokenLinks" webelement as DOM changes after driver navigate back.
       	  brokenLinks.getSize();
       	  brokenLinks.findElements(By.tagName("a")).get(j).getText();
             brokenLinks.findElements(By.tagName("a")).get(j).click();
             Thread.sleep(3000);
             driver.navigate().back();
             Thread.sleep(3000);
            
         } 
		 
	}
	

    public void checklinks() throws InterruptedException {
   
          brokenLinks = driver.findElement(By.xpath("//*[@id='top_passwd']"));
          List<WebElement> elements=brokenLinks.findElements(By.tagName("a"));
          for(int j = 0;j<elements.size();j++){    //create loop based upon number of links to traverse all links 
        	  brokenLinks= driver.findElement(By.xpath("//*[@id='top_passwd']"));   // I'm re-creating "brokenLinks" webelement as DOM changes after driver navigate back.
        	  brokenLinks.getSize();
        	  brokenLinks.findElements(By.tagName("a")).get(j).getText();
              brokenLinks.findElements(By.tagName("a")).get(j).click();
              Thread.sleep(3000);
              driver.navigate().back();
              Thread.sleep(3000);
              
        	  
        	  
          }  
        
    }

	@Override
	public UniversalHeaderAttributes getUHAttriubutes() {
	    return getUH();
	}




}
	
	
	
	  



