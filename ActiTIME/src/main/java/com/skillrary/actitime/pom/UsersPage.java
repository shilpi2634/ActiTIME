package com.skillrary.actitime.pom;

import org.openqa.selenium.WebDriver;

import com.skillrary.actitime.genericlibs.WebActionUtil;

public class UsersPage extends BasePage1 {
	
	public UsersPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		webActionUtil.verifyPage("actiTIME - Enter Time-Track", "https://demo.actitime.com/user/submit_tt.do");
	}

}
