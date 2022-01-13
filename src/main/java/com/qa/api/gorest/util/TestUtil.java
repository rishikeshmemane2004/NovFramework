package com.qa.api.gorest.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.gorest.listeners.ExtentReportListener;
 
public class TestUtil {

	/**
	 * logs test step to the extent report
	 * @param message
	 */
	public static void logToExtentReport(String message)
	{
	//	ExtentReportListener.test.get().createNode(message);
		ExtentReportListener.test.get().log(Status.INFO, message);
		
	}
	/**
	 * This method is used to convert popjo  object to JSON string
	 * 
	 * @param obj
	 * @return JSON String
	 */
	public static String getSerializedJSON(Object obj)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		String jSonString=null;
		try {
			jSonString = mapper.writeValueAsString(obj);
			System.out.println("JSon bodypayload: "+jSonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jSonString;
	}
	/**
	 * Helps to print header and footer in the begining and at the end of the test case
	 * @param label
	 * @param tcName
	 * @param prefix
	 */
	public static void printOutput(String label, String tcName, String prefix)
	{
		int totalDashToPrint = 50;
		System.out.println("\n"+prefix.repeat(totalDashToPrint));
//		System.out.println("\n"+"------------------------------");
// repeat function is present from java 11 so ensure to have Java 11 or onwards
		System.out.println(label + tcName);
		System.out.println(prefix.repeat(totalDashToPrint));
//		System.out.println("\n"+"------------------------------");
		 
	}
	
	/**
	 * returns random number generated between 0 to 1000000 
	 * @return
	 */
	public static int generateRandomnumber()
	{
		Random random = new Random();
		return random.nextInt(1000000);
	}
	
	public static String updateEmailWithRandomNumber(String email, int rndNumber)
	{
		String updatedEmail = email.split("@")[0]  + rndNumber + "@" + email.split("@")[1]; 
		return updatedEmail;
	}
	
	/**
	 * Gets CSV data from a CSV file
	 * 
	 * @param csvFile
	 * @param header
	 * @param delimiter
	 * @return Object array containing CSV values
	 */
	
	public static Object[][] getCSVData(String csvFile, boolean header, String delimiter)
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
}
