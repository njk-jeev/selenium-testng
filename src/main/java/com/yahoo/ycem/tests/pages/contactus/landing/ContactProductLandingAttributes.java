package com.yahoo.ycem.tests.pages.contactus.landing;

import java.util.List;
import java.util.Map;

import com.yahoo.ycem.tests.beans.contact.Product;
import com.yahoo.ycem.tests.beans.contact.Version;
import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;

public interface ContactProductLandingAttributes {
	
	public UniversalHeaderAttributes getUHAttributes();
	
	public String getSelectProductText();
	
	public Map<String, Product> getAllProducts();

	public Map<String, Version> getVersionForProduct(String prodRefKey);

	public List<String> getAllPortlets();

}
