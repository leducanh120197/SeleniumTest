package apitest;

import org.testng.annotations.Test;

//import sun.tools.tree.EqualExpression;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class SeleniumAPI {

	WebDriver driver;

	@Test(enabled = true)
	public void bai1() {
		driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		String a = "1";
		String b = "2";

		driver.findElement(By.xpath("//*[@id=\"sum1\"]")).sendKeys(a);
		driver.findElement(By.xpath("//*[@id=\"sum2\"]")).sendKeys(b);
		driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
		String total = driver.findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText();

		String total_rs = Integer.toString(Integer.parseInt(a) + Integer.parseInt(b));
		assertEquals(total_rs, total, "Expected output [" + total_rs + "] but actual output is [" + total + "]");
	}

	@Test(enabled = false)
	public void bai2() throws IOException, InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
		List<String[]> lines = Helper.readCsv(".\\Download\\data.csv");
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
			} catch (AssertionError e) {
				System.out.println("Failed: " + e);
			}
		}
	}

	@Test(enabled = false)
	public void bai3() {
		driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
		driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[1]/label[1]/input")).click();
		;
		driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/div[2]/label[2]/input")).click();
		;

		driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/button")).click();

		String rs = driver.findElement(By.xpath("//*[@id=\"easycont\"]/div/div[2]/div[2]/div[2]/p[2]")).getText();
		System.out.println(rs);

	}

	@Test(enabled = false)
	public void bai4() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
		List<String[]> lines = Helper.readCsv(".\\Download\\data_bai4.csv");
		lines.remove(0);
		for (String[] line : lines) {
			if (Helper.checkAge(line[1])) {
				driver.findElement(By.xpath("//*[@value=\"" + Helper.getValueAge(line[1]) + "\"]")).click();
				driver.findElement(By.xpath("//*[@value=\"" + line[0] + "\" and @name=\"gender\"]")).click();
				driver.findElement(By.xpath("//button[contains(text(), \"Get values\")]")).click();
				String strGetText = driver.findElement(By.xpath("//*[@class=\"groupradiobutton\"]")).getText();

				String Result = "Sex : " + line[0] + "\n" + "Age group: " + Helper.getValueAge(line[1]);
				try {
					assertEquals(Result, strGetText, "xxx");
					System.out.println("Pass: " + Result + " <=> \n" + strGetText);
					System.out.println("----------------");
					Thread.sleep(2000);
				} catch (AssertionError e) {
					System.out.println("Failed: " + e);
				}
			}
		}
	}

	@Test(enabled = false)
	public void bai5() throws InterruptedException {
		driver.get("http://demo.guru99.com/test/drag_drop.html");

		WebElement From = driver.findElement(By.xpath("//*[@id=\"credit2\"]/a"));
		WebElement To = driver.findElement(By.xpath("//*[@id=\"bank\"]/li"));
		Thread.sleep(1000);

		Actions act = new Actions(driver);
		act.dragAndDrop(From, To).build().perform();
	}

	@Test(enabled = false)
	public void bai6() throws InterruptedException {
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

	@Test(enabled = false)
	public void bai7() throws InterruptedException {
		driver.get("http://automationpractice.com");
		
		String email = "leducanh.games@gmail.com";

		
		driver.findElement(By.xpath("//*[@class=\"login\"]")).click();
		By locator = By.xpath("//*[@id=\"email_create\"]");
		waitAndGetElement(locator, 5000).sendKeys(email);
		
		driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]/span")).click();

		System.out.println(waitAndGetElement(By.xpath("//h3[contains(text(), \"Your personal info\")]"), 5000).getText());
		
		if (isElementPresent(By.xpath("//h3[contains(text(), \"Your personal info\")]"))) {
//			Select select = new Select(null);
			
			driver.findElement(By.xpath("//*[@id=\"id_gender1\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys("customer firstname");
			driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys("customer lastname");
//			driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
			driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("123456");

			driver.findElement(By.xpath("//*[@id=\"days\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"days\"]/option[13]")).click();
//			Select select = new Select(driver.findElement(By.xpath("//*[@id=\"days\"]")));		
//			select.selectByVisibleText("1");

			driver.findElement(By.xpath("//*[@id=\"months\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"months\"]/option[2]")).click();
//			select = new Select(driver.findElement(By.xpath("//*[@id=\"months\"]")));
//			select.deselectByVisibleText("January");

			driver.findElement(By.xpath("//*[@id=\"years\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"years\"]/option[25]")).click();
//			select = new Select(driver.findElement(By.xpath("//*[@id=\"years\"]")));
//			select.deselectByVisibleText("1997");

			driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("firstname");
			driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("lastname");
			driver.findElement(By.xpath("//*[@id=\"company\"]")).sendKeys("company");
			driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("address1");
			driver.findElement(By.xpath("//*[@id=\"address2\"]")).sendKeys("address2");
			driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("city");

			driver.findElement(By.xpath("//*[@id=\"id_state\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"id_state\"]/option[2]")).click();
//			select = new Select(driver.findElement(By.xpath("//*[@id=\"id_state\"]")));
//			select.deselectByVisibleText("Alaska");
//
			driver.findElement(By.xpath("//*[@id=\"postcode\"]")).sendKeys("10000");

			driver.findElement(By.xpath("//*[@id=\"id_country\"]")).click();
			driver.findElement(By.xpath("//*[@id=\"id_country\"]/option[2]")).click();
//			select = new Select(driver.findElement(By.xpath("//*[@id=\"id_country\"]")));
//			select.deselectByVisibleText("United States");
			
			driver.findElement(By.xpath("//*[@id=\"other\"]")).sendKeys("other");
			driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("0929292929");
			driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).sendKeys("0929292929");
			driver.findElement(By.xpath("//*[@id=\"alias\"]")).sendKeys("alias");
			
			try {
				driver.findElement(By.xpath("//*[@id=\"submitAccount\"]/span")).click();
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("EXCEPT");
			}
			
		}

	}

	@Test(enabled = false)
	public void bai8PrintNewYork() {
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

	@Test(enabled = false)
	public void bai9() {
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

	@Test(enabled = false)
	public void bai8PrintTokyo() {
		driver.get("https://www.seleniumeasy.com/test/table-data-download-demo.html");
		List<WebElement> rowsTable = new ArrayList<WebElement>();
		List<WebElement> columnsRow = new ArrayList<WebElement>();

		rowsTable = driver.findElements(By.xpath("//tr[td[contains(text(), \"Accountant\")]]"));
		for (int row = 0; row < rowsTable.size(); row++) {
			columnsRow = rowsTable.get(row).findElements(By.tagName("td"));
			if (!compareStr(columnsRow.get(2).getText(), "Tokyo")) {
				System.out.println(columnsRow.get(1).getText() + " - " + columnsRow.get(2).getText());
			}
		}
//			Thread.sleep(500);

	}

	@Test(enabled = false)
	public void bai10() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/table-pagination-demo.html");

		String strRecords = driver.findElement(By.xpath("//div[2]/div/div[2]/p[1]")).getText();
		String[] listRecords = Helper.getRecords(strRecords);

		By locatorNext = By.xpath("//a[@class=\"next_link\" and not(@style)]");
		List<WebElement> rowsTable = new ArrayList<WebElement>();
		int maxRecords = 0;
		int totalRecords = 0;

		while (true) {
			rowsTable = driver.findElements(By.xpath("//*[@id=\"myTable\"]/tr[@style=\"display: table-row;\"]"));
			if (rowsTable.size() > maxRecords) {
				maxRecords = rowsTable.size();
			}
			totalRecords += rowsTable.size();
			if (isElementPresent(locatorNext)) {
				driver.findElement(locatorNext).click();
			} else {
				break;
			}
		}
		assertEquals(listRecords[0], totalRecords);
		assertEquals(listRecords[1], maxRecords);
	}

	@Test(enabled = false)
	public void bai11() {
		driver.get("https://www.seleniumeasy.com/test/table-search-filter-demo.html");
		driver.findElement(By.xpath("//*[@id=\"task-table-filter\"]")).sendKeys("Se");

		List<WebElement> rowsTable = driver.findElements(By.xpath("//*[@id=\"task-table\"]/tbody/tr[not(@style)]"));

		for (int i = 0; i < rowsTable.size(); i++) {
			if (isElementPresent(
					By.xpath("//*[@id=\"task-table\"]/tbody/tr[not(@style)]/td[contains(text(), \"Se\")]"))) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}
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

	public boolean compareStr(String str1, String str2) {
		if (str1 == str2) {
			return true;
		}
		return false;
	}

}
