package com.qa.api.gorest.tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.gorest.listeners.ExtentReportListener;
import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.ExcelUtil;
import com.qa.api.gorest.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("Epic: Create New User")
@Feature("Feature: Creation of new users with different options - csv, excel, hard code data, dynamic data")
public class CreateUserTest {

	String baseUri  = "https://gorest.co.in";
	String basePath = "/public/v1/users";
	String token    = "90d7c227d056a668554d486727c22eb34ac99997b70607e35cd8b863740b3adc";
 
	Map<String, String> tokenMap;
	Map<String, String> authToken ;
	
	@BeforeMethod
	public void setUp()
	{
		authToken = new HashMap<String, String>();
		authToken.put("authorization", "Bearer " + token);
		System.out.println("before method flag +++++++++++++++++> " + ExtentReportListener.flag);
		//can't execute below statement as - Before method gets execued prior to listern's onStartMethod where test is initialized with createTest() method
		//TestUtil.logToExtentReport("access Token created => " +  authToken.get("authorization"));
 		
 		System.out.println("accessToken===============>" + authToken.get("authorization"));
 	}
	
	@Test(dataProvider = "getUserData")
	@Description("Creation of new user using dataprovider")
	@Severity(SeverityLevel.BLOCKER)
	public void createUserAPIPOSTTest(Method method, String name, String email, String gender, String status) {
		String tcName = "createUserAPIPOSTTest"; // method.getName();
		
		TestUtil.logToExtentReport("TC started => " + tcName);
		TestUtil.printOutput("Started TC ===> ", tcName, "-");
		TestUtil.logToExtentReport("access Token created => " +  authToken.get("authorization"));

		int rndNumber = TestUtil.generateRandomnumber();
		// User user = new User("Samu"+rndNumber, "Samu"+rndNumber+"@gmail", "female",
		// "active");

		System.out.println(name + " :: " + email + " :: " + gender + " :: " + status);

		// using data provider
		String updatedEmail = TestUtil.updateEmailWithRandomNumber(email, rndNumber);
		User user = new User(name + rndNumber, updatedEmail, gender, status);
		TestUtil.logToExtentReport("User Object created");

		Response response = RestClient.doPost("JSON", baseUri, basePath, authToken, null, true, user);
		TestUtil.logToExtentReport("Hit Create User API");

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.prettyPrint()  : ");
		response.prettyPrint();
		TestUtil.logToExtentReport("Recevied Response ");

		TestUtil.printOutput("End of TC ===> ", tcName, "-");
		TestUtil.logToExtentReport("TC Over ");

	}

	@Test(dataProvider = "getUserDataUsingHashMap")
	@Description("Creation of new user using hashmap dataprovider ")
	@Severity(SeverityLevel.CRITICAL)
	public void createUserAPIPOSTUsingHashMapTest(Method method, Map<String, String> map) {
		String tcName = method.getName();

		TestUtil.printOutput("Started TC ===> ", tcName, "-");
 		int rndNumber = TestUtil.generateRandomnumber();

		//System.out.println(map.get("name") + " :: " + map.get("email") + " :: " + map.get("gender") + " :: " + map.get("status"));
		String name   = map.get("name") ;
		String email  = map.get("email") ;
		String gender = map.get("gender") ;
		String status = map.get("status") ;
		String updatedEmail = TestUtil.updateEmailWithRandomNumber(email, rndNumber);
		
		// using data provider String updatedEmail =
		TestUtil.updateEmailWithRandomNumber(email, rndNumber);
		User user = new User(name + rndNumber, updatedEmail, gender, status);
		Response response = RestClient.doPost("JSON", baseUri, basePath, authToken, null, true, user);

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.prettyPrint()  : ");
		response.prettyPrint();

		TestUtil.printOutput("End of TC ===> ", tcName, "-");
	}

// dataprovider using hashMap
	@DataProvider()
	public Object[][] getUserDataUsingHashMap() {
		System.out.println("*****************");
		// Read xlsx file and put it in map
		Object[][] userData = ExcelUtil.getTestDataUsingHashMap("userdata",
				"/src/main/java/com/qa/api/gorest/testdata/createUserGorest.xlsx");
		System.out.println("==================");
		return userData;
	}
 
 	
	@DataProvider()
	public Object[][] getUserData() {
		// Read xlsx file
		Object[][] userData = ExcelUtil.getTestData("userdata",
				"/src/main/java/com/qa/api/gorest/testdata/createUserGorest.xlsx");

// Get data from CSV
		// Object userData[][] =
		// TestUtil.getCSVData("/src/main/java/com/qa/api/gorest/testdata/createUserGorest.csv",true,",");

/*
// Generate Fake data using JAvaFaker library Object[][] userData = new
		 * Object[4][4]; Faker faker = new Faker();
		 * 
		 * for(int i=0;i<4;i++) { userData[i][0] = faker.name().firstName();
		 * userData[i][1] = faker.internet().emailAddress(); userData[i][2] =
		 * faker.demographic().sex(); if (TestUtil.generateRandomnumber() % 2 ==0)
		 * userData[i][3] = "active"; else userData[i][3] = "inactive"; }
*/
/*
		 * below code does not work giving error related classNotFound or
		 * ClassDefNotFound // Generate Fake data using jFairy library Object[][]
		 * userData = new Object[4][4]; Fairy fairy = Fairy.create();
		 * 
		 * for(int i=0;i<4;i++) { userData[i][0] = fairy.person().getFullName();
		 * userData[i][1] = fairy.person().getEmail(); userData[i][2] =
		 * fairy.person().getSex(); if (TestUtil.generateRandomnumber() % 2 ==0)
		 * userData[i][3] = "active"; else userData[i][3] = "inactive"; }
*/

		return userData;
	}
	 
 
}
