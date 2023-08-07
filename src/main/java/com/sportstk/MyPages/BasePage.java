package com.sportstk.MyPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends Page {

	public BasePage(WebDriver driver) {
		super(driver);

	}

	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}

	@Override
	public String getPageHeader(By locator) {
		return getElement(locator).getText();

	}

	@Override
	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			waitForElementPresent(locator);
			element = driver.findElement(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some error occurred while creating Element " + locator.toString());
			e.printStackTrace();
		}
		return element;
	}

	public List<WebElement> getListElement(By locator) {

		List<WebElement> element = null;
		try {
			waitForElementPresent(locator);
			element = driver.findElements(locator);
			return element;
		} catch (Exception e) {
			System.out.println("Some error occurred when waitForElementPresent while creating List of Elements "
					+ locator.toString());
			e.printStackTrace();
		}

		return element;
	}

	@Override
	public void waitForElementPresent(By locator) {
		try {
			wait.until((ExpectedConditions.presenceOfElementLocated(locator)));
		} catch (Exception e) {
			System.out.println("Some errorException occurred while Waiting for the  Element " + locator.toString());
			e.printStackTrace();
		}

	}

	@Override
	public void waitForPageTitle(String title) {

		try {
			wait.until((ExpectedConditions.titleContains(title)));
		} catch (Exception e) {
			System.out.println("Some errorException occurred while Waiting for the  Element " + title);
			e.printStackTrace();
		}

	}

	@Override
	public void waitForPageLoad() {
		try {
			wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
		} catch (Exception e) {
			System.out.println("Some errorException occurred while Waiting for the  waitForPageLoad Method is BasePage "
					+ e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public String getCurrentPageUrl() {

		return driver.getCurrentUrl();
	}
	public void refreshPage() {
		driver.navigate().refresh();

	}

}
