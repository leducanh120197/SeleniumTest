package testNG;

import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;

public class Helper {

	public static List<Book> readBooksFromExcelFile(String excelFilePath) throws IOException {
	    List<Book> listBooks = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	    Workbook workbook = new XSSFWorkbook(inputStream);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    Iterator<Row> iterator = firstSheet.iterator();
	 
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Book aBook = new Book();
	 
//	        while (cellIterator.hasNext()) {
//	            Cell nextCell = cellIterator.next();
//	            int columnIndex = nextCell.getColumnIndex();
//	 
//	            switch (columnIndex) {
//	            case 1:
//	                aBook.setTitle((String) getCellValue(nextCell));
//	                break;
//	            case 2:
//	                aBook.setAuthor((String) getCellValue(nextCell));
//	                break;
//	            case 3:
//	                aBook.setPrice((double) getCellValue(nextCell));
//	                break;
//	            }
	        
	        while (iterator.hasNext()) {
	            while (cellIterator.hasNext()) {
	                Cell cell = cellIterator.next();
	 
	 
	        }
	        listBooks.add(aBook);
	    }
	 
	    workbook.close();
	    inputStream.close();
	 
	    return listBooks;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
