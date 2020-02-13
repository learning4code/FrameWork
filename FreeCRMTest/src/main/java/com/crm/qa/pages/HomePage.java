package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(css = "span[class='user-display']")
	WebElement userNameLabel;

	@FindBy(css = "a[href$='deals']")
	WebElement dealsLink;

	@FindBy(css = "a[href$='contacts']")
	WebElement contactsLink;

	@FindBy(css = "a[href$='tasks']")
	WebElement tasksLink;

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public ContactsPage clickOnContactsLink() {
		contactsLink.click();
		return new ContactsPage();
	}

	public DealsPage clickOnDealsLink() {
		dealsLink.click();
		return new DealsPage();
	}

	// Whenever you are dealing with clicking a button,
	// Then clicking button should return corresponding Page Object.
	public TasksPage clickOnTaskPage() {
		tasksLink.click();
		return new TasksPage();
	}

	public boolean verifyCorrectUserName() {
		boolean match = false;
		String uLab = userNameLabel.getText();
		if (uLab.equals("Learning Forcode")) {
			match = true;
		}
		return match;
	}

}
