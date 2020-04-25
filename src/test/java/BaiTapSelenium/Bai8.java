package BaiTapSelenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai8 {
	WebDriver driver;

	@Test (enabled = false)
	public void printNewYork() {
		driver.get("https://www.seleniumeasy.com/test/table-data-download-demo.html");
		List<WebElement> rowsTable = new ArrayList<WebElement>();
//		List<WebElement> columnsRow = new ArrayList<WebElement>();	
		
		rowsTable = driver.findElements(By.xpath("//tr[td[contains(text(), \"New York\")]]"));
		for (int row = 0; row < rowsTable.size(); row++) {
			System.out.println(rowsTable.get(row).findElements(By.tagName("td")).get(0).getText());
//				columnsRow = rowsTable.get(row).findElements(By.tagName("td"));
//				System.out.println(columnsRow.get(0).getText());
		}
//			Thread.sleep(500);
		

	}
	
	@Test (enabled = true)
	public void printTokyo() {
		driver.get("https://www.seleniumeasy.com/test/table-data-download-demo.html");
		List<WebElement> rowsTable = new ArrayList<WebElement>();
		List<WebElement> columnsRow = new ArrayList<WebElement>();	
		
		rowsTable = driver.findElements(By.xpath("//tr[td[contains(text(), \"Accountant\")]]"));
		for (int row = 0; row < rowsTable.size(); row++) {
			columnsRow = rowsTable.get(row).findElements(By.tagName("td"));
			if(!compareStr(columnsRow.get(2).getText(), "Tokyo")) {
				System.out.println(columnsRow.get(1).getText() + " - " + columnsRow.get(2).getText());
			}
		}
//			Thread.sleep(500);
		

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
	
	public boolean compareStr(String str1, String str2) {
		if(str1 == str2) {
			return true;
		}
		return false;
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
