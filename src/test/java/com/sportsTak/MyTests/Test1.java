package com.sportsTak.MyTests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.sportstk.MyPages.HomePage;

public class Test1 extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@Test(enabled = false)
	public void verifyleftSidebarLinksList() {
		extentTest = extentReport.createTest("verifyleftSidebarLinksList");
		try {
			List<WebElement> urlList = page.getInstance(HomePage.class).HomePagesLinkText();
			System.out.println("Number of Links are--> " + urlList.size());
			extentTest.info("Number of Links are--> " + urlList.size());
//URLActualLists
			List<String> URLActualLists = new ArrayList<String>();
			List<String> URLExptLists = new ArrayList<String>(
					Arrays.asList("Buzz", "Social", "Schedule", "Web Story", "Polls"));

			for (WebElement e : urlList) {

				String url = e.getText();
				URLActualLists.add(url);
			}
			System.out.format("Actual Links are -->\n" + URLActualLists);
			extentTest.info("Actual Links are -->\n" + URLActualLists);
			extentTest.info("Expected Links are -->\n" + URLExptLists);

			if (URLActualLists.equals(URLExptLists) == true) {
				// SoftAssert.assertEquals(URLActualLists, URLExptLists,"Both List are Equals");
				Assert.assertEquals(URLActualLists, URLExptLists, "Both List are Equals");
				System.out.println("Links Dislplayed on webPage is Expected and visible on WebPage.");
				extentTest.log(Status.PASS, "Links Dislplayed on webPage is Expected and visible on WebPage.");
			} else {
				// SoftAssert.assertEquals(URLActualLists, URLExptLists,"Both List are Not
				// Equals");
				Assert.assertEquals(URLActualLists, URLExptLists, "Both List are Not Equals");
				System.out.println("Links Dislplayed on webPage is NOT expected with the Actual WebPage.");
				extentTest.log(Status.FAIL, "Links Dislplayed on webPage is NOT expected with the Actual WebPage.");
			}
		} catch (Exception e) {
			System.out.println("Some Exception occured in Method verifyleftSidebarLinksList:- " + e.getMessage());
			extentTest.info("Some Exception occured in Method verifyleftSidebarLinksList:- " + e.getMessage());
		}
	}

	@Test
	public void verifyHomePageButtonVisibility() {
		extentTest = extentReport.createTest("verifyHomePageButtonVisibility");
		try {
			List<WebElement> buttonsList = page.getInstance(HomePage.class).HomePagesButtons();
			System.out.println("Total buttons found in HomePage:--> " + buttonsList.size());
			extentTest.info("Total buttons found in HomePage:-->" + buttonsList.size());
			List<WebElement> acticeButtons = new ArrayList<WebElement>();
			List<WebElement> inactiveButtons = new ArrayList<WebElement>();

			for (int i = 0; i < buttonsList.size(); i++) {

				WebElement button = buttonsList.get(i);
				String ariaLabel = button.getAttribute("aria-label");
				String buttonText = button.getText();

				if (ariaLabel != null || !buttonText.isEmpty()) {
					acticeButtons.add(button);
				} else {
					inactiveButtons.add(button);
				}

			}

			extentTest.info("ActiceButtons Found is:-" + acticeButtons.size());
			extentTest.info("inactiveButtons Found is:-" + inactiveButtons.size());

			int acticeButtonsclickableTrueCount = 0;
			int acticeButtonsclickableFalseCount = 0;
			int inactiveButtonsclickableTrueCount = 0;
			int inactiveButtonsclickableFalseCount = 0;

			for (WebElement button : acticeButtons) {
				boolean isClickable = button.isEnabled() && button.isDisplayed();
				System.out.println("ButtonText:" + button.getText() + ", Aria-Label: "
						+ button.getAttribute("Aria-Label") + ", Clickable :" + isClickable);
				if (isClickable == true) {
					++acticeButtonsclickableTrueCount;
					softAssert.assertTrue(isClickable, "ButtonText:" + button.getText() + ", Aria-Label: "
							+ button.getAttribute("Aria-Label") + ", Clickable :" + isClickable);
					extentTest.log(Status.PASS, "ButtonText:" + button.getText() + ", Aria-Label: "
							+ button.getAttribute("Aria-Label") + ", Clickable :" + isClickable);
				}

				else {
					++acticeButtonsclickableFalseCount;
					softAssert.assertTrue(isClickable, "ButtonText:" + button.getText() + ", Aria-Label: "
							+ button.getAttribute("Aria-Label") + ",is NOT Clickable :" + isClickable);
					extentTest.log(Status.FAIL, "ButtonText:" + button.getText() + ", Aria-Label: "
							+ button.getAttribute("Aria-Label") + ", is NOT Clickable :" + isClickable);
				}
			}

			extentTest.info(" acticeButtons clickableCount True Counts :- " + acticeButtonsclickableTrueCount);
			extentTest.info(" acticeButtons clickableCount False Counts :- " + acticeButtonsclickableFalseCount);

			for (WebElement UnActivebutton : inactiveButtons) {
				boolean isClickable = UnActivebutton.isEnabled() && UnActivebutton.isDisplayed();
				if (isClickable == true) {
					++inactiveButtonsclickableTrueCount;
					softAssert.assertTrue(isClickable,
							"UnActivebuttonClassName:" + UnActivebutton.getAttribute("class") + ", Aria-Label: "
									+ UnActivebutton.getAttribute("Aria-Label") + ", Clickable :" + isClickable);
					extentTest.log(Status.PASS,
							"UnActivebuttonClassName:" + UnActivebutton.getAttribute("class") + ", Aria-Label: "
									+ UnActivebutton.getAttribute("Aria-Label") + ", Clickable :" + isClickable);
				} else {
					++inactiveButtonsclickableFalseCount;
					softAssert.assertTrue(isClickable,
							"UnActivebuttonClassName:" + UnActivebutton.getAttribute("class") + ", Aria-Label: "
									+ UnActivebutton.getAttribute("Aria-Label") + ",is NOT Clickable :" + isClickable);
					extentTest.log(Status.FAIL,
							"UnActivebuttonClassName:" + UnActivebutton.getAttribute("class") + ", Aria-Label: "
									+ UnActivebutton.getAttribute("Aria-Label") + ", is NOT Clickable :" + isClickable);
				}
			}

			extentTest.info(" inactiveButtons clickableCount True Counts :- " + inactiveButtonsclickableTrueCount);
			extentTest.info(" inactiveButtons clickableCount False Counts :- " + inactiveButtonsclickableFalseCount);

		} catch (Exception e) {
			System.out.println("Some Exception occured in Method verifyHomePageButtonVisibility:- " + e.getMessage());
			extentTest.warning("Some Exception occured in Method verifyHomePageButtonVisibility:- " + e.getMessage());
		}
		softAssert.assertAll();
	}

}
