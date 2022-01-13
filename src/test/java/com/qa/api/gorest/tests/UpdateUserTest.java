package com.qa.api.gorest.tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.gorest.listeners.ExtentReportListener;
import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.response.Response;

public class UpdateUserTest {
	String baseUri = "https://gorest.co.in";
	String basePath = "/public/v1/users";
	String token = "90d7c227d056a668554d486727c22eb34ac99997b70607e35cd8b863740b3adc";

	static User user, updatedUser;

	@Test
	public void updateUser_createAndThenDeleteAPITest(Method method) {

		String tcName = method.getName();
		
		TestUtil.printOutput("Started TC ===> ", tcName, "-");
		TestUtil.logToExtentReport("TC started => " + tcName);
		

		// Create user first
		int rndNumber = TestUtil.generateRandomnumber();
		String email = "Siddhi@gmail.com";
		String updatedEmail = TestUtil.updateEmailWithRandomNumber(email, rndNumber);
		TestUtil.logToExtentReport("Updated email created : " + updatedEmail);

		user = new User("Siddhi" + rndNumber, updatedEmail, "female", "active");
		TestUtil.logToExtentReport("User payload created : " + user.toString());

		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer " + token);

		Response response = RestClient.doPost("JSON", baseUri, basePath, authToken, null, true, user);
		TestUtil.logToExtentReport("doPost called");
		TestUtil.logToExtentReport("Response received");

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.prettyPrint()  : ");
		response.prettyPrint();
		int id = response.jsonPath().getInt("data.id");
		TestUtil.logToExtentReport("Validating statuscode =>" + response.statusCode());

		if (response.statusCode() == 201)
		{	System.out.println("New user created successfully : " + id + " name: " + user.getName() + " gender: "
					+ user.getGender() + " : " + user.getStatus());
		TestUtil.logToExtentReport("New user created successfully : \" + id + \" name: \" + user.getName() + \" gender: \"\r\n"
					+ "					+ user.getGender() + \" : \" + user.getStatus()");
		}else
		{
			System.out.println("New user did not create : " + id);
			TestUtil.logToExtentReport("USer did not created => " + id);
		}
		// Assign id which you want to update

		System.out.println("baseUri : " + baseUri);
		System.out.println("basePath: " + basePath);

		// update User for it status
		updateUser();
		TestUtil.logToExtentReport("status is updated to update the user created in prior API");
		
		response = RestClient.doPut("JSON", baseUri, basePath + "/" + id, authToken, null, true, updatedUser);
		TestUtil.logToExtentReport("doPut called");
		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.prettyPrint()  : ");
		response.prettyPrint();
		TestUtil.logToExtentReport("Response received");

		String updatedName = response.jsonPath().getString("data.name");
		String updatedGender = response.jsonPath().getString("data.gender");
		String updatedStatus = response.jsonPath().getString("data.status");
		TestUtil.logToExtentReport("Validating for gender, status");
		Assert.assertEquals(updatedGender, updatedUser.getGender());
		Assert.assertEquals(updatedStatus, updatedUser.getStatus());

		TestUtil.logToExtentReport("Validating name");
		// fail TC deliberately 
		//Assert.assertEquals(updatedName + "FAIL", updatedUser.getName());
		
		TestUtil.logToExtentReport("Validating status code");
		if (response.statusCode() == 200)
			System.out.println("user has been updated sucessfully : " + id + " name " + updatedName + " gender: "
					+ updatedGender + " status: " + updatedStatus);
		if (response.statusCode() == 404)
			System.out.println("user did not update : " + id);

		TestUtil.printOutput("End of TC ===> ", tcName, "=");
		TestUtil.logToExtentReport("Test Case compelted");
	}

	/**
	 * Update user for gender and status for User object and stored changes in
	 * updatedUser
	 */
	private static void updateUser() {
		try {
			updatedUser = (User) user.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Something has gone wrong in cloning from user to UpdatedUser !!!");
			e.printStackTrace();
		}

		updatedUser.setName(updatedUser.getName() + "NEW");
		updatedUser.setStatus("inactive");
		updatedUser.setGender("male");
	}
}
