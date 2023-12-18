package rest_utils;

import java.io.File;
import java.util.HashMap;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import testdata.GetDataPayload;


/**
 * @author shrut
 *
 */
public class RestUtils {

	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public static Response taPost(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {

		RestAssured.baseURI = sBaseUri;
		System.out.println(sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();

		Response res = RestAssured.given().headers(header).when().body(payload).post();
		return res;
	}
	public static Response taPost(String sBaseUri, HashMap<String, String> header, String payload) {

		RestAssured.baseURI = sBaseUri;
		System.out.println(sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();

		Response res = RestAssured.given().headers(header).when().body(payload).post();
		return res;
	}
	
	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public static Response taPost(String sBaseUri, HashMap<String, String> header, Object payload) {

		RestAssured.baseURI = sBaseUri;
		System.out.println(sBaseUri);
		RestAssured.useRelaxedHTTPSValidation();

		Response res = RestAssured.given().headers(header)
				.when().body(payload).post()
				.then().assertThat().body(matchesJsonSchema(new File(FileConstants.LOGIN_SCHEMA_FILE_PATH))).extract().response();
		return res;
	}

	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public static Response taGet(String sBaseUri, HashMap<String, String> header) {

		RestAssured.baseURI = sBaseUri;
		
		Response res = RestAssured.given().headers(header).get();
		
		System.out.println(res.asPrettyString());
		return res;
	}

	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 * 
	 */
	public static Response taPut(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {

		RestAssured.baseURI = sBaseUri;
         
		Response res = RestAssured.given().headers(header).when().body(payload).put();
		return res;
	}

	/**
	 * @param sBaseUri
	 * @param header
	 * @param payload
	 * @return
	 */
	public static Response taDelete(String sBaseUri, HashMap<String, String> header, HashMap<String, String> payload) {

		RestAssured.baseURI = sBaseUri;

		Response res = RestAssured.given().headers(header).when().body(payload).delete();
		return res;
	}
	
	/**
	 * @param user
	 * @return
	 * @throws JsonProcessingException
	 */
	public String serializeObject(Object user) throws JsonProcessingException {
		System.out.println(" in serializeObject ");
		ObjectMapper om = new ObjectMapper();
		String jsonPayload = om.writeValueAsString(user);
		System.out.println("RestUtils: serializeObject: "+jsonPayload);
		return jsonPayload;
	}
	
	public Object deSerializeJson(Response res) throws JsonMappingException, JsonProcessingException {
		System.out.println(" in deSerializeJson");
	String sFirstObject = JsonPath.read(res.getBody().asString(), "$.[0]");
	ObjectMapper om= new ObjectMapper();
	GetDataPayload user = om.readValue(sFirstObject, GetDataPayload.class);
	System.out.println("RestUtils: deSerializeJson: "+user.getAccountNo());
	return user;
	}

}
