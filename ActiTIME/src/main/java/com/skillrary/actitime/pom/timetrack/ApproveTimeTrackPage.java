package com.skillrary.actitime.pom.timetrack;

import org.openqa.selenium.WebDriver;

import com.skillrary.actitime.genericlibs.WebActionUtil;
import com.skillrary.actitime.pom.BasePage1;

public class ApproveTimeTrackPage extends BasePage1{
	public ApproveTimeTrackPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		webActionUtil.verifyPage("actiTIME - Enter Time-Track", "https://demo.actitime.com/user/submit_tt.do");
	}
}
