package apitest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai9 {
	WebDriver driver;

	@Test (enabled = true)
	public void f() {
		driver.get("https://www.seleniumeasy.com/test/jquery-date-picker-demo.html");
		
//		Select select = new Select(null);
//		
		driver.findElement(By.xpath("//*[@id=\"from\"]")).click();
		Select select = new Select(driver.findElement(By.xpath("//*[@class=\"ui-datepicker-month\"]")));
		select.selectByVisibleText("Mar");
		driver.findElement(By.xpath("//a[contains(text(), \"1\")]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"to\"]")).click();
		Select select2 = new Select(driver.findElement(By.xpath("//*[@class=\"ui-datepicker-month\"]")));
		select2.selectByVisibleText("May");
		driver.findElement(By.xpath("//a[contains(text(), \"5\")]")).click();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		  driver.close();
	}
	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}
	public WebElement waitAndGetElement(By locator, long timeOut) throws InterruptedException {
		for(int i=0; i < timeOut/500;i++) {
			if(isElementPresent(locator))
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
