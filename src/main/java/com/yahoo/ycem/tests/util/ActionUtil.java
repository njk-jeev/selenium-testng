package com.yahoo.ycem.tests.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import com.google.common.base.Function;

public class ActionUtil {
	
	private static final String CLICK_EVENT = "<i><b>Clicked</i></b> ";
	private static final String SEPARATOR = " - ";

	public static void captureScreenShot(WebDriver driver) {
		if ("on".equalsIgnoreCase(System.getProperty("SCREENSHOT"))) {
			try {
				// If the driver is RemoteWebDriver then augment it to enable
				// screen
				// shots on it.
				if (driver.getClass().getName().contains("RemoteWebDriver")) {
					driver = new Augmenter().augment(driver);
				}
				final DateFormat df = new SimpleDateFormat("HH_mm_ss");
				final DateFormat dfFolder = new SimpleDateFormat("yyyy_MM_dd");
				
				File scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
				
				String browser = System.getProperty("BROWSER");
				String fileName = "target/surefire-reports/screenshots/"
						+ dfFolder.format(new Date()) + "/" + browser + "/"
						+ df.format(new Date()) + ".png";
				FileUtils.copyFile(scrFile, new File(fileName));
				String currentDir = new File(".").getAbsolutePath()
						.replace(".", "").replace("SelNG-v400","SelNG-v4.00");
				currentDir = "file:///"
						+ (currentDir + fileName).replace("\\", "/");
				String machineName = "ccdwdevdb02.corpapps.corp.sk1.yahoo.com:9999/yhudson/job/ycem-tests/ws/Cem_Test_Suites/SelNG-v4.00/";
				if (currentDir.contains("/home/y/var/")) {
					currentDir = currentDir
							.replace("file:///", "http://")
							.replace("/home/y/var/builds/workspace/ycem-tests/Cem_Test_Suites/SelNG-v4.00/", machineName);
				}
				logStep("<b><a href='"
						+ currentDir
						+ "' onclick=\"window.open('"
						+ currentDir
						+ "','popup','"
						+ "width=800,height=1500,toolbar=no,directories=no,location=no,"
						+ "menubar=no,status=no,left=0,top=0'); return false\">"
						+ "Click to see screen shot" + "</a></b> Browser Size:"
						+ getBrowserSize(driver));
			} catch (Exception e) {
				logFailureStep("Could not capture screenshot due to:" + e);
			}
		} else {
			logStep("Screenshot not enabled."
					+ "set flag SCREENSHOT=on to capture screenshot.");
		}
	}


/*
	
public static void fluentWait(WebDriver driver, final By locator){
    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(30, TimeUnit.SECONDS)
            .pollingEvery(5, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class);

    WebElement element = wait.until(
new Function<WebDriver, WebElement>() {
        public WebElement apply(WebDriver driver) {
                    return driver.findElement(locator);
            }
            }
);
                       return  element;              }     ;

*/
	/*
	
	 public static void waitAndClick(final WebDriver driver,  final By locator)
		      throws RuntimeException {
			  
			  FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					  .withTimeout(90, TimeUnit.SECONDS)
					  .pollingEvery(5, TimeUnit.MILLISECONDS)
					  .ignoring(NoSuchElementException.class);

			  WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				  return driver.findElement(locator);
				  }
				  });

		    String text = element.getText();
		    String elementName = (text.trim().length() == 0 && element
		        .getAttribute("value") != null) ? element.getAttribute("value") : text;
		    element.click();
		    logStep(CLICK_EVENT + SEPARATOR + elementName);
		  }
		  
		  */

	 public static void waitAndClick(final WebDriver driver, final By locator)
		      throws RuntimeException {
			  
			  FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					  .withTimeout(90, TimeUnit.SECONDS)
					  .pollingEvery(5, TimeUnit.MILLISECONDS)
					  .ignoring(NoSuchElementException.class);

			  WebElement seyi = wait.until(new Function<WebDriver, WebElement>() {
				  public WebElement apply(WebDriver driver) {
				  return driver.findElement(locator);
				  }
				  });

		    String text = seyi.getText();
		    String elementName = (text.trim().length() == 0 && seyi
		        .getAttribute("value") != null) ? seyi.getAttribute("value") : text;
		    seyi.click();
		    logStep(CLICK_EVENT + SEPARATOR + elementName);
		  }



	
	 
	 
	public static String getBrowserSize(WebDriver driver) {
		Options options = driver.manage();
		Dimension dimension = options.window().getSize();
		return "W: " + dimension.getWidth() + " H: " + dimension.getHeight();
	}

	/**
	 * Logs the message to be seen from testng report.
	 * 
	 * @param message
	 */
	public static void logStep(final String message) {
		Reporter.log(message);
	}

	/**
	 * Logs the message in <b>bold</b> to be seen from testng report.
	 * 
	 * @param message
	 */
	public static void logFailureStep(final String message) {
		Reporter.log("<b>" + message + "</b>" + "\n");
	}

	/**
	 * Logs the message in <b>bold</b> to be seen from testng report.
	 * 
	 * @param message
	 */
	public static void logPassColorStep(final String message) {
		Reporter.log("<font color='GREEN'><b>" + message + "</b></font>" + "\n");
	}

	/**
	 * Logs the message in <b>bold</b> to be seen from testng report.
	 * 
	 * @param message
	 */
	public static void logFailureColorStep(final String message) {
		Reporter.log("<font color='RED'><b>" + message + "</b></font>" + "\n");
	}
	
}