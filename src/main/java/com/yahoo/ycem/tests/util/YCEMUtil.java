package com.yahoo.ycem.tests.util;

public class YCEMUtil {

	 public static String getLanguageFromLocale(String locale) {
	    	String langagueName = "en";
	    	if(locale !=null && !locale.equals("")) {
	    		if(locale.indexOf("_HK")> 0 ){
	    			langagueName ="zh1";
	    		} else {
	    			langagueName = locale.substring(0,2);
	    		}
	    	}
	    	
	    	return langagueName;
	    }
	 
	public static boolean isWindows(){
			
			boolean isWindows = false;
			if (System.getProperty("os.name").startsWith("Windows")){
				isWindows = true;
			}
			return isWindows;
		}

	public static boolean isMac(){
			
			boolean isMac = false;
			if (System.getProperty("os.name").startsWith("Mac")){
				isMac = true;
			}
			return isMac;
		}
}
