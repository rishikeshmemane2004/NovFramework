package com.qa.api.gorest.restclient;

import java.io.File;
import java.util.Map;

import com.qa.api.gorest.util.TestUtil;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * This class is having all HTTP methods which will call the APIs and having
 * generic methods for getting the response and fetch the values from response.
 * 
 * @author Rishikessh
 *
 */
public class RestClient {

	// HTTP methods : GEt, Post, Put, Delete
	/**
	 * this method will call POST HTTP method
	 * 
	 * @param contentType
	 * @param baseUri
	 * @param basePath
	 * @param token
	 * @param paramMap
	 * @param log
	 * @param obj
	 * @return
	 */
	@Step("POST HTTP Call with Parameter {0},{1},{2},{3},{4},{5},{6}")
	public static Response doPost(String contentType, String baseUri, String basePath, 
			Map<String, String> token,
			Map<String, String> paramMap, boolean log, Object obj) {
		if (setBaseUri(baseUri)) {
			RequestSpecification request = createRequest(contentType, token, paramMap, log);
			addRequestPayLoad(request, obj);
			return getResponse("POST", request, basePath);
		}
		return null;
	}

	@Step("Add payload with Parameter {0},{1}")
	private static void addRequestPayLoad(RequestSpecification request, Object obj) {
		if (obj instanceof Map) {
			request.formParams((Map<String, String>) obj);
		} else {
			String jSonPayLoad = TestUtil.getSerializedJSON(obj);
			request.body(jSonPayLoad);
		}
	}
	@Step("GET HTTP Call with Parameter {0},{1},{2},{3},{4},{5}")
	public static Response doGet(String contentType, String baseUri, String basePath, 
			Map<String,String> token,
			Map<String, String> paramMap, boolean log) 
	{

		if (setBaseUri(baseUri)) {
			RequestSpecification request = createRequest(contentType, token, paramMap, log);
			return getResponse("GET", request, basePath);
		}
		return null;
	}

	@Step("Get-Response with Parameter {0},{1},{2}")
	private static Response getResponse(String httpMethod, RequestSpecification request, 
									    String basePath) 
	{
		return executeAPI(httpMethod, request, basePath);
	}

	@Step("ExecuteAPI with Parameter {0},{1},{2}")
	private static Response executeAPI(String httpMethod, RequestSpecification request, 
									   String basePath)
	{
		Response response = null;
		switch (httpMethod.toUpperCase()) {
		case "GET":
			response = request.get(basePath);
			break;
		case "POST":
			response = request.post(basePath);
			break;
		case "PUT":
			response = request.put(basePath);
			break;
		case "DELETE":
			response = request.delete(basePath);
			break;
		default:
			System.out.println("Please pass the correct http method : you entered : " + httpMethod);
			break;
		}
		return response;
	}

	/**
	 * Creates request with content type, token, parameters with logs if required
	 * 
	 * @param contentType
	 * @param token
	 * @param paramMap
	 * @param log
	 * @return
	 */
	@Step("Create Request with Parameter {0},{1},{2},{3}")
	private static RequestSpecification createRequest(String contentType, Map<String, String> token,
			Map<String, String> paramMap, boolean log) {
		RequestSpecification request;

		// Add log
		if (log)
			request = RestAssured.given().log().all();
		else
			request = RestAssured.given();

		// Add token
		if (token.size() > 0)
			// request.header("Authorization","Bearer "+ token);
			request.headers(token);

		// Add parameters
		if (!(paramMap == null))
			request.queryParams(paramMap);
		// Add content type
		if (contentType != null) {
			if (contentType.equalsIgnoreCase("JSON"))
				request.contentType(ContentType.JSON);
			else if (contentType.equalsIgnoreCase("XML"))
				request.contentType(ContentType.XML);
			else if (contentType.equalsIgnoreCase("TEXT"))
				request.contentType(ContentType.TEXT);
			else if (contentType.equalsIgnoreCase("MULTIPART"))
				request.multiPart("image", new File("C:\\Users\\lenovo\\Downloads\\img2.bmp"));
		}

		return request;

	}

	/**
	 * Adds baseUri
	 * 
	 * @param baseUri
	 */
	@Step("Set Base URI for HTTP Call with Parameter {0}")
	private static boolean setBaseUri(String baseUri) {
		if ( baseUri == null || 
				baseUri.isEmpty() || baseUri.isBlank())
		{
			System.out.println("Please pass correct bsaeURI, You entered either null/blank/empty");
			return false;
		}
		try {
			RestAssured.baseURI = baseUri;
			return true;
		} catch (Exception e) {
			System.out.println("Some exception occcurred while assinging the base URI with rest assured !!!");
			return false;
		}
	}

	@Step("DELTETE HTTP Call with Parameter {0},{1},{2},{3}")
	public static Response doDelete(String baseUri, String basePath, 
			Map<String,String> token, boolean log) 
	{

		if (setBaseUri(baseUri)) {
			RequestSpecification request = createRequest(null, token, null, log);
			return getResponse("DELETE", request, basePath);
		}
		return null;
	}
	
	@Step("Update - PUT HTTP Call with Parameter {0},{1},{2},{3},{4},{5},{6}")
	public static Response doPut(
			String contentType, String baseUri, String basePath, 
			Map<String, String> token, Map<String, String> paramMap, 
			boolean log, Object obj) 	
	{
		if (setBaseUri(baseUri)) {
			RequestSpecification request = createRequest(contentType, token, paramMap, log);
			addRequestPayLoad(request, obj);
			return getResponse("PUT", request, basePath);
		}
		return null;
	}
}
