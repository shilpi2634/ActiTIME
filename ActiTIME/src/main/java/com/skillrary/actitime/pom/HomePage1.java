package com.skillrary.actitime.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillrary.actitime.genericlibs.WebActionUtil;

public class HomePage1 extends BasePage1 {
	@FindBy(id="logoutLink")
	private WebElement logoutLink;
	
	@FindBy(linkText="Time-Track")
	private WebElement timeTrackLink;
	
	@FindBy(xpath="//a[contains(@class, 'content')]")
	private List<WebElement> menuLinksList;
	
	@FindBy(id="closeProjectLightBoxBtn")
	private WebElement closePopUpIcon;
	
	
	public HomePage1(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
//		webActionUtil.verifyPage("actiTIME - Enter Time-Track", "https://demo.actitime.com/user/submit_tt.do");
		try {
			webActionUtil.click(closePopUpIcon);
		} catch(Exception e) {
			
		}
	}
	
	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getTimeTrackLink() {
		return timeTrackLink;
	}
	
	public BasePage1 clickOnMenuLink(String menuName) {
		for(WebElement ele:menuLinksList) {
			if(ele.getText().equalsIgnoreCase(menuName)) {
				webActionUtil.click(ele);
				break;
			}
		}
		
		menuName = menuName.toLowerCase();
		switch(menuName){
			case "time-track": return new EnterTimeTrackPage(driver, webActionUtil);
			case "users": return new UsersPage(driver, webActionUtil);
			case "reports": return new ReportsPage(driver, webActionUtil);
			case "tasks": return new TaskPage(driver, webActionUtil);
			default : return null;
		}
	}
	
	public void logout() {
		webActionUtil.click(logoutLink);
	}
	
}
