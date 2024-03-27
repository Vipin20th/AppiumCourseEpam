package appiumBase;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;

public class AppDriver {
	
	    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

	    public static AppiumDriver getDriver(){
	        return driver.get();
	    }

	    public static void setDriver(AppiumDriver Driver){
	        driver.set(Driver);
	        System.out.println("Driver is set");
	    }
	

}
