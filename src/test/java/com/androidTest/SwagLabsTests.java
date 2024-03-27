package com.androidTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.UIElements.SwagLabUI;
import com.Utils.BaseObjects;

import appiumBase.AppDriver;
import io.appium.java_client.AppiumDriver;
import report.ReportInit;

public class SwagLabsTests {

	private BaseObjects baseObject = new BaseObjects();

	@Test()
	public void completeCheckout() throws Exception {
		SwagLabUI swag = new SwagLabUI(AppDriver.getDriver());
		swag.loginInSwag("standard_user", "secret_sauce");
		ReportInit.getTest().info("successfully login with standard user");
		baseObject.driverHelper().clickElement(swag.menu_icon);
		baseObject.driverHelper().clickElement(swag.all_item);
		ReportInit.getTest().info("clicked on all item option in menu");
		baseObject.driverHelper().clickElement(swag.product_container_filter);
		baseObject.driverHelper().clickElement(swag.filter_az);
		baseObject.driverHelper().clickElement(swag.product_bike_light);
		ReportInit.getTest().info("scroll for add to cart");
		baseObject.driverHelper().scrollDown();
		baseObject.driverHelper().clickElement(swag.addToCart);
		String price = baseObject.driverHelper().getText(swag.price);
		baseObject.driverHelper().clickElement(swag.shoppingCart);
		ReportInit.getTest().info("clicked on shopping cart");
		String description = baseObject.driverHelper().getText(swag.description);
		String priceOnCheckOut = baseObject.driverHelper().getText(swag.priceOnCheckOutPage);
		assertEquals(price, priceOnCheckOut, "price not matched");
		assertEquals(description, "Sauce Labs Bike Light", "description not matched");
		baseObject.driverHelper().clickElement(swag.checkOut);
		swag.fillDetails("company", "onboarding", "110051");
		baseObject.driverHelper().scrollDown();
		baseObject.driverHelper().clickElement(swag.finishButton);
		 ReportInit.getTest().info("clicked on Finish button");
		String thanksYouPage = baseObject.driverHelper().getText(swag.thankYouPage);
		assertEquals(thanksYouPage, "THANK YOU FOR YOU ORDER", "description not matched");
	}
	
	@Test()
	public void webView() throws Exception {
		AppiumDriver driver = AppDriver.getDriver();
		SwagLabUI swag = new SwagLabUI(AppDriver.getDriver());
		swag.loginInSwag("standard_user", "secret_sauce");
		ReportInit.getTest().info("successfully login with standard user");
		baseObject.driverHelper().clickElement(swag.menu_icon);
		ReportInit.getTest().info("open menu");
		baseObject.driverHelper().clickElement(swag.webViewMenu);
		ReportInit.getTest().info("click on webview option in menu");
		Thread.sleep(4000);
		Set<String> contexts = driver.getContextHandles();
		 for (String context : contexts) {
             System.out.println(contexts);
            if (!context.equals("NATIVE_APP")) {
                driver.context((String) contexts.toArray()[1]);
                break;
            }
        }
		 ReportInit.getTest().info("context switched to webview");
		 baseObject.driverHelper().enterText(swag.url_field, "https://www.amazon.com");
		 baseObject.driverHelper().clickElement(swag.goToSite);
		 ReportInit.getTest().info("open amazon url");
		 Thread.sleep(5000);
		 baseObject.driverHelper().enterText(swag.amazonSearch, "Samsung Mobile M53");
		 ReportInit.getTest().info("search samsung mobile on amazon");
		 baseObject.driverHelper().clickElement(swag.amazonSearchOption); 
		 
	}

}
