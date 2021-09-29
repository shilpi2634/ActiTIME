package com.skillrary.actitime.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.skillrary.actitime.genericlibs.FUtil;
import com.skillrary.actitime.genericlibs.WebActionUtil;

public class EnterTimeTrackPage extends BasePage1 {
	
	@FindBy(id="addTaskButtonId")
	private WebElement addTaskButton;
	
	@FindBy(xpath="//div[contains(@class, 'customerSelector')]//div[@class='dropdownButton']")
	private WebElement custSelectDropdownButton;
	
	@FindBy(xpath="//div[contains(@class, 'customerSelector')]//div[contains(@class,'itemRow')]")
	private List<WebElement> custSelectDropdownOptionsList;
	
	@FindBy(xpath="//input[@placeholder='Enter Customer Name']")
	private WebElement custNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Project Name']")
	private WebElement projectNameTextField;
	
	@FindBy(xpath="(//input[@placeholder='Enter task name'])[1]")
	private WebElement enterTaskTextField;
	
	@FindBy(xpath="(//input[@placeholder='not needed'])[1]")
	private WebElement estimateHoursTextField;
	
	@FindBy(xpath="(//button[text()='set deadline'])[1]")
	private WebElement setDeadLineButton;
	
	@FindBy(xpath="//div[text()='Create Tasks']")
	private WebElement createTasksButton;
	
	@FindBy(xpath="//div[@class='task']")
	private List<WebElement> tasksList;
	
	@FindBy(xpath="//td[@class='x-date-active' or contains(@class, 'activex-date-weekend') or contains(@class, 'x-date-selected')]")
	private List<WebElement> daysList;
	
	@FindBy(xpath="//td[@class='x-date-middle']")
	private WebElement currentMonthAndYearData;
	
	@FindBy(xpath="//a[@title='Next Month (Control+Right)']")
	private WebElement nextMonthButton;
	
	@FindBy(xpath="//div[contains(@class,'customerSelector')]//div[@class='iScrollIndicator']")
	private WebElement scrollBarElement;
	
	
	public WebElement getAddTaskButton() {
		return addTaskButton;
	}


	public WebElement getCustSelectDropdownButton() {
		return custSelectDropdownButton;
	}


	public List<WebElement> getCustSelectDropdownOptionsList() {
		return custSelectDropdownOptionsList;
	}


	public WebElement getCustNameTextField() {
		return custNameTextField;
	}


	public WebElement getProjectNameTextField() {
		return projectNameTextField;
	}


	public WebElement getEnterTaskTextField() {
		return enterTaskTextField;
	}


	public WebElement getEstimateHoursTextField() {
		return estimateHoursTextField;
	}


	public WebElement getSetDeadLineButton() {
		return setDeadLineButton;
	}


	public WebElement getCreateTasksButton() {
		return createTasksButton;
	}


	public List<WebElement> getTasksList() {
		return tasksList;
	}


	public EnterTimeTrackPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver, webActionUtil);
		webActionUtil.verifyPage("actiTIME - Enter Time-Track", "https://demo.actitime.com/user/submit_tt.do");
	}
	
	public void selectOption(String optionName) {
		for(WebElement ele:custSelectDropdownOptionsList) {
			if(ele.getText().contains(optionName)) {
				webActionUtil.jsClick(ele);
				break;
			}
		}
	}
	
	public void selectDate(String day, String monthAndyear) {
		outer:
		for(;;) {
			if(currentMonthAndYearData.getText().contains(monthAndyear)) {
				for(WebElement ele:daysList) {
					if(ele.getText().equals(day)) {
						webActionUtil.click(ele);
						break outer;
					}
				}
			} else {
				webActionUtil.click(nextMonthButton);
			}
			
		}		
	}
	
	public void createTask(String optionName, String custName, String projectName, String taskName,
						   String estimateHours, String day, String monthAndYear) {
		int currentCount = tasksList.size();
		FUtil.sleepInSeconds(5);
		webActionUtil.jsClick(addTaskButton);
		webActionUtil.click(custSelectDropdownButton);
		try {
			webActionUtil.scrollBarElement(scrollBarElement, -100);
		} catch (Exception e) {
			
		}
		
		selectOption(optionName);
		webActionUtil.enterData(custNameTextField, custName);
		webActionUtil.enterData(projectNameTextField, projectName);
		webActionUtil.enterData(enterTaskTextField, taskName);
		webActionUtil.enterData(estimateHoursTextField, estimateHours);
		webActionUtil.click(setDeadLineButton);
		selectDate(day, monthAndYear);
		webActionUtil.click(createTasksButton);
		webActionUtil.waitForNumberOfElementsToBeLess(By.xpath("//div[@class='task']"), currentCount);
	}
	
	public boolean verifyTask(String taskName) {
		for(WebElement ele:tasksList) {
			if(ele.isDisplayed() && ele.getText().equalsIgnoreCase(taskName)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteTask(String taskName) {
		
	}

}
