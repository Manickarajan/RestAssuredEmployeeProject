package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listners extends TestListenerAdapter{

	public ExtentHtmlReporter htmlReporter;
	
	public ExtentReports extent;
	
	public ExtentTest test;
	
	public void onStart(ITestContext testcontext) {
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\myReport.html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Rest API Automation report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Manick");
	}
	
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS,"Test Case passed is "+ result.getName());
	}
	
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case failed is "+result.getName());
		test.log(Status.FAIL, result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Case skipped is "+result.getName());
	}
	
	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}
}
