package testFlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Helper {

	public static String readFile(String filePath) {
		String data = "";
		try {
		      File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        data = myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return data;
	}
}
