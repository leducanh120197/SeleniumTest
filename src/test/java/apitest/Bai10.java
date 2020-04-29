package apitest;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai10 {
	WebDriver driver;

	@Test(enabled = true)
	public void f() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/table-pagination-demo.html");
		
		String strRecords = driver.findElement(By.xpath("//div[2]/div/div[2]/p[1]")).getText();
		String[] listRecords = Helper.getRecords(strRecords);
		
		By locatorNext = By.xpath("//a[@class=\"next_link\" and not(@style)]");
		List<WebElement> rowsTable = new ArrayList<WebElement>();	
		int maxRecords = 0;
		int totalRecords = 0;

		while (true) {
			rowsTable = driver.findElements(By.xpath("//*[@id=\"myTable\"]/tr[@style=\"display: table-row;\"]"));
			if(rowsTable.size() > maxRecords) {
				maxRecords = rowsTable.size();
			}		
			totalRecords += rowsTable.size();
			if(isElementPresent(locatorNext)) {
				driver.findElement(locatorNext).click();
			}else {
				break;
			}
		}
		assertEquals(listRecords[0], totalRecords);
		assertEquals(listRecords[1], maxRecords);
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
