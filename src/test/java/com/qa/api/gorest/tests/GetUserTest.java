package com.qa.api.gorest.tests;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.TestUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

/**
 * 
 * @author Rishikesh
 *
 */
@Epic("Get User go rest api feature implentation")
@Feature("get user api feature")
public class GetUserTest {

	String baseUri = "https://gorest.co.in";
	String basePath = "/public-api/users";
	String token = "90d7c227d056a668554d486727c22eb34ac99997b70607e35cd8b863740b3adc";

	/**
	 * This test will get all users for get call and validate total count, code
	 */
	@Test
	@Description("Get All API User list")
	@Severity(SeverityLevel.CRITICAL)
	public void getAllUserListAPITest(Method method) 
	{ 
		
		String tcName = method.getName();
		
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer " + token);
		TestUtil.printOutput("Started TC ===> ", tcName, "-");
		System.out.println("baseUri : " +  baseUri);
		System.out.println("basePath: " +  basePath);
		
		Response response = RestClient.doGet("JSON", baseUri, basePath, authToken, null, true);

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.prettyPrint()  : "); 
		response.prettyPrint();

		TestUtil.printOutput("End of TC ===> ", tcName, "=");

	}
	
	@Test
	@Description("Get All Users with Query Parameters")
	@Severity(SeverityLevel.NORMAL)
	public void getUserListWithQueryParamsAPITest(Method method) 
	{ 
		String tcName = method.getName();
		
		TestUtil.printOutput("Started TC ===> ", tcName, "-");
		Map<String, String> authToken = new HashMap<String, String>();
		authToken.put("Authorization", "Bearer " + token);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("status", "active");
		params.put("name", "Vedant");
		params.put("gender", "male");
		
		Response response = RestClient.doGet("json", baseUri, basePath, authToken, params, true);

		System.out.println("response.getStatusCode(): " + response.getStatusCode());
		System.out.println("response.getStatusLine(): " + response.getStatusLine());
		System.out.println("response.getTime()      : " + response.getTime());
		System.out.println("response.prettyPrint()  : ");
		response.prettyPrint();

		TestUtil.printOutput("End of TC ===> ", tcName, "=");
	}
}
