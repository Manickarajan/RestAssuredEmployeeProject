package com.employeeapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee extends TestBase {
	RequestSpecification httprequest;
	Response response;
	
	
	
	@BeforeClass
	void updateemployee() throws InterruptedException {
		logger.info("-------TC05 is started--------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		response=httpRequest.request(Method.GET, "/employees");
		JsonPath jsonpath = response.jsonPath();
		String empid=jsonpath.get("[0].id");
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  
		response=httpRequest.request(Method.DELETE,"/delete/"+empid);
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("---------Checking Response Body----");
		
		String responsebody=response.getBody().asString();
		logger.info("Response Body is :"+responsebody);
		Assert.assertEquals(responsebody.contains("deleted Records"),true);
	}
	
	@Test
	void checkStatusCode() {
		logger.info("---------Checking Status Code----");
		
		int statuscode=response.getStatusCode();
		logger.info("Status code is :"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	
	@AfterClass
	void teardown() {
		logger.info("-------TC05 is Completed--------");
	}
}
