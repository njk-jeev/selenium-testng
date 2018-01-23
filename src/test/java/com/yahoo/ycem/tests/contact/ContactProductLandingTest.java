package com.yahoo.ycem.tests.contact;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.yahoo.ycem.tests.beans.contact.Product;
import com.yahoo.ycem.tests.beans.contact.Version;
import com.yahoo.ycem.tests.common.BaseContactTest;
import com.yahoo.ycem.tests.pages.contactus.landing.ContactProductLandingMasterPage;
import com.yahoo.ycem.tests.pages.contactus.landing.ContactProductLandingPage;
import com.yahoo.ycem.tests.util.ActionUtil;

public class ContactProductLandingTest extends BaseContactTest {

	@Test (enabled=true, groups={"contactLanding"})
	public void selectProductTextTest(){
		
		ContactProductLandingPage cplpage = new ContactProductLandingPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactProductLandingMasterPage cplMasterPage = new ContactProductLandingMasterPage(System.getProperty("LOCALE"));
		Assert.assertEquals(cplpage.getSelectProductText(), cplMasterPage.getSelectProductText());
	}
	
	@Test (enabled=true, groups={"contactLanding"})
	public void allProductsTest(){
		
		ContactProductLandingPage actualPage = new ContactProductLandingPage(driverThread.get(), System.getProperty("TEST_URL"));
		Map<String, Product> actualProducts = actualPage.getAllProducts();
		ContactProductLandingMasterPage masterPage = new ContactProductLandingMasterPage(System.getProperty("LOCALE"));
		Map<String, Product> expectedProducts = masterPage.getAllProducts();
		Product tempActProd = null;
		Product tempExpProd = null;
		
		Assert.assertEquals(actualProducts.size(), expectedProducts.size());
		
		for(String key : expectedProducts.keySet()){
			
			tempExpProd = expectedProducts.get(key);
			tempActProd = actualProducts.get(key);
			
			Assert.assertEquals(tempActProd.getRefKey(), tempExpProd.getRefKey());
			Assert.assertEquals(tempActProd.getDescription(), tempExpProd.getDescription());
		}
	}


	@Test (enabled=true, groups={"contactLanding"})
	public void allVersionsTest(){
		
		ContactProductLandingPage actualPage = new ContactProductLandingPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactProductLandingMasterPage masterPage = new ContactProductLandingMasterPage(System.getProperty("LOCALE"));
		Map<String, Product> expectedProducts = masterPage.getAllProducts();
		Product tempExpProd = null;
		Map<String, Version> actualVersionsForProduct = null;
		Map<String, Version> expectedVersionsForProduct = null;
		Version tempExpVer = null;
		Version tempActVer = null;
		
		for(String key : expectedProducts.keySet()){
			
			tempExpProd = expectedProducts.get(key);
			
			if(tempExpProd.hasVersions()){
			
				actualVersionsForProduct = actualPage.getVersionForProduct(tempExpProd.getRefKey());
				expectedVersionsForProduct = masterPage.getVersionForProduct(tempExpProd.getRefKey());
				
				Assert.assertEquals(actualVersionsForProduct.size(), expectedVersionsForProduct.size());
				
				for(String verKey : expectedVersionsForProduct.keySet()){
					
					tempExpVer = expectedVersionsForProduct.get(verKey);
					tempActVer = actualVersionsForProduct.get(verKey);
					
					Assert.assertEquals(tempActVer.getRefKey(), tempExpVer.getRefKey());
					Assert.assertEquals(tempActVer.getDescription(), tempExpVer.getDescription());
				}
			}
		}
	
	}
	
	@Test (enabled=true, groups={"contactLanding"})
	public void allPortletsTest(){
		
		ContactProductLandingPage cplpage = new ContactProductLandingPage(driverThread.get(), System.getProperty("TEST_URL"));
		List<String> actualPortlets = cplpage.getAllPortlets();
		ContactProductLandingMasterPage cplMasterPage = new ContactProductLandingMasterPage(System.getProperty("LOCALE"));
		List<String> expectedPortlets = cplMasterPage.getAllPortlets();
		ActionUtil.logStep("checking for  right hand portlets ");
		for(int i = 0 ; i < actualPortlets.size(); i++) {
			Assert.assertEquals(actualPortlets.get(i), expectedPortlets.get(i));
		}
	  }
	
	
	@Test (enabled=true, groups={"contactLanding"})
	public void whatsMyVersionTest(){
		ContactProductLandingPage actualPage = new ContactProductLandingPage(driverThread.get(), System.getProperty("TEST_URL"));
		ContactProductLandingMasterPage masterPage = new ContactProductLandingMasterPage(System.getProperty("LOCALE"));

		Map<String, Product> productMap = masterPage.getAllProducts();
		for(String key : productMap.keySet()){
			Product prod = productMap.get(key);
			if(prod.getRefKey().equalsIgnoreCase("PROD_MAIL") || prod.getRefKey().equalsIgnoreCase("PROD_MSNG")){
				ActionUtil.logStep("Verifying whats my version link for Product: " + prod.getRefKey());
				Assert.assertEquals(masterPage.getSLNforWhatsMyVersion(prod.getRefKey()), actualPage.getWhatsMyVersionArticle(prod.getRefKey()));
			}
		}
	}
		
}
