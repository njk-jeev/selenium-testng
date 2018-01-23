package com.yahoo.ycem.tests.components;

import java.util.HashMap;

import com.yahoo.ycem.tests.pages.BaseMasterPage;

public class UniversalHeaderMaster extends BaseMasterPage implements UniversalHeaderAttributes {
	private static HashMap<String,String> headerHomeText = new HashMap<String,String>(); // locale to header home text map
	
	static {
		headerHomeText.put("en_US", "Home");
	}
    public UniversalHeaderMaster(String locale)
    {
    	super(locale);
    }
    
	@Override
	public String helpCentralTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String yahooAccountHelpTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getYahooLogoText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderHome() {
		String configuredValue = headerHomeText.get(locale);
		if(configuredValue == null)
			configuredValue = headerHomeText.get(DEFAULT_LOCALE);
		return configuredValue;
	}

	@Override
	public String getheaderMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderNews() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderSports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderFinance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderWeather() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderScreen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderFlickr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderMore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderSignin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderSigninMail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getheaderUniversalLogo() {
		// TODO Auto-generated method stub
		return null;
	}

}
