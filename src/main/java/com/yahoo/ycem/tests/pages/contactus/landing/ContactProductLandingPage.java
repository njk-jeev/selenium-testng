package com.yahoo.ycem.tests.pages.contactus.landing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.yahoo.ycem.tests.beans.contact.Product;
import com.yahoo.ycem.tests.beans.contact.Version;
import com.yahoo.ycem.tests.components.UniversalHeader;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;
import com.yahoo.ycem.tests.pages.BasePage;
import com.yahoo.ycem.tests.util.ActionUtil;

public class ContactProductLandingPage extends BasePage implements ContactProductLandingAttributes {

	private static final Logger LOGGER = LogManager.getLogger(ContactProductLandingPage.class);
	private UniversalHeader uh;
	
	@FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class, ' '),' b2 ')]//h2")
	WebElement selectProductText;
	
	@FindBy(how = How.XPATH, using = "//ul[contains(concat(' ',@class, ' '),' pmn ')]//li")
	List<WebElement> products;
	
	@FindBy(how = How.XPATH, using = "//div[contains(concat(' ',@class, ' '),' pdp ')]//div")
	List<WebElement> portlets;
		
	public ContactProductLandingPage(WebDriver driver, String url) {
		super(driver, url);
		//uh = new UniversalHeader(driver, url);
	}
	
	public UniversalHeader getUH(){
		return new UniversalHeader(driver, url);
	}

	@Override
	public UniversalHeaderAttributes getUHAttributes() {
		return getUH();
	}
	
	public String getSelectProductText() {
		return selectProductText.getText();
	}
	
	public Map<String, Product> getAllProducts(){
	
		LOGGER.info("inside getAllProducts");
		
		ActionUtil.logStep("inside getAllProducts");
		
		Map<String, Product> productsMap = new HashMap<String, Product>();
		Product tempProd = null;
		String[] prodClasses = null;
		
		for(WebElement product : products){
			
			if(product.getAttribute("class") != null && product.getAttribute("class").endsWith("prod")){

				prodClasses = product.getAttribute("class").split(" ");
				
				if(prodClasses[0] != null){

					tempProd = new Product();
					tempProd.setRefKey(prodClasses[0].toUpperCase());
					tempProd.setDescription(product.findElement(By.xpath(".//a")).getText());
					tempProd.setLocale(System.getProperty("LOCALE"));
					productsMap.put(prodClasses[0].toUpperCase(), tempProd);
					
				}
			}
		}
		
		return productsMap;
	}

	@Override
	public Map<String, Version> getVersionForProduct(String refKey) {
		
		LOGGER.info("inside getVersionForProduct");
		
		Map<String, Version> versionsMap = new HashMap<String, Version>();
		Version tempVer = null;
		String[] prodClasses = null;
		
		for(WebElement product : products){
			if(product.getAttribute("class") != null && product.getAttribute("class").endsWith("prod")){
				prodClasses = product.getAttribute("class").split(" ");
				if(refKey != null && refKey.toLowerCase().equals(prodClasses[0])){
					//found a product with version on the page
					WebElement versionDiv = product.findElement(By.xpath(".//div"));
					if(versionDiv != null){
						product.findElement(By.xpath(".//a")).click();
						List<WebElement> versions = versionDiv.findElements(By.xpath(".//li"));
						String[] verClasses = null;
						for(WebElement version : versions){
							if(version.getAttribute("class") != null && version.getAttribute("class").endsWith("ver")){
								verClasses = version.getAttribute("class").split(" ");
								if(verClasses[0] != null){
									tempVer = new Version();
									tempVer.setRefKey(verClasses[0].toUpperCase());
									tempVer.setDescription(version.findElement(By.xpath(".//a")).getText());
									versionsMap.put(verClasses[0].toUpperCase(), tempVer);
								}
							}
						}
					}
				}
			}
		}
		return versionsMap;
	}

	public String getWhatsMyVersionArticle(String refKey){
		String articleSlnId = "";

		String[] prodClasses = null;
		for(WebElement product : products){
			if(product.getAttribute("class") != null && product.getAttribute("class").endsWith("prod")){
				prodClasses = product.getAttribute("class").split(" ");
				if(refKey != null && refKey.toLowerCase().equals(prodClasses[0])){
					//found a product with version on the page
					WebElement versionDiv = product.findElement(By.xpath(".//div"));
					if(versionDiv != null){
						WebElement whatsMyVersionP = versionDiv.findElement(By.id("version-article"));
						WebElement whatsMyVersionAnchor = whatsMyVersionP.findElement(By.xpath(".//a"));
						if(whatsMyVersionAnchor != null) {
							String articleHref = whatsMyVersionAnchor.getAttribute("href");
							articleSlnId = articleHref.substring(articleHref.indexOf("SLN"), articleHref.indexOf("SLN") + 7);
						}
					}
				}
			}
		}

		return articleSlnId;
	}

	public List<String> getAllPortlets(){

		LOGGER.info("inside getAllPortlets");
		//String docId;
		List<String> portletsList = new ArrayList<String>();
		for(WebElement portlet : portlets){
			if(portlet.getAttribute("id") !=null  && !portlet.getAttribute("id").equals("")) {
			String docId = portlet.getAttribute("id");
				portletsList.add(docId);
			}
			
		}
		return portletsList;
	}

}