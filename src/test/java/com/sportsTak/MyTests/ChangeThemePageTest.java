package com.sportsTak.MyTests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.sportstk.MyPages.ChangeThemePage;

public class ChangeThemePageTest extends BaseTest {

	@Test(priority = 1)
	public void VerifyDefaultThemeBeforeClick() {

		try {
			extentTest = extentReport.createTest("VerifyDefaultThemeBeforeClick");
			String allCssPrperties = page.getInstance(ChangeThemePage.class).site_BackGroundColor()
					.getAttribute("style");
			System.out.println("CssPrperties:-" + allCssPrperties);
//			extentTest.info("Default_SiteCssPrperties:-" +allCssPrperties );

			List<String> StylePropertiesValue = page.getInstance(ChangeThemePage.class)
					.verifyThemeCssProperties(allCssPrperties);
			Assert.assertEquals(StylePropertiesValue.get(0), "#FFFFFF",
					"Background colour is in White Which is Default theme..");
			Assert.assertEquals(StylePropertiesValue.get(1), "#000", "Text colour is in Black Colour..");
			Assert.assertEquals(StylePropertiesValue.get(2), "#FF443B", "Active Element colour is in Red Colour..");
			extentTest.log(Status.PASS, "Background theme colour is in White Which is Default theme.." +StylePropertiesValue.get(0) +
					"Text colour is in Black Colour" +StylePropertiesValue.get(1) + 
					"Active Element colour is in Red Colour" +StylePropertiesValue.get(2));
			
			page.getInstance(ChangeThemePage.class).refreshPage();
			Assert.assertEquals(StylePropertiesValue.get(0), "#FFFFFF",
					"Background colour is in White Which is Default theme..");
			extentTest.log(Status.PASS, "After Refresh the page Background colour is in White Which is Default theme.."+ StylePropertiesValue.get(0));
			
			page.getInstance(ChangeThemePage.class).navigateToCricketPage();
			extentTest.info("After Navigated To CricketPage");
			Assert.assertEquals(StylePropertiesValue.get(0), "#FFFFFF",
					"Background colour is in White Which is Default theme..");
			Assert.assertEquals(StylePropertiesValue.get(1), "#000", "Text colour is in Black Colour..");
			Assert.assertEquals(StylePropertiesValue.get(2), "#FF443B", "Active Element colour is in Red Colour..");	
			extentTest.log(Status.PASS, "Navigated_ToCricketPage To check the PageTheme is Same"
					+ ":-Background theme colour is in White Which is Default theme."
					 +StylePropertiesValue.get(0) +
					"Text colour is in Black Colour" +StylePropertiesValue.get(1) + 
					"Active Element colour is in Red Colour" +StylePropertiesValue.get(2));

		} catch (Exception e) {
			System.out.println("Some Exception occured in VerifyDefaultThemeBeforeClick Method:- " + e.getMessage());

		}

	}

	@Test(priority = 2,enabled=false)
	public void VerifyChangeThemeAfterClick() {

		try {
			page.getInstance(ChangeThemePage.class).ChangeTheme();

			String allCssPrperties = page.getInstance(ChangeThemePage.class).site_BackGroundColor()
					.getAttribute("style");
			extentTest.info("AfterThemeChange_SiteCssPrperties:-" +allCssPrperties );
			
			List<String> StylePropertiesValue = page.getInstance(ChangeThemePage.class)
					.verifyThemeCssProperties(allCssPrperties);
			Assert.assertEquals(StylePropertiesValue.get(0), "#1E1E1E", "Background theme colour is in Black..");
			Assert.assertEquals(StylePropertiesValue.get(1), "#E0E1EB", "Text colour is in Grey Colour..");
			Assert.assertEquals(StylePropertiesValue.get(2), "#FF443B", "Active Element colour is in Red Colour..");
			extentTest.log(Status.PASS, "Background theme colour is in Black.." +StylePropertiesValue.get(0) +
					"Text colour is in Grey Colour" +StylePropertiesValue.get(1) + 
					"Active Element colour is in Red Colour" +StylePropertiesValue.get(2));
			
			
			page.getInstance(ChangeThemePage.class).refreshPage();
			page.getInstance(ChangeThemePage.class).navigateToMotorsportsPage();

			Assert.assertEquals(StylePropertiesValue.get(0), "#1E1E1E", "Background theme colour is in Black..");
			Assert.assertEquals(StylePropertiesValue.get(1), "#E0E1EB", "Text colour is in Grey Colour..");
			Assert.assertEquals(StylePropertiesValue.get(2), "#FF443B", "Active Element colour is in Red Colour..");
			extentTest.log(Status.PASS, "After Refresh and Navigated_ToMotorsportsPage " + " Background theme colour is in Black.." +StylePropertiesValue.get(0) +
					"Text colour is in Grey Colour" +StylePropertiesValue.get(1) + 
					"Active Element colour is in Red Colour" +StylePropertiesValue.get(2));
			
			
		} catch (Exception e) {
			System.out.println("Some Exception occured in VerifyChangeThemeAfterClick Method:- " + e.getMessage());

		}

	}

}
