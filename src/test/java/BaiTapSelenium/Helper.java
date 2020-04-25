package BaiTapSelenium;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.internal.Utils;

import com.opencsv.CSVReader;

public class Helper {
	static WebDriver driver;

	public static List<String[]> readCsv(String url) {
		try {
			Reader reader = new FileReader(url);
			CSVReader csvReader = new CSVReader(reader);
			List<String[]> lines = csvReader.readAll();
			return lines;
		} catch (Exception e) {
			System.out.println("Can't read csv");
		}
		return null;
	}

	public static boolean checkAge(String strYearOld) {
		int yearOld = stringToInt(strYearOld);
		if (yearOld < 0 || yearOld > 50) {
			return false;
		}
		return true;
	}

	public static int stringToInt(String str) {
		return Integer.parseInt(str);
	}

	public static String getValueAge(String strYearOld) {
		int yearOld = stringToInt(strYearOld);
		if (0 < yearOld && yearOld <= 5) {
			return "0 - 5";
		}
		if (5 < yearOld && yearOld <= 15) {
			return "5 - 15";
		}
		if (15 < yearOld && yearOld <= 50) {
			return "15 - 50";
		}
		return null;
	}

	public static boolean isElementPresent(String xPath) {
		try {
			driver.findElement(By.xpath(xPath));
			return true;
		}catch (NoSuchElementException e) {
			System.out.println("EXCEPT " + e);
			return false;
		}
	}
	
	public static WebElement waitAndGetElement(String xPath, long timeOut) {
		for(int i=0; i< timeOut/500;i++) {
			if(isElementPresent(xPath))
				return driver.findElement(By.xpath(xPath));
			else {
				System.out.println("Element not available");
//				Utils.pause(500);
			}
		}
		return null;
	}
	public static String[] getRecords(String str) {
		String[] records = str.split("records");
		records[0] = records[0].replaceAll("[a-z A-Z,]", "");
		records[1] = records[1].replaceAll("[a-z A-Z,]", "");
		return records;
	}
}
