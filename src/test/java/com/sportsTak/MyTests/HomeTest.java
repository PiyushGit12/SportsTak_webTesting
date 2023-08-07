package com.sportsTak.MyTests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sportstk.MyPages.HomePage;

public class HomeTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@Test
	public void verifyChangeLanguageFeature() {
		extentTest = extentReport.createTest("verifyChangeLanguageFeature");
		page.getInstance(HomePage.class).waitForPageLoad();
		String EnglishPageTitle = page.getInstance(HomePage.class).getHomePageTitle();
		extentTest.info("This is the Default Title of the page before changing Language -->" + EnglishPageTitle);
		page.getInstance(HomePage.class).ChangeLanguageEnglishToHindi();

		String HindiPageTitle = page.getInstance(HomePage.class).getHomePageTitle();
		extentTest.info("<i>This is the Title of the page After ChangeLanguage_EnglishToHindi --></i>" + HindiPageTitle);
				
		if(HindiPageTitle.equals(HomePage.HindiTitle)) {
			Assert.assertEquals(HindiPageTitle, HomePage.HindiTitle,"Title of the Page is same");
			extentTest.log(Status.PASS, HindiPageTitle +" ,Title of the Page is same");
						
		}else {
			Assert.assertEquals(HindiPageTitle, HomePage.HindiTitle,"Title of the Page is NOT same");
			extentTest.log(Status.FAIL, HindiPageTitle +" , Title of the Page is NOT same");
		}
		String currentPageurl = page.getInstance(HomePage.class).getCurrentPageUrl();
		extentTest.info("<i>Validatation Started for the HindiCurrentUrl of the Page is:-> </i>" +currentPageurl );
		
		if(currentPageurl.equals(HomePage.curentHindiUrl)) {
			Assert.assertEquals(currentPageurl, HomePage.curentHindiUrl,"Current Page URL is Same");
			extentTest.log(Status.PASS,"[" +currentPageurl + " , Current Page URL is Same");
		}else {
			Assert.assertEquals(currentPageurl, HomePage.curentHindiUrl,"Current Page URL is NOT Same");
			extentTest.log(Status.FAIL, "[" +currentPageurl +" , Current Page URL is NOT Same");
		}

		//softAssert.assertAll();
	}

	@Test
	public void verifyEachThumbnailNotBroken() throws IOException {
		extentTest = extentReport.createTest("verifyEachThumbnailNotBroken");
		page.getInstance(HomePage.class).waitForPageLoad();
		List<WebElement> imgList = page.getInstance(HomePage.class).HomePagesthumbnaiLikns();

		System.out.println("Size of imgList Links--> " + imgList.size());
		extentTest.info("Total Number of imgList Links Visible in WebPage --> " + imgList.size());

		List<WebElement> acticeLinks = new ArrayList<WebElement>();
		List<WebElement> brokenLinks = new ArrayList<WebElement>();

		for (int i = 0; i < imgList.size(); i++) {
			System.out.println(imgList.get(i).getAttribute("src"));

			if (imgList.get(i).getAttribute("src") != null && (!imgList.get(i).getAttribute("src").contains("data"))) {
				acticeLinks.add(imgList.get(i));

			} else {
				brokenLinks.add(imgList.get(i));
			}

		}
		System.out.println("Size of UnBroken Links Visible in WebPage--> " + acticeLinks.size());
		extentTest.info("Size of UnBroken Links Visible in WebPage --> " + acticeLinks.size());
		System.out.println("Size of Broken Links Visible in WebPage--> " + brokenLinks.size());
		extentTest.info("Size of Broken Links Visible in WebPage --> " + brokenLinks.size());
		extentTest.info("Broken Links Visible in WebPage--> " + brokenLinks);
		// Check Href,Url with HttpConnection api:

		for (int j = 0; j < acticeLinks.size(); j++) {

			String LinksAttribute = acticeLinks.get(j).getAttribute("src");
			String responseCode = page.getInstance(HomePage.class).checkBrokenLinkConnection(LinksAttribute);
			if (responseCode.equalsIgnoreCase("OK")) {

				softAssert.assertTrue(true, LinksAttribute + "--> " + responseCode);
				// softAssert.assertEquals(responseCode, "OK", LinksAttribute);
				System.out.println(LinksAttribute + "---> " + responseCode);
				extentTest.log(Status.PASS, LinksAttribute + "---> " + responseCode);
				
			} else {
				softAssert.assertFalse(false, LinksAttribute + "--> " + responseCode);
				// softAssert.assertEquals(responseCode, "NoTOK", LinksAttribute);
				System.out.println(LinksAttribute + "---> " + responseCode);
				extentTest.log(Status.FAIL, LinksAttribute + "---> " + responseCode);
			}
		}
		softAssert.assertAll();

	}

	@Test
	public void verifyleftSidebarLinksList() {
		extentTest = extentReport.createTest("verifyleftSidebarLinksList");
		try {
			List<WebElement> urlList = page.getInstance(HomePage.class).HomePagesLinkText();
			System.out.println("Number of Links are--> " + urlList.size());
			extentTest.info("Number of Links are--> " + urlList.size());

			List<String> URLExptLists = new ArrayList<String>();
			List<String> URLActualLists = new ArrayList<String>(
					Arrays.asList("Buzz","Schedule", "Web Story", "Polls"));

			for (WebElement e : urlList) {

				String url = e.getAttribute("alt");
				URLExptLists.add(url);
			}
			System.out.format("Expected Links are -->\n" + URLExptLists);
			extentTest.info("Expected Links are -->\n" + MarkupHelper.createOrderedList(URLExptLists));
			extentTest.info("Actual Links are -->\n" + MarkupHelper.createOrderedList(URLActualLists));

			if (URLActualLists.equals(URLExptLists) == true) {
				Assert.assertEquals(URLActualLists, URLExptLists, "Both List are Equals");
				// Assert.assertEquals(URLActualLists, URLExptLists,"Both List are Equals");
				System.out.println("Links Dislplayed on webPage is Expected and visible on WebPage.");
				extentTest.log(Status.PASS, "Links Dislplayed on webPage is Expected and visible on WebPage.");
			} else {
				Assert.assertEquals(URLActualLists, URLExptLists, "Both List are Not Equals");
				// Assert.assertEquals(URLActualLists, URLExptLists,"Both List are Not Equals");
				System.out.println("Links Dislplayed on webPage is NOT expected with the Actual WebPage.");
				extentTest.log(Status.FAIL, "Links Dislplayed on webPage is NOT expected with the Actual WebPage.");
			}
		} catch (Exception e) {
			System.out.println("Some Exception occured in Method verifyleftSidebarLinksList:- " + e.getMessage());
			extentTest.info("Some Exception occured in Method verifyleftSidebarLinksList:- " + e.getMessage());
		}
		//softAssert.assertAll();
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
