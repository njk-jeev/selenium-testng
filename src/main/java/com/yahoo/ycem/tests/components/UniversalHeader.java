package com.yahoo.ycem.tests.components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.yahoo.ycem.tests.pages.BasePage;

public class UniversalHeader extends BasePage implements UniversalHeaderAttributes {
	
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(), 'Yahoo Hel')]")
	private WebElement logo;

	@FindBy(how = How.XPATH, using = "//ul[@id='yucs-top-list']")
	private WebElement topBar;
	
	//@FindBy(how = How.XPATH, using = "//ul[@id='yucs-top-list']//li[1]//a")
	//private WebElement homeLink;
	
	@FindBy(how = How.XPATH, using = "//ul/li/a")
	private WebElement headerHome;
	
	@FindBy(how = How.XPATH, using = "//li[2]/a")
	private WebElement headerMail;
	
	@FindBy(how = How.XPATH, using = "//li[3]/a")
	private WebElement headerNews;
	
	@FindBy(how = How.XPATH, using = "//li[4]/a")
	private WebElement headerSports;
	
	@FindBy(how = How.XPATH, using = "//li[5]/a")
	private WebElement headerFinance;
	
	@FindBy(how = How.XPATH, using = "//li[6]/a")
	private WebElement headerWeather;
	
	@FindBy(how = How.XPATH, using = "//li[7]/a")
	private WebElement headerGames;
	
	@FindBy(how = How.XPATH, using = "//li[8]/a")
	private WebElement headerGroups;
	
	@FindBy(how = How.XPATH, using = "//li[9]/a")
	private WebElement headerAnswers;
	
	@FindBy(how = How.XPATH, using = "//li[10]/a")
	private WebElement headerScreen;
	
	@FindBy(how = How.XPATH, using = "//li[11]/a")
	private WebElement headerFlickr;
	
	@FindBy(how = How.XPATH, using = "//li[12]/a")
	private WebElement headerMore;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='searchInput']") 
	@FindBy(how = How.XPATH, using = "//*[@id='mnp-search_box']") 
	private WebElement headerSearchField;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Search Help')]") 
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Search Help')]") 
	private WebElement headerSearchHelp;
	
	@FindBy(how = How.XPATH, using = "//div[2]/button")
	private WebElement headerSearchWeb;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yucs-login_signIn']")
	private WebElement headerSignin;
	
	@FindBy(how = How.XPATH, using = "//*[@id='yucs-mail_link_id']")
	private WebElement headerMaill;
	
	@FindBy(how = How.XPATH, using = "//div[@class='pit']")
	private WebElement headerHelpYou;
	
	@FindBy(how = How.XPATH, using = "//div[@class='qlb nhl']")
	private WebElement headerPhone;
	
	
	public UniversalHeader(WebDriver driver, String url) {
		super(driver, url);
	}
	
	public boolean isTopBarDisplayed(){
		
		List<WebElement> topBarLinks = topBar.findElements(By.xpath(".//li"));
		WebElement menuItem = null;
		boolean result = true;
		
		if(topBarLinks != null && !topBarLinks.isEmpty()){
			for(WebElement topBarLink : topBarLinks){
				if(topBarLink != null){
					menuItem = topBarLink.findElement(By.xpath(".//a"));
					result = result && menuItem.isDisplayed();
					if("More".equals(menuItem.getText())){
						break;
					}
				}
			}
		}
		
		return result;
	}
	
	
	public String helpCentralTitle() {
		
		 WebElement title = driver.findElement(By.tagName("title"));
		 return title.getTagName();
	}
	
	public String yahooAccountHelpTitle() {
		
		 WebElement title = driver.findElement(By.tagName("title"));
		 return title.getTagName();
	}
	
	
	
	public boolean isYahooLogoDisplayed(){
		return logo.isDisplayed();
	}
	
	public String getYahooLogoText(){
		return logo.getText();
	}

    public boolean isHomeHeaderDisplayed(){
	return headerHome.isDisplayed();
}

    public String getheaderHome(){
	return headerHome.getText();
}

    public boolean isMailHeaderDisplayed(){
	return headerMail.isDisplayed();
}

    public String getheaderMail(){
	return headerMail.getText();
}

    public boolean isNewsHeaderDisplayed(){
	return headerNews.isDisplayed();
}

    public String getheaderNews(){
	return headerNews.getText();
}
    public boolean isSportsHeaderDisplayed(){
    return headerSports.isDisplayed();
    }

    public String getheaderSports(){
    return headerSports.getText();
    }
    
    public boolean isFinanceHeaderDisplayed(){
    return headerFinance.isDisplayed();
    }

    public String getheaderFinance(){
    return headerFinance.getText();
    }
    
    public boolean isWeatherHeaderDisplayed(){
    return headerWeather.isDisplayed();
    }

    public String getheaderWeather(){
    return headerWeather.getText();
    }
    
    public boolean isGamesHeaderDisplayed(){
    return headerGames.isDisplayed();
        }

    public String getheaderGames(){
    return headerGames.getText();
        }
    
    public boolean isGroupsHeaderDisplayed(){
    return headerGroups.isDisplayed();
        }

    public String getheaderGroups(){
    return headerGroups.getText();
        }
    
    public boolean isAnswersHeaderDisplayed(){
    return headerGroups.isDisplayed();
        }

    public String getheaderAnswers(){
    return headerAnswers.getText();
        }
    
    public boolean isScreenHeaderDisplayed(){
    return headerScreen.isDisplayed();
        }

    public String getheaderScreen(){
    return headerScreen.getText();
        }
    
    public boolean isFlickrHeaderDisplayed(){
    return headerFlickr.isDisplayed();
        }

    public String getheaderFlickr(){
    return headerFlickr.getText();
        }
    
    public boolean isMoreHeaderDisplayed(){
    return headerMore.isDisplayed();
        }

    public String getheaderMore(){
    return headerMore.getText();
        }
   
    public boolean isSearchFieldHeaderDisplayed(){
    return headerSearchField.isDisplayed();
        }
    
    public boolean isSearchHelpButtonHeaderDisplayed(){
    return headerSearchHelp.isDisplayed();
        }
    
    public boolean isSearchWebButtonHeaderDisplayed(){
    return headerSearchWeb.isDisplayed();
        }
    
    public boolean isSigninHeaderDisplayed(){
    return headerSignin.isDisplayed();
        }

    public String getheaderSignin(){
    return headerSignin.getText();
        }
    
    public boolean isSigninMailHeaderDisplayed(){
    return headerMaill.isDisplayed();
        }

    public String getheaderSigninMail(){
    return headerMail.getText();
         }
    
    public boolean isUniversalHeaderLogoDisplayed(){
    return headerHelpYou.isDisplayed();
         }
    
   public String getheaderUniversalLogo(){
   return headerHelpYou.getText();
         }
    
    public boolean isYahooCustomerCareNumberDisplayed(){
        return headerPhone.isDisplayed();
             }

    public void search(String searchTerm) throws InterruptedException{
           
    	headerSearchField.sendKeys(searchTerm);
    	headerSearchHelp.click();
    	Thread.sleep(5000);
    }

   

}
