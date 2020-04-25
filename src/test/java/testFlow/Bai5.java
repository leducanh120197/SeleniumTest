package testFlow;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.JavascriptExecutor;

public class Bai5 {
	WebDriver driver;
	WebDriver driver2;
	

	@Test(enabled = true)
	public void f() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/");
		String handleParent = driver.getWindowHandle();
		System.out.println(handleParent);
		driver.findElement(By.xpath("//*[@id=\"navbar-brand-centered\"]/ul/li/a[contains(text(),\"Alerts & Modals\")]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id=\"navbar-brand-centered\"]/ul/li/ul/li/a[contains(text(), \"File Download\")]")).click();
		
		Thread.sleep(1000);
//		driver.findElement(By.cssSelector("hml/body")).sendKeys(Keys.CONTROL + "t");
		((JavascriptExecutor) driver).executeScript("window.open('https://www.stackoverflow.com','_blank');");
		String content = "";
		
		Set<String> handles = driver.getWindowHandles();
		for(String handle: handles) {
			if(!handle.equals(handleParent)) {
				driver.switchTo().window(handle);
				content = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h1")).getText();
			}
		}
		
		Thread.sleep(1000);
		driver.switchTo().window(handleParent);
		driver.findElement(By.xpath("//*[@id=\"textbox\"]")).sendKeys(content);
		driver.findElement(By.xpath("//*[@id=\"create\"]")).click();
		waitAndGetElement(By.xpath("//*[@id=\"link-to-download\"]"), 5000).click();
		
		Thread.sleep(2000);
		String filePath = "C:\\\\Users\\\\leduc\\\\Downloads\\\\easyinfo.txt";
		String dataInFile = Helper.readFile(filePath);
		System.out.println(content);
		System.out.println(dataInFile);
		assertEquals(content, dataInFile);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
		String downloadFilepath = "C:\\Users\\leduc\\OneDrive\\___Code\\SeleniumTest\\Download";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		
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
