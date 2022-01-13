package com.qa.api.gorest.tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.pojo.User;
import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.restassured.response.Response;

public class DeleteUserTest {

	String baseUri = "https://gorest.co.in";
	String basePath = "/public/v1/users";
	String token = "90d7c227d056a668554d486727c22eb34ac99997b70607e35cd8b863740b3adc";

	/**
	 * deletes user based on its id. id is passed as a path parameter
	 * 
	 * id is concatenated to basePath
	 * 
	 * @param method
	 */
//	@Test
	public void deletesingleUserAPITest(Method method) 
	{ 
		// Assign id which you want to delete
		String id="1749";
		String tcName = method.getName();
		
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer " + token);

		TestUtil.printOutput("Started TC ===> ", tcName, "-");
		System.out.println("baseUri : " +  baseUri);
		System.out.println("basePath: " +  basePath);
		
		Response response = RestClient.doDelete(baseUri, basePath+"/"+id, 
				authToken, true);

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.prettyPrint()  : "); 
		response.prettyPrint();
		
		if (response.statusCode()==204)
			System.out.println("user has been deleteed sucessfully : " + id);
		if (response.statusCode()==404)
			System.out.println("user did not delete : " + id);
		
		
		TestUtil.printOutput("End of TC ===> ", tcName, "=");
	}

	@Test
	public void deletesingleUser_createAndThenDeleteAPITest(Method method) 
	{ 
		
		// Create user first
		int rndNumber = TestUtil.generateRandomnumber();
		String email = "Samu@gmail.com";
		String updatedEmail = TestUtil.updateEmailWithRandomNumber(email, rndNumber);
		User user = new User("Samu"+rndNumber, updatedEmail, "female",
		"active");
		
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer " + token);

		Response response = RestClient.doPost("JSON", baseUri, basePath, authToken, null, true, user);

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.prettyPrint()  : ");
		response.prettyPrint();
		int id= response.jsonPath().getInt("data.id");
	
		if (response.statusCode()==201)
			System.out.println("New user created successfully : " + id);
		else
			System.out.println("New user did not create : " + id);
			
		
		
		
		// Assign id which you want to delete
//		String id="1749";
		String tcName = method.getName();
		
		TestUtil.printOutput("Started TC ===> ", tcName, "-");
		System.out.println("baseUri : " +  baseUri);
		System.out.println("basePath: " +  basePath);
		
		response = RestClient.doDelete(baseUri, basePath+"/"+id, 
				authToken, true);

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.prettyPrint()  : "); 
		response.prettyPrint();
		
		if (response.statusCode()==204)
			System.out.println("user has been deleted sucessfully : " + id);
		if (response.statusCode()==404)
			System.out.println("user did not delete : " + id);
		
		
		TestUtil.printOutput("End of TC ===> ", tcName, "=");
	}



}
