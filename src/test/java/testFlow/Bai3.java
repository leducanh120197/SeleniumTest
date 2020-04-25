package testFlow;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai3 {
	WebDriver driver;

	@Test(enabled = true)
	public void f() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/bootstrap-progress-bar-dialog-demo.html");
		driver.findElement(By.xpath("//button[@class=\"btn btn-warning\"]")).click();

		for(int i=0; i<10000; i+=1000) {
			if(isElementPresent(By.xpath("//div[@class=\"modal fade in\"]"))) {
				System.out.println("alert");
			}else {
				System.out.println("not alert");
			}
			Thread.sleep(1000);
		}
	}
	
	@Test(enabled = false)
	public void f2() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/bootstrap-progress-bar-dialog-demo.html");
		driver.findElement(By.xpath("//button[@class=\"btn btn-warning\"]")).click();

		for(int i=0; i<10000; i+=1000) {
			if(isElementPresent(By.xpath("//div[@class=\"modal fade in\"]"))) {
				System.out.println("alert");
			}else {
				System.out.println("not alert");
			}
			Thread.sleep(1000);
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
//		  driver.close();
	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement waitAndGetElement(By locator, long timeOut) throws InterruptedException {
		for (int i = 0; i < timeOut / 500; i++) {
			if (isElementPresent(locator))
				return driver.findElement(locator);
			else {
				System.out.println("Element not available");
//				Utils.pause(500);
				Thread.sleep(500);
			}
		}
		return null;
	}
}
