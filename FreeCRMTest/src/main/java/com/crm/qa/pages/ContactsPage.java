package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(css = "div[class='ui header item mb5 light-black']")
	WebElement contactsLabel;

	@FindBy(css = "a[href*='new']")
	WebElement newContactLink;

	@FindBy(name = "first_name")
	WebElement firstName;

	@FindBy(name = "last_name")
	WebElement lastName;

	@FindBy(xpath = "//div[@name='hint']//i[@class='dropdown icon']")
	WebElement countryDropdown;

	@FindBy(css = "input[placeholder='Number']")
	WebElement phoneNo;

	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveBtn;
	
	//can use this also for saveBtn
	
	@FindBy(css = "i[class='save icon']")
	WebElement saveBtn0;

	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyContactsPage() {
		boolean match = false;
		if (contactsLabel.getText().equals("Contacts")) {
			match = true;
		}
		return match;
	}

	public void selectContactsByName(String name) {
		try {
			driver.findElement(
					By.xpath("//td[text()='" + name + "']//parent::tr//div[@class='ui fitted read-only checkbox']"))
					.click();
		}catch(Exception e) {
			driver.findElement(By.cssSelector("i[class='right chevron icon']")).click();
			driver.findElement(
					By.xpath("//td[text()='" + name + "']//parent::tr//div[@class='ui fitted read-only checkbox']"))
					.click();
		}
		
	}

	//have to use explicit wait here because it takes some time to save.
	//its compulsory to use explicit wait while dealing with critical actions like saving-
	//uploading etc, verify whether the action is successful or not. even while u use-
	//implicit wait its necessary to use explicit wait in these methods.
	
	public void createNewContact(String fname, String lname, String countryName, String contactNo) {
		newContactLink.click();
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);

		// Can't use select method cause the elements don't have select tab.
		//use the second format when no word matches in two values.
		//use the first format only when all the values are unique.
		
		countryDropdown.click();
		
		/*
		driver.findElement(By.xpath("//div[@class='visible menu transition']"
				+ "//span[@class='text'][contains(text(),'" + countryName + "')]")).click();
		*/
		
		driver.findElement(By.xpath("//div[@class='visible menu transition']"
				+ "//span[@class='text'][text()='"+countryName+"']"));
		phoneNo.sendKeys(contactNo);
		saveBtn0.click();
		
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("i[class$='red icon']"))));
	
	}
}
