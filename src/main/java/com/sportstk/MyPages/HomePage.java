package com.sportstk.MyPages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

	// Home Page Locators

	private By ChangeLanguageButton = By.xpath("//div[@class='right']//button[2]");
	private By LinksText = By.xpath("//div[@class='MuiDrawer-root MuiDrawer-docked leftSidebar']//ul//child::img");
	private By header = By.xpath("//img[@class='logo-container']");

	private By thumbnail = By.tagName("img");
	private By AllLinks = By.tagName("a");
	private By buttons = By.tagName("button");
	// private By buttons = By.xpath("button");
	
	public static String HindiTitle = "पढ़ें नवीनतम खेल समाचार, वीडियो, आज की खेल सुर्खियाँ, और अलग-अलग खेलों के लाइव अपडेट और हाईलाइट्स सिर्फ और सिर्फ स्पोर्ट्स तक पर | SportsTak";

	public static String curentHindiUrl = "https://hindi.thesportstak.com/";
	
	public HomePage(WebDriver driver) {
		super(driver);
	}



	public String getHomePageTitle() {
		return getPageTitle();
	}
	
	public String getHomepageHeader() {
		return getPageHeader(header);
	}

	public void ChangeLanguageEnglishToHindi() {

		getElement(ChangeLanguageButton).click();

	}

	public void ChangeLanguageHindiToEnglish() {

		// getElement(Menu).click();
		getElement(ChangeLanguageButton).click();

	}

	public List<WebElement> HomePagesLinkText() {
		return getListElement(LinksText);

	}

	public List<WebElement> HomePagesthumbnaiLikns() {
		return getListElement(thumbnail);

	}

	public List<WebElement> HomePagesAll_Links() {
		return getListElement(AllLinks);

	}

	public List<WebElement> HomePagesButtons() {
		return getListElement(buttons);

	}

	public String checkBrokenLinkConnection(String linkUrl) throws IOException {

		URL url = new URL(linkUrl);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		String responseCode = connection.getResponseMessage(); // ok
		connection.disconnect();

		return responseCode;

	}

	public int checkBrokenLinkConnection_WithResponseCode(String linkUrl) throws IOException {

		URL url = new URL(linkUrl);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect();
		int responseCode = connection.getResponseCode(); // ok
		connection.disconnect();

		return responseCode;

	}

}
