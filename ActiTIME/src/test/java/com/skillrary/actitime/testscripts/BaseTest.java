package com.skillrary.actitime.testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.skillrary.actitime.genericlibs.FUtil;
import com.skillrary.actitime.genericlibs.IAutoConstants;
import com.skillrary.actitime.genericlibs.WebActionUtil;
import com.skillrary.actitime.pom.HomePage1;
import com.skillrary.actitime.pom.LoginPage1;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConstants  {
	
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public HomePage1 homePage;
	
	@Parameters({"browserName", "appUrl", "implicit", "explicit"})
	@BeforeClass(alwaysRun=true)
	public void openApp(@Optional(DEFAULT_BROWSER)String browserName,
			            @Optional(DEFAULT_URL)String appUrl, 
			            @Optional(ITO)String implicit, 
			            @Optional(ETO)String explicit) {
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		} else {
			Assert.fail("Only 2 Browsers Chrome and Firefox are Supported");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(implicit), TimeUnit.SECONDS);
		driver.get(appUrl);
		webActionUtil = new WebActionUtil(driver, Long.parseLong(explicit));
	}
	
	@Parameters({"username", "password"})
	@BeforeMethod(alwaysRun=true)
	public void loginToApp(@Optional(DEFULT_USER)String username,
						   @Optional(DEFAULT_PASSWORD)String password) {
		homePage = new LoginPage1(driver, webActionUtil).login(username, password);
	}
	
	@AfterMethod(alwaysRun=true)
	public void loginoutFromApp(ITestResult result) {
		
		if(result.getStatus()== ITestResult.FAILURE) {
			Reporter.log(FUtil.getSnapshot(driver, result.getName()), true);
		}
		
		homePage.logout();
	}
	
	
	@AfterClass(alwaysRun=true)
	public void closeApp() {
		webActionUtil.quitBrowser();		
	}


}
