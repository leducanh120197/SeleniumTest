package apitest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai6 {
	WebDriver driver;

	@Test (enabled = true)
	public void f2() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
		By locatorNext = By.xpath("//*[@class=\"paginate_button next\"]");
		List<WebElement> rowsTable = new ArrayList<WebElement>();
//		List<WebElement> columnsRow = new ArrayList<WebElement>();	
		
		while (isElementPresent(locatorNext)) {
			rowsTable = driver.findElements(By.xpath("//tr[td[contains(text(), \"London\")]]"));
			for (int row = 0; row < rowsTable.size(); row++) {
				System.out.println(rowsTable.get(row).findElements(By.tagName("td")).get(0).getText());
//				columnsRow = rowsTable.get(row).findElements(By.tagName("td"));
//				System.out.println(columnsRow.get(0).getText());
			}
//			Thread.sleep(500);
			driver.findElement(locatorNext).click();
		}
	}
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\leduc\\OneDrive\\___Code\\WebDriver\\chromedriver.exe");
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
}
