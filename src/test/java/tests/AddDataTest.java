package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.response.Response;
import rest_utils.DataUtils;
import rest_utils.RestUtils;
import testdata.AddDataPayLoad;



public class AddDataTest extends BaseTest {

	
	
	@Test(enabled = false)
	public void addDataTests_TC01() throws IOException {
		String sEnv = DataUtils.readJsonFilePathToString(FileConstants.ENVI_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.production.URL");
		sUri = sUri + JsonPath.read(sEnv, "$.production.endpoint.adddata");
		HashMap<String, String> addDataHeaders = new HashMap<String, String>();
		addDataHeaders.put("Content-Type", "application/json");
		addDataHeaders.put("token", token);
		
		HashMap<String, String> sAddDataPayload = new HashMap<String, String>();
		String creds = DataUtils.readJsonFilePathToString(FileConstants.ADD_USER_DATA_FILE_PATH);
		sAddDataPayload.put( "accountno", JsonPath.read(creds,"$.data.accountno"));
		sAddDataPayload.put( "departmentno", JsonPath.read(creds,"$.data.departmentno"));
		sAddDataPayload.put( "salary", JsonPath.read(creds,"$.data.salary"));
		sAddDataPayload.put( "pincode", JsonPath.read(creds,"$.data.pincode"));
		Response res = RestUtils.taPost(sUri, addDataHeaders, sAddDataPayload);
		System.out.println(res.getStatusCode());
	}
	@Test
	public void addDataTests_TC02() throws IOException {
		String sEnv = DataUtils.readJsonFilePathToString(FileConstants.ENVI_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.production.URL");
		sUri = sUri + JsonPath.read(sEnv, "$.production.endpoint.adddata");
		
		HashMap<String, String> addDataHeaders = new HashMap<String, String>();
		addDataHeaders.put("Content-Type", "application/json");
		addDataHeaders.put("token", token);
		
		AddDataPayLoad  user = new AddDataPayLoad ("SH-1234567","2","453762","345217");
		BaseTest.setup();
		Response res = RestUtils.taPost(sUri, addDataHeaders, oRestUtils.serializeObject(user));
		System.out.println(res.getStatusCode());
		
		
	}
}
