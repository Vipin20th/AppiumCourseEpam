package com.UIElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.Utils.BaseObjects;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SwagLabUI {
	
	BaseObjects tk = new BaseObjects();
	
	public SwagLabUI(WebDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
	public WebElement username;
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
	public WebElement password;

	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
	public WebElement loginButton;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Menu']/android.view.ViewGroup/android.widget.ImageView")
	public WebElement menu_icon;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'ALL ITEMS')]")
	public WebElement all_item;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Modal Selector Button']/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView")
	public WebElement product_container_filter;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'Name (A to Z)')]")
	public WebElement filter_az;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'Sauce Labs Bike Light')]")
	public WebElement product_bike_light;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-ADD TO CART']")
	public WebElement addToCart;
	
	@FindBy(xpath = "//android.widget.TextView[@content-desc='test-Price']")
	public WebElement price;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']/android.view.ViewGroup/android.widget.ImageView")
	public WebElement shoppingCart;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
	public WebElement description;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView")
	public WebElement priceOnCheckOutPage;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CHECKOUT']")
	public WebElement checkOut;
	
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc='test-First Name']")
	public WebElement firstName;
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc='test-Last Name']")
	public WebElement lastName;
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc='test-Zip/Postal Code']")
	public WebElement zipCode;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-CONTINUE']")
	public WebElement continueButton;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-FINISH']")
	public WebElement finishButton;
	
	@FindBy(xpath = "//android.widget.ScrollView[@content-desc='test-CHECKOUT: COMPLETE!']/android.view.ViewGroup/android.widget.TextView[1]")
	public WebElement thankYouPage;
	
	@FindBy(xpath = "//android.widget.TextView[contains(@text, 'WEBVIEW')]")
	public WebElement webViewMenu;
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc='test-enter a https url here...']")
	public WebElement url_field;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc='test-GO TO SITE']")
	public WebElement goToSite;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='nav-search-keywords']")
	public WebElement amazonSearch;
	
	@FindBy(xpath = "(//android.widget.Button)[3]")
	public WebElement amazonSearchOption;
	
	
	
	
	public void loginInSwag(String userName, String passWord) {
		tk.driverHelper().enterText(username, userName);
		tk.driverHelper().enterText(password, passWord);
		tk.driverHelper().clickElement(loginButton);
	}
	
	
	public void fillDetails(String firstName, String lastName, String pincode) {
		tk.driverHelper().enterText(this.firstName, firstName);
		tk.driverHelper().enterText(this.lastName, lastName);
		tk.driverHelper().enterText(zipCode, pincode);
		tk.driverHelper().clickElement(continueButton);
	}
	
	



	
	
	
	
	
	
	
	


}
