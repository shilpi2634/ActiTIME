package com.skillrary.actitime.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillrary.actitime.genericlibs.WebActionUtil;

public class TaskPage extends BasePage1 {
	
	@FindBy(xpath="//div[@class='unfilteredContainer']//div[@class='iScrollIndicator']")
	private WebElement scrollBarElement;
	
	@FindBy(xpath="//div[contains(@class,'customerNode ')]//div[@class='text']")
	private List<WebElement> custNamesList;
	
	@FindBy(xpath="//div[@class='titleEditButtonContainer']/div[@class='editButton']")
	private WebElement editIcon;
	
	@FindBy(xpath="//div[@class='editCustomerPanelHeader']//div[text()='ACTIONS']")
	private WebElement actionsButton;
	
	@FindBy(xpath="//div[@class='taskManagement_customerPanel']//div[@class='deleteButton']")
	private WebElement deleteIcon;
	
//	@FindBy(xpath="//span[text()='Delete permanently']")
//	private WebElement deletePermanentlyButton;
	
	@FindBy(xpath="//span[text()='Delete permanently']/..")
	private WebElement deletePermanentlyButton;
	
	@FindBy(xpath="//div[@class='content_customerPanel']/div[@class='scrollableContainer ' and contains(@style,'display: block')]//div[@class='iScrollVerticalScrollbar iScrollLoneScrollbar' and contains(@style,'visible')]/div")
	private WebElement deleteScrollBarElement;
	
	public TaskPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		webActionUtil.verifyPage("actiTIME - Task List", "https://demo.actitime.com/tasks/tasklist.do");
	}
	
	public boolean clickOnCustomerName(String custName) {

		for(int i=1;i<=3;i++) {
			for(WebElement ele:custNamesList) {
				if(ele.getText().equalsIgnoreCase(custName)) {
					webActionUtil.click(ele);
					return true;
				}
			}
			webActionUtil.scrollBarElement(scrollBarElement, 100);
		}
		
		return false;
		
	}
	
	public void deletePermanently() {
		for(int i=1;i<=5;i++) {
			if(deletePermanentlyButton.isDisplayed()) {
				webActionUtil.click(deletePermanentlyButton);
				break;
			} else {
				webActionUtil.scrollBarElement(deleteScrollBarElement, 25);
			}			
		}		
	}
	
	
	public void deleteCustomer(String custName) {
		if(clickOnCustomerName(custName)) {
			webActionUtil.click(editIcon);
			webActionUtil.jsClick(actionsButton);
			webActionUtil.click(deleteIcon);
			deletePermanently();
		}		
	}


}
