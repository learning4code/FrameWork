package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.utils.TestUtil;

public class ContactsPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

	public ContactsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.clickOnContactsLink();
		driver.navigate().refresh();
	}

	// i am using enabled attribute for learning purpose only.
	@Test(priority = 1)    		//, enabled = false)
	public void verifyContactsLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsPage(), "Contacts Label didn't Match.");
	}

	@Test(priority = 2)			//, enabled = false)
	public void selectSingleContactByNameTest() throws InterruptedException {
		contactsPage.selectContactsByName("Phani Rajesh");
	}

	@DataProvider
	public Object[][] getCRMTestdata() throws Exception {

//		Object data[][] = null;
		Object[][] data;
		data = TestUtil.getTestData("contacts");
		return data;
	}

	@Test(priority = 3)				//, enabled = false)
	public void selectMultipleContactByNameTest() {
		contactsPage.selectContactsByName("Naveen Krishnan");
		contactsPage.selectContactsByName("V Shiva");
	}

	@Test(priority = 4, dataProvider = "getCRMTestdata")
	public void createNewContactTest(String firstName, String lastName, String country, String phoneNo) {
		// contactsPage.createNewContact("abc", "adin", "Uganda", "+91978634507688");
		contactsPage.createNewContact(firstName, lastName, country, phoneNo);

	}

	@AfterMethod			//(enabled = true)
	public void tearDown() {
		driver.quit();
	}

}
