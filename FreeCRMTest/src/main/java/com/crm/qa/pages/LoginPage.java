package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	
	@FindBy(css="a[href$='cogmento.com/register']")
	WebElement signUp;
	
	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(css="div[class*='submit']")
	WebElement submitBtn;
	
	//This is the constructor for LoginPage class, so void is not used.
	//Initialization of the Page Object:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validatePageTitle() {
		return driver.getTitle();
	}
	
	//Its returning HomePage class Object.
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		submitBtn.click();
		
		return new HomePage();
	}
	
}
