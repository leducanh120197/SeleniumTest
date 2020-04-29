package apitest;

import org.testng.annotations.Test;

//import sun.tools.tree.EqualExpression;

import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

public class Bai1 {
	
	WebDriver driver;
	
  @Test
  public void bai1() {
	  driver.get("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
	  String a = "1";
	  String b = "2";
	  
	  driver.findElement(By.xpath("//*[@id=\"sum1\"]")).sendKeys(a);
	  driver.findElement(By.xpath("//*[@id=\"sum2\"]")).sendKeys(b);
	  driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
	  String total = driver.findElement(By.xpath("//*[@id=\"displayvalue\"]")).getText();
	  
	  String total_rs = Integer.toString(Integer.parseInt(a) + Integer.parseInt(b));
	  assertEquals(total_rs, total, "Expected output [" + total_rs + "] but actual output is [" + total+"]");
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

}
