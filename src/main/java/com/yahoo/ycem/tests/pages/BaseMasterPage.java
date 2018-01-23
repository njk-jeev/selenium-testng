package com.yahoo.ycem.tests.pages;

import java.util.Locale;
import java.util.ResourceBundle;

public class BaseMasterPage {
	
	protected static final String DEFAULT_LOCALE = "en_US"; 
	
    protected String locale = DEFAULT_LOCALE;
    
    private static ResourceBundle rb;
    
    
    protected BaseMasterPage(String locale)
    {
    	this.locale = locale;
    	rb = ResourceBundle.getBundle("ycem/YCEMResources", new Locale(locale));
    }
    
    protected String getLocalizedText(String key){
    	
    	return rb.getString(key);
    }
}
