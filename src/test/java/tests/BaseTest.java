package tests;

import org.testng.annotations.BeforeSuite;

import rest_utils.RestUtils;

public class BaseTest {
	
	static RestUtils oRestUtils = null;
	public static String token =null;
	public static  String userid = null;
	public static  String  id = null;
	
	@BeforeSuite
	public static void setup() {
		oRestUtils = new RestUtils();
	}

}
