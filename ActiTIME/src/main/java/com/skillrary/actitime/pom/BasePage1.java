package com.skillrary.actitime.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.skillrary.actitime.genericlibs.WebActionUtil;

public class BasePage1 {
public WebDriver driver;
public WebActionUtil webActionUtil;
public BasePage1(WebDriver driver, WebActionUtil webActionUtil) {
	this.driver=driver;
	this.webActionUtil=webActionUtil;
	PageFactory.initElements(driver, this);
}
}
