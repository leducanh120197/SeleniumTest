package BaiTapSelenium;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;

public class Bai5 {
	WebDriver driver;

	@Test
	public void f() throws InterruptedException {
		driver.get("http://demo.guru99.com/test/drag_drop.html");

		WebElement From = driver.findElement(By.xpath("//*[@id=\"credit2\"]/a"));
		WebElement To = driver.findElement(By.xpath("//*[@id=\"bank\"]/li"));
		Thread.sleep(1000);
		
		Actions act = new Actions(driver);
		act.dragAndDrop(From, To).build().perform();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\chromedriver.exe");
		driver = new ChromeDriver();
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\leduc\\OneDrive\\___Code\\WebDriver\\geckodriver.exe");
//		driver = new FirefoxDriver();
	}

	@AfterMethod
	public void afterMethod() {
//		  driver.close();
	}

}
