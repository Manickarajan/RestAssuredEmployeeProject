package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String empName() {
		String generatedsting=RandomStringUtils.randomAlphabetic(1);
		return("John"+generatedsting);
	}
	
	public static String empSal() {
		String generatedsting=RandomStringUtils.randomNumeric(5);
		return(generatedsting);
	}
	
	public static String empAge() {
		String generatedsting=RandomStringUtils.randomNumeric(2);
		return(generatedsting);
	}
}
