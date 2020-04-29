package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG {

	WebDriver driver;

	@Test
	public void bai1() throws InterruptedException {
		driver.get("http://automationpractice.com");

		String email = "leducanh.games@gmail.com";
		String password = "123456";

		driver.findElement(By.xpath("//*[@class=\"login\"]")).click();
		
		//*[@id="login_form"]/h3
		
		By locator = By.xpath("//*[@id=\"email\"]");
		waitAndGetElement(locator, 5000).sendKeys(email);
		driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys(password);

		driver.findElement(By.xpath("//*[@id=\"SubmitLogin\"]/span")).click();
		if(!isElementPresent(By.xpath("//div/a[contains(text(),\"Sign out\")]"))) {
			System.out.println("can not login");
		}else {
			System.out.println("Done");
		}
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", ".\\WebDriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
//		driver.close();
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
