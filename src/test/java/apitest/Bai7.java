package apitest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.Utils;

public class Bai7 {
	WebDriver driver;

	@Test (enabled = true)
	public void f() throws InterruptedException {
		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//*[@class=\"login\"]")).click();
		By locator = By.xpath("//*[@id=\"email_create\"]");
		waitAndGetElement(locator, 5000).click();
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();
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
