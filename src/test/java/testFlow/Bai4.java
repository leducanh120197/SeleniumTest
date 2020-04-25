package testFlow;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai4 {
	WebDriver driver;

	@Test(enabled = true)
	public void f() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
		driver.findElement(By.xpath("//*[@id=\"cricle-btn\"]")).click();
		
		while(!isElementPresent(By.xpath("//div[contains(text(),\"100%\")]"))) {
			System.out.println(driver.findElement(By.xpath("//*[@class=\"percenttext\"]")).getText());
			Thread.sleep(1000);
		}
		if(isElementPresent(By.xpath("//div[contains(text(),\"100%\")]"))) {
			System.out.println("Done: 100%");
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		
		String downloadFilepath = "C:\\Users\\leduc\\OneDrive\\___Code\\SeleniumTest\\Download";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
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
}
