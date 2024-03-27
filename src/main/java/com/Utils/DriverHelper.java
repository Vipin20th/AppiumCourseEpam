package com.Utils;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import appiumBase.AppDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class DriverHelper {

	private final int waitTimeSeconds = 10;

	public WebElement getElementWhenVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), waitTimeSeconds);
		boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	        	wait.until(ExpectedConditions.visibilityOf(element));
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
		return element;
		
	}

	public void getElementWhenClickable(WebElement element) {
		new WebDriverWait(AppDriver.getDriver(), waitTimeSeconds)
		.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	

	public String getText(WebElement element) {
		return getElementWhenVisible(element).getText();
	}


	public void enterText(WebElement element, String text) {
		getElementWhenVisible(element).sendKeys(text);
	}

	public void clickElement(WebElement element) {
		getElementWhenClickable(element);
	}

	public static void scrollDown() {
		Dimension dimension = AppDriver.getDriver().manage().window().getSize();
		int scrollStart = (int) (dimension.getHeight() * 0.5);
		int scrollEnd = (int) (dimension.getHeight() * 0.2);

		new TouchAction((PerformsTouchActions) AppDriver.getDriver()).press(PointOption.point(0, scrollStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(0, scrollEnd))
				.release().perform();
	}


	public static void scrollIntoView(String Text) {
		String mySelector = "new UiSelector().text(" + Text + ").instance(0)";
		String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + mySelector
				+ ");";
		((AndroidDriver<?>) AppDriver.getDriver()).findElementByAndroidUIAutomator(command);
	}
	
}
