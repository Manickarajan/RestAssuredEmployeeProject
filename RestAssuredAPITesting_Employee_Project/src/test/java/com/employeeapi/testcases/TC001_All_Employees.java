package com.employeeapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_All_Employees extends TestBase {
 
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
	logger.info("-------TC01 is started--------");
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	httpRequest=RestAssured.given();
	response=httpRequest.request(Method.GET, "/employees");
	Thread.sleep(3000);
	
	}
	
	@Test
	void checkResponseBody() {
		logger.info("---------Checking Response Body----");
		
		String responsebody=response.getBody().asString();
		logger.info("Response Body is :"+responsebody);
		Assert.assertTrue(responsebody!=null);
	}
	
	@Test
	void checkStatusCode() {
		logger.info("---------Checking Status Code----");
		
		int statuscode=response.getStatusCode();
		logger.info("Status code is :"+statuscode);
		Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	void checkStatusLine() {
		logger.info("---------Checking Status Line----");
		
		String statusline = response.getStatusLine();
		logger.info("Status line is :"+statusline);
		Assert.assertEquals(statusline, "HTTP/1.1 200 OK");
	}
	
	
	@Test
	void checkContentType() {
		logger.info("---------Checking Content Type----");
		
		String contenttype = response.getHeader("Content-Type");
		logger.info("Content Type is :"+contenttype);
		Assert.assertEquals(contenttype, "text/html; charset=UTF-8");
	}
	
	@Test
	void checkServerType() {
		logger.info("---------Checking Server Type----");
		
		String servertype = response.getHeader("Server");
		logger.info("Server type is :"+servertype);
		Assert.assertEquals(servertype, "nginx/1.16.0");
	}
	
	@AfterClass
	void teardown() {
		logger.info("-------TC01 is Completed--------");
	}
}
