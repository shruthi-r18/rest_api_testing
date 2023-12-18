package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.response.Response;
import rest_utils.DataUtils;
import rest_utils.RestUtils;

public class UpdateDataTests extends BaseTest {
	@Test
	public void addDataTests_TC01() throws IOException {
		String sEnv = DataUtils.readJsonFilePathToString(FileConstants.ENVI_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.production.URL");
		sUri = sUri + JsonPath.read(sEnv, "$.production.endpoint.updatedata");
		HashMap<String, String> updateDataHeaders = new HashMap<String, String>();
		updateDataHeaders.put("Content-Type", "application/json");
		updateDataHeaders.put("token", token);
		
		HashMap<String, String> sUpdateDataPayload = new HashMap<String, String>();
		String creds = DataUtils.readJsonFilePathToString(FileConstants.UPDATE_USER_DATA_FILE_PATH);
		sUpdateDataPayload.put( "accountno", JsonPath.read(creds,"$.data.accountno"));
		sUpdateDataPayload.put( "departmentno", JsonPath.read(creds,"$.data.departmentno"));
		sUpdateDataPayload.put( "id", id);
		sUpdateDataPayload.put( "salary", JsonPath.read(creds,"$.data.salary"));
		sUpdateDataPayload.put( "pincode", JsonPath.read(creds,"$.data.pincode"));		
		sUpdateDataPayload.put( "userid",userid);
		
		Response res = RestUtils.taPut(sUri, updateDataHeaders, sUpdateDataPayload);
		System.out.println(res.getStatusCode());
	}
}
