package com.qa.api.gorest.util;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Token {

	public static Map<Object, Object> appTokenMap;
	public static Map<String, String> tokenMap = new HashMap<String,String>();
	public static String clientId = "78b20dc1d658df2";
	
	/** THIS TOKEN IS FOR AMGUR.COM ONLY
	 * 
	 * this method is to generate token Id 
	 * @return hashMap of response containing various fields like 
	 * 
	 */
	public static Map<Object,Object> getAccessToken() {
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("refresh_token", "f3e56aa32e95995a97ace53d174b86c08feb768a");
		formParams.put("client_id", "2d2f5866fec77ec");
		formParams.put("client_secret", "90a4548bf1bb9a1db44933edbeb3976bcf059942");
		formParams.put("grant_type", "refresh_token");

		JsonPath tokenJson = RestAssured.given().formParams(formParams).when()
				.post("https://api.imgur.com/oauth2/token").then().extract().jsonPath();
		//System.out.println("Map : " + tokenJson.getMap(""));
		
		appTokenMap =  tokenJson.getMap("");
		return appTokenMap;
	}
	
	/** 
	 * this method will collect access token in form of collection
	 * @return client id in form of map of String, String
	 */
	public static Map<String, String> getTokenAccess()
	{
		String authToken = appTokenMap.get("access_token").toString();
		System.out.println("auth Token ====> " + authToken);
		tokenMap.put("Authorization", "Bearer " + authToken);
		return tokenMap;	
	}

	/**
	 * collects client id in form of map of String, String
	 * @return client id in form of map
	 */
	public static Map<String, String> getClientId()
	{
		System.out.println("Client Id ====> " + clientId);
		tokenMap.put("Authorization", "Client-ID " + clientId);
		return tokenMap;	
	}
	
 	 public static void main(String[] args) 
 	 {
 		 Map<Object, Object> map = getAccessToken(); 
 		 System.out.println("map : "+ map);
 		 
 		 System.out.println("access Token    : " + map.get("access_token"));
 		 System.out.println("token_type      : " + map.get("token_type"));
 		 System.out.println("refresh_token   : " + map.get("refresh_token"));
 		 System.out.println("account_id      : " + map.get("account_id"));
 		 System.out.println("account_username: " + map.get("account_username"));
 	 }
	 
}
