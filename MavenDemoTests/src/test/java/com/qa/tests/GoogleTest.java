package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class GoogleTest {

	
	@Test
	public void searchTest() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium Softwares\\myDrivers\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://google.com");
		
		driver.manage().window().maximize();
		driver.findElement(By.name("q")).sendKeys("Maan Doolaa rey!");
		Actions a=new Actions(driver);
		
		
		a.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.close();
	}
}
