package appiumBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppFactory {

    private static AppiumDriver<MobileElement> driver;
    private static DesiredCapabilities cap;

    public void Android_LaunchApp() throws MalformedURLException {
        cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "12.0");        
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("newCommandTimeout", "50000");
//        cap.setCapability("appPackage", "com.swaglabsmobileapp");
//        cap.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
        cap.setCapability("app", System.getProperty("user.dir")+"/app/SauceLabs.apk");
		cap.setCapability("androidInstallTimeout", "200000");
        driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        AppDriver.setDriver(driver);
        System.out.println("Android driver is set");
        

    }

    public void iOS_LaunchApp() throws MalformedURLException {
        cap = new DesiredCapabilities();
        cap.setCapability("platformName", "iOS");
        cap.setCapability("deviceName", "iPhone 11");
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("platformVersion", "13.5");
        cap.setCapability("newCommandTimeout", "45000");
        cap.setCapability("bundleId", "");
        driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        AppDriver.setDriver(driver);
        System.out.println("iOS driver is set");

    }
    
    
    public void Chrome_LaunchApp() throws MalformedURLException {
    	 //WebDriverManager.chromedriver().setup();
    	  
    	
        cap = new DesiredCapabilities();
        cap.setCapability("platformName", "Android");
        cap.setCapability("platformVersion", "11.0");
        cap.setCapability("deviceName", "emulator-5554");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("newCommandTimeout", "45000");
        cap.setCapability("browserName", "chrome");
        cap.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"/drivers/chromedriver");
       // cap.setCapability("chromedriverExecutableDir", "folder path");
        //command to run "appium --allow-insecure chromedriver_autodownload"
		//cap.setCapability("androidInstallTimeout", "200000");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        System.out.println("Chrome Browser is set");
       
    }


    public static void closeApp(){
        try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
