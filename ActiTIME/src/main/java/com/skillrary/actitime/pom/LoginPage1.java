package com.skillrary.actitime.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillrary.actitime.genericlibs.WebActionUtil;

public class LoginPage1 extends BasePage1 {
	
	@FindBy(id="username")
	private WebElement usernameTextField;
	
	@FindBy(name="pwd")
	private WebElement passwordField;
	
	@FindBy(id="loginButton")
	private WebElement loginButton;
	
	@FindBy(id="keepLoggedInCheckBox")
	private WebElement rememberCheckBox;
	
	public LoginPage1(WebDriver driver ,WebActionUtil webActionUtil)
	{
		super(driver,webActionUtil);
		webActionUtil.verifyPage("actiTIME - Login", "https://demo.actitime.com/login.do");

	}

	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getRememberCheckBox() {
		return rememberCheckBox;
	}
	
	public HomePage1 login(String userData, String passdData) {
		webActionUtil.enterData(usernameTextField, userData);
		webActionUtil.enterData(passwordField, passdData);
		webActionUtil.click(loginButton);
		return new HomePage1(driver, webActionUtil);
	}

}
