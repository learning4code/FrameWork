 package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	//Here we are creating constructor to get the properties prop from the parent class
	public LoginPageTest() {
		super();
	}
	
	
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage=new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title=loginPage.validatePageTitle();
		Assert.assertEquals(title, "Cogmento CRM");
	}
	
	@Test(priority=2)
	public void loginTest() {
		homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
