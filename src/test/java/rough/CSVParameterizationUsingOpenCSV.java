package rough;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CSVParameterizationUsingOpenCSV {

	
	@Test(dataProvider="getData")
	public void test001(String name, String email, String gender, String status)
	{
		System.out.println(name + "  :: " + email + "  :: " + gender + "  :: " + status);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object data[][] = getCSVData("/src/main/java/com/qa/api/gorest/testdata/createUserGorest.csv",true,",");
		return data;
	}
	
	public Object[][] getCSVData(String csvFile, boolean header, String delimiter)
	{
		Object[][] data = null;
		try {
			  csvFile = System.getProperty("user.dir")  +csvFile;

			  File file = new File(csvFile);
		      FileReader fr = new FileReader(file);
		      BufferedReader br = new BufferedReader(fr);
		      String line = " ";
		 
		      // find total rows
		      Path path = Paths.get(csvFile);
		      int totalRows = (int) Files.lines(path).count();
		      if(header)
		      {
		    	  totalRows--;
		    	  line = br.readLine();  // skip this line for below while - read and store csv values from file
		      }
		      
		      
		      int totalColumns=0;
		      int lineCount = 0;
		      String[] tempArr;
		      while ((line = br.readLine()) != null) 
		      {
		        tempArr = line.split(delimiter);
			    // find total columns
		    	if (lineCount==0)
		    	{
		    		totalColumns=tempArr.length;
		    		data = new Object[totalRows][totalColumns];
		    	}
		    	
		    	// store csv values in Object array
		    	for(int c=0; c<tempArr.length;c++)
		    		data[lineCount][c] = tempArr[c];

		    	lineCount++;
		      }
		      br.close();
		    }
		    catch(IOException ioe) {
		      ioe.printStackTrace();
		    }	
		return data;
	}
	
	
	public void getParameters()
	{
	
		String csvFile = "E:/NAL/Nov2019RestAssuredFramework/src/main/java/com/qa/api/gorest/testdata/createUserGorest.csv";
		try {
			  String delimiter=",";
		      File file = new File(csvFile);
		      FileReader fr = new FileReader(file);
		      BufferedReader br = new BufferedReader(fr);
		      String line = " ";
		      boolean header=false;
		      if(header)
		    	  line = br.readLine();
		      
		      String[] tempArr;
		      while ((line = br.readLine()) != null) 
		      {
		        tempArr = line.split(delimiter);
		        for (String tempStr: tempArr) {
		          System.out.print(tempStr + " ");
		        }
		        System.out.println();
		      }
		      br.close();
		    }
		    catch(IOException ioe) {
		      ioe.printStackTrace();
		    }	
		}
	
	
}
