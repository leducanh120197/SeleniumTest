package com.seleniumtest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class Test2 {
	WebDriver driver;

	@Test
	public void testCheckBox() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
		
		WebElement element = driver.findElement(By.xpath("//*[@id=\"isAgeSelected\"]"));

		if (!element.isSelected()) element.click();
		
		Thread.sleep(5000);
	}
	
//	@Test
	public void testKeyBoard() throws InterruptedException {
		String message = "abc";
		String message2 = "cccccccccccccccc";
		driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		driver.findElement(By.xpath("//*[@placeholder=\"Please enter your Message\"]")).sendKeys(message);

		driver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();
		String outMess = driver.findElement(By.xpath("//*[@id=\"display\"]")).getText();

		if (message.equals(outMess)) {
			System.out.println("NGon");
		} else {
			System.out.println("=.=");
		}
		assertEquals(outMess, message2, "Expected output" + message2 + "but actual output is " + outMess);
		Thread.sleep(5000);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
		// driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		//driver.close();
	}

}
