package com.yahoo.ycem.tests.helpcentral;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.yahoo.ycem.tests.common.BaseTest;
import com.yahoo.ycem.tests.pages.helpcentral.HelpCentralPage;
import com.yahoo.ycem.tests.pages.helpcentral.HelpCentralMasterPage;
//import com.yahoo.ycem.tests.components.UniversalHeader;
//import com.yahoo.ycem.tests.util.ActionUtil;
import java.util.*;
import java.lang.*;

public class HelpCentralTest extends BaseTest implements Cloneable {
	
	
	/*@Test(enabled = true)
    public void helpcentralTitleTest() throws InterruptedException{
		
		// this will list the current system properties
		   Properties p = System.getProperties();
		   System.out.println("The fucking walmart I will destroy" + " " + testurl);
		   p.list(System.out);
		
		
	}
	*/
	
	
  @Test(enabled = true)
    public void helpcentralTitleTest() throws InterruptedException {
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		
		Thread.sleep(5000);
		Assert.assertNotNull(hcp.getUH().helpCentralTitle());
		System.out.println("The fucking walmart I will destroy" + " " + testurl);
		String p = System.getProperty("basedir");
		System.out.println("safi, please pring" + " " + " " + p);
		
		//try to externalize hard coded string below
		//Assert.assertEquals("Yahoo! Help", hcp.getpageTitle());
	}

