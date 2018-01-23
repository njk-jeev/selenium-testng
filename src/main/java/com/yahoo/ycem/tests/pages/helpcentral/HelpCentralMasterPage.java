package com.yahoo.ycem.tests.pages.helpcentral;

import com.yahoo.ycem.tests.components.UniversalHeaderAttributes;
import com.yahoo.ycem.tests.components.UniversalHeaderMaster;
import com.yahoo.ycem.tests.pages.BaseMasterPage;

public class HelpCentralMasterPage extends BaseMasterPage implements HelpCentralPageAttributes {

	UniversalHeaderMaster uhMaster;
	
	public HelpCentralMasterPage(String locale)
	{
	    super(locale);	
	    uhMaster = new UniversalHeaderMaster(locale);
	}
	
	@Override
	public String getpageTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelpByTopicText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCantAccessAccountText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSuspiciousActivityAndSpamText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrganizeAndRestoreMessagesText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDownloandAndInstallText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBillingText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSecurityAndSafetyText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSendingAndReceivingEmailChatText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCommentsAndSharingText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getErrorAndAlertsText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFantasyteamManagementAndScoringText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isPopAndImapDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPopAndImapText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttachmentsAndPhotosText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccessibilityText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSettingsAndPreferencesText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVideoText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHelpByProductText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMyAccountText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getYahooMailText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMessengerText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSportsText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSearchText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMoreProductsText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getChooseLanguageText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UniversalHeaderAttributes getUHAttriubutes() {
        return uhMaster;
	}

}
