package com.sportstk.MyPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class Page {
	
	public WebDriver driver;
    WebDriverWait wait;

    protected Page(WebDriver driver){
        this.driver = driver;
                      

    }

   

    public abstract String getPageTitle();
    public abstract String getCurrentPageUrl();

    public abstract String getPageHeader(By locator);

    public abstract WebElement getElement(By locator);

    public abstract void waitForElementPresent(By locator);

    public abstract void waitForPageTitle(String title);

    public <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {

        try
        {
        	return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
        
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public abstract void waitForPageLoad();



}
