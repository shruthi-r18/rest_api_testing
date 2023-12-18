package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.response.Response;
import rest_utils.DataUtils;
import rest_utils.RestUtils;

public class DeleteDataTests extends BaseTest{

	@Test
	public void addDataTests_TC01() throws IOException {
		String sEnv = DataUtils.readJsonFilePathToString(FileConstants.ENVI_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.production.URL");
		sUri = sUri + JsonPath.read(sEnv, "$.production.endpoint.deletedata");
		HashMap<String, String> deleteDataHeaders = new HashMap<String, String>();
		deleteDataHeaders.put("Content-Type", "application/json");
		deleteDataHeaders.put("token", token);
		
		HashMap<String, String> sDeleteDataPayload = new HashMap<String, String>();
		String creds = DataUtils.readJsonFilePathToString(FileConstants.DELETE_USER_DATA_FILE_PATH);
		sDeleteDataPayload.put( "id", id);
		sDeleteDataPayload.put( "userid", userid);
		
		Response res = RestUtils.taDelete(sUri, deleteDataHeaders, sDeleteDataPayload);
		System.out.println(res.getStatusCode());
	}
	
	
}
