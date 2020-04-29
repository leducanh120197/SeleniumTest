package testFlow;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestFlow {
	WebDriver driver;

	@Test(enabled = true)
	public void bai1() throws InterruptedException {

		driver.get("https://www.seleniumeasy.com/test/jquery-download-progress-bar-demo.html");
		driver.findElement(By.xpath("//*[@id=\"downloadButton\"]")).click();

		String complate = waitAndGetElement(By.xpath("//*[@id=\"dialog\"]/div[contains(text(),\"Complete!\")]"), 10000)
				.getText();
		try {
			driver.findElement(By.xpath("//button[contains(text(), \"Close\")]")).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Test(enabled = false)
	public void bai2() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html");

		driver.findElement(By.xpath("//*[@id=\"save\"]")).click();
		;
		String x = waitAndGetElement(By.xpath("//*[@id=\"loading\"][contains(text(),\"First\")]"), 10000).getText();
		System.out.println(x);
	}

	@Test(enabled = false)
	public void bai3() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/bootstrap-progress-bar-dialog-demo.html");
		driver.findElement(By.xpath("//button[@class=\"btn btn-warning\"]")).click();

		for (int i = 0; i < 10000; i += 1000) {
			if (isElementPresent(By.xpath("//div[@class=\"modal fade in\"]"))) {
				System.out.println("alert");
			} else {
				System.out.println("not alert");
			}
			Thread.sleep(1000);
		}
	}

	@Test(enabled = false)
	public void bai4() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
		driver.findElement(By.xpath("//*[@id=\"cricle-btn\"]")).click();

		while (!isElementPresent(By.xpath("//div[contains(text(),\"100%\")]"))) {
			System.out.println(driver.findElement(By.xpath("//*[@class=\"percenttext\"]")).getText());
			Thread.sleep(1000);
		}
		if (isElementPresent(By.xpath("//div[contains(text(),\"100%\")]"))) {
			System.out.println("Done: 100%");
		}
	}

	@Test(enabled = false)
	public void bai5() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/");
		String handleParent = driver.getWindowHandle();
		System.out.println(handleParent);
		driver.findElement(By.xpath("//*[@id=\"navbar-brand-centered\"]/ul/li/a[contains(text(),\"Alerts & Modals\")]"))
				.click();
		Thread.sleep(500);
		driver.findElement(
				By.xpath("//*[@id=\"navbar-brand-centered\"]/ul/li/ul/li/a[contains(text(), \"File Download\")]"))
				.click();

		Thread.sleep(1000);
//		driver.findElement(By.cssSelector("hml/body")).sendKeys(Keys.CONTROL + "t");
		((JavascriptExecutor) driver).executeScript("window.open('https://www.stackoverflow.com','_blank');");
		String content = "";

		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(handleParent)) {
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
		String filePath = "C:\\Users\\LQA\\Downloads\\easyinfo.txt";
		String dataInFile = Helper.readFile(filePath);
		System.out.println(content);
		System.out.println(dataInFile);
		assertEquals(content, dataInFile);

//		driver.close();
	}

	@BeforeMethod
	public void beforeMethod() {
//		System.setProperty("webdriver.chrome.driver", ".\\WebDriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\WebDriver\\chromedriver.exe");

		String downloadFilepath = "C:\\Users\\LQA\\OneDrive\\___Code\\SeleniumTest\\Download";
		
//		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", downloadFilepath);

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(cap);

//		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
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