	/*@Test(enabled = true)
    public void helpcentralLogoTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isYahooLogoDisplayed());
		//try to externalize hard coded string below
		//Assert.assertEquals("Yahoo! Help", hcp.getUH().getPageTitleText());
	}
	
	
	@Test(enabled = false)
    public void helpcentralHeaderHomeTest(){
		
		HelpCentralPage hcp = new HelpCentralPage (driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isHomeHeaderDisplayed());
		HelpCentralMasterPage master_en_US = new HelpCentralMasterPage("en_US");
		Assert.assertEquals(master_en_US.getUHAttriubutes().getheaderHome(), hcp.getUH(). getheaderHome());

	}
	
	@Test(enabled = false)
    public void helpcentralHeaderMailTest(){
		
		HelpCentralPage hcp = new HelpCentralPage (driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isMailHeaderDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Mail", hcp.getUH().getheaderMail());
		
	}
		
	@Test(enabled = false)
    public void helpcentralHeaderNewsTest(){
			
		HelpCentralPage hcp = new HelpCentralPage (driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isNewsHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("News", hcp.getUH().getheaderNews());
	}	
	
	@Test(enabled = false)
    public void helpcentralHeaderSportsTest(){
			
		HelpCentralPage hcp = new HelpCentralPage (driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isSportsHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Sports", hcp.getUH().getheaderSports());
	}
	
	@Test(enabled = false)
    public void helpcentralHeaderFinanceTest(){
			
		HelpCentralPage hcp= new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isFinanceHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Finance", hcp.getUH().getheaderFinance());
	}
	
	@Test(enabled = false)
    public void helpcentralHeaderWeatherTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isWeatherHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Weather", hcp.getUH().getheaderWeather());
	}

	@Test(enabled = false)
    public void helpcentralHeaderGamesTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isGamesHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Games", hcp.getUH().getheaderGames());
	}
   
	@Test(enabled = false)
    public void helpcentralHeaderGroupsTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isGroupsHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Groups", hcp.getUH().getheaderGroups());
	}
	
	@Test( enabled = false)
    public void helpcentralHeaderAnswersTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isAnswersHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Answers", hcp.getUH().getheaderAnswers());
	}
	
	@Test(enabled = false)
    public void helpcentralHeaderFlickrTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isFlickrHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Flickr", hcp.getUH().getheaderFlickr());
	}
    
	@Test(enabled = false)
    public void helpcentralHeaderMoreTest(){
			
		HelpCentralPage hcp= new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isMoreHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("More", hcp.getUH().getheaderMore());
	}
	
	@Test(enabled = true)
    public void helpcentralSearchFieldTest() throws InterruptedException{
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		
		Assert.assertTrue(hcp.getUH().isSearchFieldHeaderDisplayed());
		hcp.getUH().search("Changing Password");
		
	}
	
	@Test(enabled = false)
    public void helpcentralSearchHelpButtonTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isSearchHelpButtonHeaderDisplayed());
		
	}
	
	@Test(enabled = false)
    public void helpcentralWebButtonTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isSearchWebButtonHeaderDisplayed());
		
	}
	
	@Test(enabled = false)
    public void helpcentralHeaderSigninTest(){
			
		HelpCentralPage hcp= new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isSigninHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Sign In", hcp.getUH().getheaderSignin());
	}
	
	@Test(enabled = false)
    public void helpcentralHeaderSigninMailTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isSigninMailHeaderDisplayed());
			//try to externalize hard coded string below
		Assert.assertEquals("Mail", hcp.getUH().getheaderSigninMail());
	}
		
	@Test(enabled = false)
    public void helpcentralUniversalLogoTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isUniversalHeaderLogoDisplayed());
			//try to externalize hard coded string below
		//Assert.assertEquals("How can we help you today",  hcp.getUH().getheaderUniversalLogo());
	}
	
	@Test(enabled = false)
    public void helpcentralYahooCustomerCareNumberTest(){
			
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isYahooCustomerCareNumberDisplayed());
			//try to externalize hard coded string below
	//	Assert.assertEquals(" How can we help you today? Answering your questions and providing support when you need it Need Help Logging In?", uh.getheaderUniversalLogo());
	}
	
	@Test(enabled = false)
	public void helpcentralHelpByTopicTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isHelpByTopicDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Help by Topic |", hcp.getHelpByTopicText());
	}
	
	
	
	@Test(enabled = false)
	public void cantAccessAccountTopicTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isCantAccessAccountDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Can't access account", hcp.getCantAccessAccountText());
	}
	
	@Test(enabled = false)
	public void suspiciousActivityAndSpamTopicTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isSuspiciousActivityAndSpamDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Suspicious activity and spam", hcp.getSuspiciousActivityAndSpamText());
	}
	
	@Test(enabled = false)
	public void organizeAndRestoreMessagesTopicTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isOrganizeAndRestoreMessagesDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Organize and restore messages", hcp.getOrganizeAndRestoreMessagesText());
	}
	@Test(enabled = false)
   public void DownLoandAndInstallTopicTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isDownloadAndInstallDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Download and install", hcp.getDownloandAndInstallText());
	}
	
	@Test(enabled = false)
	   public void BillingTopicTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isBillingDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Billing", hcp.getBillingText());
		}
	
	@Test(enabled = false)
	   public void SecurityAndSafetyTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isSecurityAndSafetyDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Security and safety", hcp.getSecurityAndSafetyText());
		}
	
	@Test(enabled = false)
	   public void SendingAndReceivingEmailChatTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isSendingAndReceivingEmailChatDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Sending and receiving email/chat", hcp.getSendingAndReceivingEmailChatText());
		}
	
	@Test(enabled = false)
	   public void CommentAndSharingTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isCommentsAndSharingDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Comments and sharing", hcp.getCommentsAndSharingText());
		}
	
	@Test(enabled = false)
	   public void ErrorAndAlertsTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isErrorAndAlertsDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Errors and alerts", hcp.getErrorAndAlertsText());
		}
	
	@Test(enabled = false)
	   public void FantasyteamManagementAndScoringTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isFantasyteamManagementAndScoringDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Fantasy team management and scoring", hcp.getFantasyteamManagementAndScoringText());
		}
	
	@Test(enabled = false)
	   public void PopAndImapTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isPopAndImapDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("POP and IMAP", hcp.getPopAndImapText());
		}
	
	@Test(enabled = false)
	   public void AttachmentsAndPhotosTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isAttachmentsAndPhotosDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Attachments and photos", hcp.getAttachmentsAndPhotosText());
		}
	
	@Test(enabled = false)
	   public void AccessibilityTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isSettingsAndPreferencesDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Settings and preferences", hcp.getSettingsAndPreferencesText());
		}
	
	@Test(enabled = false)
	   public void VideoTest(){
			
			HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
			Assert.assertTrue(hcp.isVideoDisplayed());
			//try to externalize hard coded string below
			Assert.assertEquals("Video", hcp.getVideoText());
		}
	
	@Test(enabled = false)
	public void helpcentralHelpByProductTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isHelpByProductDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Help by Product |", hcp.getHelpByProductText());
	}
	
	@Test(enabled = false)
	public void helpcentralMyAccountProductTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isMyAccountDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("My Account", hcp.getMyAccountText());
		
	}
	
	@Test(enabled = false)
	public void helpcentralYahooMailProductTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isYahooMailDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Yahoo Mail", hcp.getYahooMailText());
	}
	
	@Test(enabled = false)
	public void helpcentralMessengerProductTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isMessengerDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Messenger", hcp.getMessengerText());
	}
	
	@Test(enabled = false)
	public void helpcentralSportsProductTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isSportsDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Sports", hcp.getSportsText());
	}
	
	@Test(enabled = false)
	public void helpcentralSearchProductTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isSearchDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Search", hcp.getSearchText());
	}
	
	@Test(enabled = false)
	public void helpcentralMoreProductsTest() throws InterruptedException{
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));		//try to externalize hard coded string below
		Assert.assertEquals("More products", hcp.getMoreProductsText());
		hcp.more();
		
	}
	
	@Test(enabled = false)
	public void helpcentralChooseLanguageTest() throws InterruptedException{
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isChooseLanguageDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Choose Language", hcp.getChooseLanguageText());
		hcp.language();
		
	}
	
	

	@Test(enabled = true)
	public void helpcentralBrokenLinksTest() throws InterruptedException{	
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
	   hcp.checklinks();
		
	}*/
	
	
	
	


	/*
	
	@Test
	public void helpcentralCarouselTitleTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isHelpByTopicDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Help by Topic |", hcp.getHelpByTopicText());
	}
	
	@Test
	public void helpcentralCarouselTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.isFirstTopicDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Can't access account", hcp.getFirstTopicText());
	}
	
	@Test
	public void helpcentralUHHomeLinkTest(){
		
		HelpCentralPage hcp = new HelpCentralPage(driverThread.get(), System.getProperty("TEST_URL"));
		Assert.assertTrue(hcp.getUH().isHomeLinkDisplayed());
		//try to externalize hard coded string below
		Assert.assertEquals("Home", hcp.getUH().getHomeLinkText());
		
		Assert.assertTrue(hcp.getUH().isTopBarDisplayed());
		
		// System.getProperty("buildDirectory") --> till target
		ActionUtil.captureScreenShot(driverThread.get());
	}
	*/
}
