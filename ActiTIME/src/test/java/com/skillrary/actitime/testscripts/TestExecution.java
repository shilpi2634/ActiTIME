package com.skillrary.actitime.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.skillrary.actitime.pom.EnterTimeTrackPage;
import com.skillrary.actitime.pom.TaskPage;

public class TestExecution extends BaseTest {
	
	EnterTimeTrackPage entertimeTrackPage;
	String taskName = "Seltos manufacturing";
	String custName = "KIA";
	
	
	@BeforeMethod(alwaysRun=true)
	public void deleteTaskBefore() {
		TaskPage taskPage = (TaskPage) homePage.clickOnMenuLink("Tasks");
		taskPage.deleteCustomer(custName);
		entertimeTrackPage = (EnterTimeTrackPage)homePage.clickOnMenuLink("Time-Track");
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), false);
	}
	
	@Test(description = "To Verify whether Created task is displayed in the tasks Page", invocationCount=2)
	public void testTaskCreation() {
		entertimeTrackPage.createTask("New Customer", custName, "Car Manufacturing", taskName, "10", "15", "October 2021");
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), true);
	}
	
	@AfterMethod(alwaysRun=true)
	public void deleteTaskAfter() {
		TaskPage taskPage = (TaskPage) homePage.clickOnMenuLink("Tasks");;
		taskPage.deleteCustomer(custName);
		entertimeTrackPage = (EnterTimeTrackPage) homePage.clickOnMenuLink("Time-Track");
		Assert.assertEquals(entertimeTrackPage.verifyTask(taskName), false);
	}


}
