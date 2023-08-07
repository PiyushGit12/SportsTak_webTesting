package com.sportsTak.MyTests;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sportstk.MyPages.BasePage;
import com.sportstk.MyPages.Page;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Page page;
	public Properties prop;
	public static String screenShotSubFolderName;
	public static ExtentReports extentReport;
	public static ExtentSparkReporter extentSparkReporter;
	public static ExtentTest extentTest;

	@BeforeMethod
	public void setUpTest() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/resources/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String brws_name = prop.getProperty("browser");
		String url = prop.getProperty("URL");

		if (brws_name.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (brws_name.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//
		driver.get(url);
//		extentTest.log(Status.INFO, "Url Launched:-" + url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		page = new BasePage(driver);
		// we are using Child class(BasePage) of Page class(parent class) to calling
		// constructor of page class.
		// Other wise you will get NUll Pointer.
	}

	@BeforeSuite
	public void reportInitilization() throws IOException {
		extentReport = new ExtentReports();
		extentSparkReporter = new ExtentSparkReporter("Spark.html");
		extentSparkReporter.loadJSONConfig(new File("./src/test/resources/spark-config.json"));
		
		extentReport.attachReporter(extentSparkReporter);
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
	}

	@AfterMethod
	public void CheckStatus(Method m, ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail("Test Case " + result.getName() + " is Failed");
			// Capture ScreenShot here...
			String screenshotPath = result.getTestContext().getName() + "_" + result.getMethod().getMethodName()
					+ ".jpg";
			String temp = captureScreenShot(screenshotPath);
			extentTest.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName() + " is Passed");
		}
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());
		driver.quit();
	}

	public String captureScreenShot(String fileName) {
		if (screenShotSubFolderName == null) {
			LocalDateTime myDateObj = LocalDateTime.now();
			DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
			screenShotSubFolderName = myDateObj.format(myFormatDate);
		}
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("./Screenshots/" + screenShotSubFolderName + "/" + fileName);
		// Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			System.out.println("error occured during acaptureScreenShot " + e.getMessage());
		}
		System.out.println("Screenshot saved successfully");
		return DestFile.getAbsolutePath();
	}

	@AfterSuite
	public void flushReport() {
		extentReport.flush();
		try {
			Desktop.getDesktop().browse(new File("Spark.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
