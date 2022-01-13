package com.qa.api.gorest.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	/**
	 * Collects data from Excel file
	 * 
	 * @param sheetName
	 * @param filePathName
	 * @return 2 dimensional object containing excel cell values
	 */
	public static Object[][] getTestData(String sheetName, String filePathName) {

		Workbook book;
		XSSFWorkbook book1;
		
		String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + filePathName;

		Sheet sheet = null;

		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			//book = WorkbookFactory.create(fis);
			book1 = new XSSFWorkbook(fis);
			sheet = book1.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int totalNoOfRows = sheet.getLastRowNum();
		int totalNoOfColumns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[totalNoOfRows][totalNoOfColumns];

		for (int i = 0; i < totalNoOfRows; i++) {
			for (int j = 0; j < totalNoOfColumns; j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}

	public static Object[][] getTestDataUsingHashMap(
			String sheetName, String filePathName) {
		System.out.println("Started getTestDataUsingHashMap() ---->");
		Workbook book;
		XSSFWorkbook book1;
		
		String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + filePathName;
		Sheet sheet = null;
		System.out.println("TESTDATA_SHEET_PATH: " + TESTDATA_SHEET_PATH);
		
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			//book = WorkbookFactory.create(fis);
			book1 = new XSSFWorkbook(fis);
			System.out.println("book object created....");
			sheet = book1.getSheet(sheetName);
			System.out.println("sheet object created....");
		} catch (FileNotFoundException e) {
			System.out.println("#1 File Not found");
			//e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			System.out.println("#1 EncryptedDocument found");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("#1 IO Exception found");
			//e.printStackTrace();
		}catch (Exception e) {
			System.out.println("#5 defautl exception found");
			System.out.println("inside Exception block");
			//e.printStackTrace();
		}
		
		System.out.println("After try block....");
		int totalNoOfRows    = sheet.getLastRowNum();
		int totalNoOfColumns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[totalNoOfRows][1];

		for (int i = 0; i < totalNoOfRows; i++) {
			Map<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < totalNoOfColumns; j++)
				map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
			data[i][0] = map;
		}
		return data;
	}
}