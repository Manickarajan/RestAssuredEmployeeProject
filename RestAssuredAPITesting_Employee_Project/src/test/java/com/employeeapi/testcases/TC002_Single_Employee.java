package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Single_Employee extends TestBase{
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
	logger.info("-------TC02 is started--------");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest=RestAssured.given();
	response=httpRequest.request(Method.GET, "/employee/"+empid);
	Thread.sleep(3000);
	
	}
	
	@Test
	void checkResponseBody() {
		logger.info("---------Checking Response Body----");
		
		String responsebody=response.getBody().asString();
		logger.info("Response Body is :"+responsebody);
		Assert.assertEquals(responsebody.contains(empid),true);
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
		logger.info("-------TC02 is Completed--------");
	}
}
