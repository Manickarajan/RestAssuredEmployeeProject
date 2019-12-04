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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Update_Employee extends TestBase{
	RequestSpecification httprequest;
	Response response;
	
	String empName=RestUtils.empName();	
	String empSal=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	@BeforeClass
	void updateemployee() throws InterruptedException {
		logger.info("-------TC04 is started--------");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest=RestAssured.given();
		
		JSONObject requestParams=new JSONObject();
		  requestParams.put("name",empName);
		  requestParams.put("salary",empSal);
		  requestParams.put("age",empAge);
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  httpRequest.body(requestParams.toJSONString());
		response=httpRequest.request(Method.PUT,"/update/12914");
		
		Thread.sleep(5000);
	}
	
	@Test
	void checkResponseBody() {
		logger.info("---------Checking Response Body----");
		
		String responsebody=response.getBody().asString();
		logger.info("Response Body is :"+responsebody);
		Assert.assertEquals(responsebody.contains(empName),true);
		Assert.assertEquals(responsebody.contains(empSal),true);
		Assert.assertEquals(responsebody.contains(empAge),true);
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
		logger.info("-------TC04 is Completed--------");
	}
}
