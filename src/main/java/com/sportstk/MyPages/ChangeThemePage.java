package com.sportstk.MyPages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangeThemePage extends BasePage {

	private By Theme = By.xpath("//div[@class='right']//button[1]");

	private By pageBackGroundColour = By.xpath("//html[@class='i-amphtml-singledoc i-amphtml-standalone hydrated']");

	private By cricketPage = By.xpath("//button[@aria-label='Cricket']");
	private By Soccer = By.xpath("//button[@aria-label='Soccer']");
	private By Motorsports = By.xpath("//button[@aria-label='Motor sports']");
	
	public ChangeThemePage(WebDriver driver) {
		super(driver);

	}

	public void ChangeTheme() {

		getElement(Theme).click();
	}

	public WebElement site_BackGroundColor() {
		return getElement(pageBackGroundColour);

	}

	public List<String> verifyThemeCssProperties(String allCssPrperties) {
		
			List<String> StylePropertiesValue = new ArrayList<String>();

			String[] styleProperties = allCssPrperties.split(";");

			if (styleProperties.length > 0) {
				// Get the first style property
				String firstProperty = styleProperties[0];
				String activeElement = styleProperties[1];
				String textColour = styleProperties[2];

				// Split the first property by colon
				String[] propertyPart = firstProperty.split(":");
				String[] propertyTextPart = textColour.split(":");
				String[] propertyActivePagePart = activeElement.split(":");

				if (propertyPart.length > 1) {
					// Retrieve the value of the first property
					String firstValue = propertyPart[1].trim();
					String pageTextValue = propertyTextPart[1].trim();
					String pageActiveElementValue = propertyActivePagePart[1].trim();
					System.out.println("First value: " + firstValue);
					System.out.println("Text value: " + pageTextValue);
					System.out.println("Active Page value: " + pageActiveElementValue);
					StylePropertiesValue.add(firstValue);
					StylePropertiesValue.add(pageTextValue);
					StylePropertiesValue.add(pageActiveElementValue);
					System.out.println("StylePropertiesValue List:- " + StylePropertiesValue);

				} else {
					System.out.println("Invalid style attribute value");
				}

			} else {
				System.out.println("No style properties found");
			}

			return StylePropertiesValue;

	}
	
	public void navigateToCricketPage() {
		waitForElementPresent(cricketPage);
		 getElement(cricketPage).click();
		 
	}
	public void navigateToSoccerPage() {
		waitForElementPresent(Soccer);
		 getElement(Soccer).click();
		 
	}
	public void navigateToMotorsportsPage() {
		waitForElementPresent(Motorsports);
		 getElement(Motorsports).click();
		 
	}
	
	
}