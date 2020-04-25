package BaiTapSelenium;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Bai4 {
	WebDriver driver;

	@Test
	public void f() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
		List<String[]> lines = Helper.readCsv("C:\\Users\\leduc\\OneDrive\\___Code\\SeleniumTest\\data_bai4.csv");
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

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterMethod() {
	}
}
