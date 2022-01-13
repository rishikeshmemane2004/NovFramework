package com.qa.api.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.gorest.restclient.RestClient;
import com.qa.api.gorest.util.Token;

import io.restassured.response.Response;

public class GetImgurAPITest {

	Map<Object, Object> tokenMap;
	String accessToken ;
	String accountUserName;
	String refreshToken;
	
	@BeforeMethod
	public void setUp()
	{
		tokenMap = Token.getAccessToken();
		accessToken = (String) tokenMap.get("access_token");
		refreshToken = (String) tokenMap.get("refresh_token");
		accountUserName = (String) tokenMap.get("account_username");
		System.out.println("accessToken: " + accessToken);
		System.out.println("accountUserName: " +accountUserName );
	}
	
	@Test
	public void getAccountBlockStatusTest() 
	{
		Map<String, String>authToken = Token.getTokenAccess();
		
		Response response = RestClient.doGet(null, "https://api.imgur.com",
				"/account/v1/" + accountUserName +"/block", 
				authToken, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	/**
	 * this test will return all images stored for this  account id
	 */
	@Test
	public void getAccountImagesTest() 
	{
		Map<String, String>authToken = Token.getTokenAccess();
		Response response = RestClient.doGet(null, "https://api.imgur.com",
				"/3/account/me/images", 
				authToken, null, true);
		System.out.println(response.statusCode());
		System.out.println(response.prettyPrint());
	}
	
	@Test
	public void uploadImagePostAPITest()
	{
		Map<String, String> clientIdMap = Token.getClientId();
		Map<String, String> formMap = new HashMap<String, String>();
		formMap.put("title", "test title");
		formMap.put("description", "test description API");
	
		//https://api.imgur.com/3/upload
 		Response response = RestClient.doPost("multipart", "https://api.imgur.com", 
				"/3/upload", clientIdMap, null, true, formMap);
		System.out.println(response.statusCode());
		response.prettyPrint();
	}
}
