package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.response.Response;
import rest_utils.DataUtils;
import rest_utils.RestUtils;

public class GetDataTest extends BaseTest {
	
	@Test
	public void getDataTest_TC01() throws IOException {
		String sEnv = DataUtils.readJsonFilePathToString(FileConstants.ENVI_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.production.URL");
		sUri = sUri + JsonPath.read(sEnv, "$.production.endpoint.getdata");
		System.out.println(sUri);
		HashMap<String, String> getDataHeaders = new HashMap<String, String>();
		getDataHeaders.put("Content-Type", "application/json");
		getDataHeaders.put("token", token);
		Response res=RestUtils.taGet(sUri, getDataHeaders);
		 System.out.println("get:  "+res.getStatusCode());
		 id=JsonPath.read(res.asPrettyString(), "$.[0].id");
		System.out.println("getDataTest_TC01: "+id);
		//BaseTest.setup();
		//Object user = oRestUtils.deSerializeJson(res);
//		System.out.println(user.toString());
	}

}
