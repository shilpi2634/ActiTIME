package com.skillrary.actitime.genericlibs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FUtil {
	public static String getSnapshot(WebDriver driver, String testCaseName) {
		String timeStamp = LocalDateTime.now().toString().replace(':', '-');
		TakesScreenshot ts = (TakesScreenshot)driver;
		File tempFile=ts.getScreenshotAs(OutputType.FILE);
		File destFile= new File(IAutoConstants.SCREENSHOT_PATH+testCaseName+timeStamp+".png");
		try {
			FileUtils.copyFile(tempFile, destFile);
			return destFile.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	 
	public static void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
