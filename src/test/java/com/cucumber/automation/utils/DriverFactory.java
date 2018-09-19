package com.cucumber.automation.utils;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DriverFactory {

	public static WebDriver driver = null;
	public static WebDriverWait waitVar = null;

	public static String baseURL = "https://github.com/";

	/**
	 *  This function is to invoke Selenium Webdriver
	 * 
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public void createDriver() throws MalformedURLException,
			InterruptedException {
		System.setProperty("webdriver.gecko.driver", "lib/geckodriver.exe");
		FirefoxProfile profile = new FirefoxProfile();
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
		profile.setAcceptUntrustedCertificates(false);
		profile.setAssumeUntrustedCertificateIssuer(true);
		profile.setPreference("browser.link.open_newwindow.restriction", 0);
		profile.setAcceptUntrustedCertificates(true);

		profile.setPreference("browser.download.folderList", 2);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/msword, application/csv, application/ris, text/csv, image/png, application/pdf, " +
						"text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, " +
						"application/download, application/octet-stream");
		profile.setPreference("browser.download.manager.showWhenStarting", false);
		profile.setPreference("browser.download.manager.focusWhenStarting", false);
		profile.setPreference("browser.download.useDownloadDir", true);
		profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		profile.setPreference("browser.download.manager.closeWhenDone", true);
		profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		profile.setPreference("browser.download.manager.useWindow", false);
		profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		profile.setPreference("pdfjs.disabled", false);
		profile.setPreference("browser.applicationCacheEnabled", false);

		capabilities.setCapability(FirefoxDriver.PROFILE, profile);
		capabilities.setCapability("marionette", false);
		driver = new FirefoxDriver(capabilities);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get(baseURL);

		waitVar = new WebDriverWait(driver, 15);
	}

	/**
	 * This function is to close driver instance
	 */
	public void teardown() {
			driver.quit();
	}
}
