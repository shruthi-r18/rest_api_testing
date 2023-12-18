package demo;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APITesting {

	public static void main(String[] args) {

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");

		RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";

		Response res = RestAssured.given().headers(headers).when()
				.body("{\"username\": \"mar2023shruthi@ta.com\", \"password\": \"Mar231234\"}").post("login").then()
				.statusCode(201).extract().response();

		System.out.println(res.asPrettyString());

		String token = res.jsonPath().getString("token").replaceAll("\\[", "").replaceAll("]", "");
		System.out.println(token);

		String userid = res.jsonPath().getString("userid").replaceAll("\\[", "").replaceAll("]", "");
		System.out.println(userid);

		HashMap<String, String> addDataHeaders = new HashMap<String, String>();
		addDataHeaders.put("Content-Type", "application/json");
		addDataHeaders.put("token", token);

		HashMap<String, String> addDataPayload = new HashMap<String, String>();
		addDataPayload.put("accountno", "TA-1621899");
		addDataPayload.put("departmentno", "2");
		addDataPayload.put("salary", "24514");
		addDataPayload.put("pincode", "235613");

		Response res2 = RestAssured.given().headers(addDataHeaders).when().body(addDataPayload).post("adddata").then()
				.statusCode(201).extract().response();

		System.out.println(res2.asPrettyString());

		Response res01 = RestAssured.given().headers(addDataHeaders).when().get("getData").then()
				.statusCode(200).extract().response();

	System.out.println(res01.asPrettyString());
		
		HashMap<String, String> updateDataPayload = new HashMap<String, String>();
		updateDataPayload.put("accountno", "TA-1621899");
		updateDataPayload.put("departmentno", "3");
		updateDataPayload.put("id", "6wiOXfkG8CQ5YEi4c2Lf");
		updateDataPayload.put("pincode", "235616");
		updateDataPayload.put("salary", "24514");
		updateDataPayload.put("userid", userid);

		Response res3 = RestAssured.given().headers(addDataHeaders).when().body(updateDataPayload).put("updateData")
				.then().statusCode(200).extract().response();
		System.out.println(res3.asPrettyString());

		HashMap<String, String> deleteDataPayload = new HashMap<String, String>();
		deleteDataPayload.put("id", "6wiOXfkG8CQ5YEi4c2Lf");
		deleteDataPayload.put("userid", userid);

		Response res4 = RestAssured.given().headers(addDataHeaders).when().body(deleteDataPayload).delete("deleteData")
				.then().statusCode(200).extract().response();

		System.out.println(res4.asPrettyString());
	}

}
