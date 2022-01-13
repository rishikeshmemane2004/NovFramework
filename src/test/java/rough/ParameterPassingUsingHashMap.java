package rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParameterPassingUsingHashMap {

	@Test(dataProvider = "getData")
	public void test001(Map<String, String> paramMap) {
		System.out.println(
				paramMap.get("name") + paramMap.get("email") + paramMap.get("gender") + paramMap.get("status"));
	}

	@DataProvider
	public Object[][] getData() {

		Workbook book;

		String TESTDATA_SHEET_PATH = "E:\\NAL\\Nov2019RestAssuredFramework\\src\\test\\java\\rough\\userdata.xlsx";

		Sheet sheet = null;
		String sheetName = "userdata";
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
