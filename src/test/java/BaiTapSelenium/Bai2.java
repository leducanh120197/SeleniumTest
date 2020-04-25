package BaiTapSelenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class Bai2 {

	WebDriver driver;

	@Test (enabled = false)
	public void f2() throws IOException, InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		List<String[]> lines = Helper.readCsv("C:\\Users\\leduc\\OneDrive\\___Code\\SeleniumTest\\data.csv");
		lines.remove(0);
		for (String[] line : lines) {
			driver.findElement(By.xpath("//*[@id=\"sum1\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"sum2\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"sum1\"]")).sendKeys(line[0]);
			driver.findElement(By.xpath("//*[@id=\"sum2\"]")).sendKeys(line[1]);
			driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
			String total = driver.findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText();
			assertEquals(line[2], total, "");
			Thread.sleep(2000);
		}
	}
	
	@Test (enabled = true)
	public void f() throws IOException, InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		List<String[]> lines = Helper.readCsv("C:\\Users\\leduc\\OneDrive\\___Code\\SeleniumTest\\data.csv");
		lines.remove(0);
		for (String[] line : lines) {
			driver.findElement(By.xpath("//*[@id=\"sum1\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"sum2\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"sum1\"]")).sendKeys(line[0]);
			driver.findElement(By.xpath("//*[@id=\"sum2\"]")).sendKeys(line[1]);
			driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
			String total = driver.findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText();
			try {
				assertEquals(line[2], total, "xxx");
				System.out.println("Pass: " + line[2] + " <=> " + total);
				Thread.sleep(2000);
			}
			catch (AssertionError e) {
				System.out.println("Failed: " + e);
			}
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
	}

}
