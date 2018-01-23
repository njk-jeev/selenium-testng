package com.yahoo.ycem.tests.pages.contactus.landing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yahoo.ycem.tests.beans.contact.Product;
import com.yahoo.ycem.tests.beans.contact.Version;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;
import com.yahoo.ycem.tests.components.UniversalHeaderMaster;
import com.yahoo.ycem.tests.pages.BaseMasterPage;
import com.yahoo.ycem.tests.util.CacheManager;

public class ContactProductLandingMasterPage extends BaseMasterPage implements
		ContactProductLandingAttributes {

	private UniversalHeaderMaster uhMaster;
	private String selectProductTextKey = "contact.landing.select.product";
	
	public ContactProductLandingMasterPage(String locale) {
		super(locale);
		uhMaster = new UniversalHeaderMaster(locale);
	}

	@Override
	public UniversalHeaderAttributes getUHAttributes() {
		return uhMaster;
	}

	@Override
	public String getSelectProductText() {
		return getLocalizedText(selectProductTextKey);
	}

	@Override
	public Map<String, Product> getAllProducts() {
		
		Map<String, Product> productsMap = CacheManager.getProductsFromLocale(locale);
		
//		Map<String, Product> productsMap = new HashMap<String, Product>();
//		Product p = new Product();
//		p.setDescription("Messenger");
//		p.setRefKey("PROD_MSNG");
//		p.setLocale("en_US");
//		productsMap.put("PROD_MSNG", p);
//		
//		p = new Product();
//		p.setDescription("Mail");
//		p.setRefKey("PROD_MAIL");
//		p.setLocale("en_US");
//		p.setVersions(true);
//		productsMap.put("PROD_MAIL", p);
//		
//		p = new Product();
//		p.setDescription("Finance");
//		p.setRefKey("PROD_FIN");
//		p.setLocale("en_US");
//		productsMap.put("PROD_FIN", p);
//		
//		p = new Product();
//		p.setDescription("Wallet - billing and purchases");
//		p.setRefKey("PROD_WALLET");
//		p.setLocale("en_US");
//		productsMap.put("PROD_WALLET", p);
//		
//		p = new Product();
//		p.setDescription("Yahoo Account");
//		p.setRefKey("PROD_ACCT");
//		p.setLocale("en_US");
//		productsMap.put("PROD_ACCT", p);
//		
//		p = new Product();
//		p.setDescription("Answers");
//		p.setRefKey("PROD_ANSW");
//		p.setLocale("en_US");
//		productsMap.put("PROD_ANSW", p);
//		
//		p = new Product();
//		p.setDescription("Groups");
//		p.setRefKey("PROD_GRPS");
//		p.setLocale("en_US");
//		productsMap.put("PROD_GRPS", p);
//		
//		p = new Product();
//		p.setDescription("Sports");
//		p.setRefKey("PROD_SPORTS");
//		p.setLocale("en_US");
//		productsMap.put("PROD_SPORTS", p);
//		
//		p = new Product();
//		p.setDescription("Mobile");
//		p.setRefKey("PROD_MOBILE");
//		p.setLocale("en_US");
//		productsMap.put("PROD_MOBILE", p);
//		
//		p = new Product();
//		p.setDescription("Search");
//		p.setRefKey("PROD_SRCH");
//		p.setLocale("en_US");
//		productsMap.put("PROD_SRCH", p);
		
		return productsMap;
	}

	@Override
	public Map<String, Version> getVersionForProduct(String prodRefKey) {
		
		Map<String, Version> versionsMap = CacheManager.getVersionsFromProductLocale(locale, prodRefKey);
//		Map<String, Version> versionsMap = new HashMap<String, Version>();
//		Version v = new Version();
//		v.setRefKey("PROD_MAIL_ML");
//		v.setDescription("Mail");
//		versionsMap.put("PROD_MAIL_ML", v);
//		
//		v = new Version();
//		v.setRefKey("PROD_MAIL_WIN8APP");
//		v.setDescription("Mail App for Windows 8");
//		versionsMap.put("PROD_MAIL_WIN8APP", v);
//		
//		v = new Version();
//		v.setRefKey("PROD_MAIL_ANDROIDAPP");
//		v.setDescription("Mail app for Android");
//		versionsMap.put("PROD_MAIL_ANDROIDAPP", v);
//		
//		v = new Version();
//		v.setRefKey("PROD_MAIL_IOSAPP");
//		v.setDescription("Mail app for iOS");
//		versionsMap.put("PROD_MAIL_IOSAPP", v);
//		
//		v = new Version();
//		v.setRefKey("PROD_MAIL_MOBILE");
//		v.setDescription("Mobile Mail");
//		versionsMap.put("PROD_MAIL_MOBILE", v);
		
		return versionsMap;
	}
	
	
	@Override
	public List<String> getAllPortlets() {
		List<String> portletsList = new ArrayList<String>();
		portletsList.add("PMG465");
		portletsList.add("PMG466");
		portletsList.add("PMG467");
		return portletsList;
	}

	public String getSLNforWhatsMyVersion(String prodRefKey){
		String slnID = "";
		if(prodRefKey != null && prodRefKey.equalsIgnoreCase("PROD_MAIL")){
			slnID = "SLN2069";
		}else if(prodRefKey != null && prodRefKey.equalsIgnoreCase("PROD_MSNG")){
			slnID = "SLN1079";
		}
		return slnID;
	}

}