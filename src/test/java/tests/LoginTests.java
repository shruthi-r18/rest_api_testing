package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.Test;
import com.jayway.jsonpath.JsonPath;

import constants.FileConstants;
import io.restassured.response.Response;
import rest_utils.DataUtils;
import rest_utils.RestUtils;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class LoginTests extends BaseTest {

	@Test
	public void loginTests_TC01() throws IOException {
		String sEnv = DataUtils.readJsonFilePathToString(FileConstants.ENVI_URI_FILE_PATH);
		String sUri = JsonPath.read(sEnv, "$.production.URL");
		sUri = sUri + JsonPath.read(sEnv, "$.production.endpoint.login");
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		HashMap<String, String> sPayload = new HashMap<String, String>();
		String creds = DataUtils.readJsonFilePathToString(FileConstants.USER_ACCOUNTS_FILE_PATH);
		sPayload.put("username", JsonPath.read(creds, "$.production.valid.username"));
		sPayload.put("password", JsonPath.read(creds, "$.production.valid.password"));
		Response res = RestUtils.taPost(sUri, headers, sPayload);
		System.out.println(res.asPrettyString());
//		String expectedValidation = DataUtils.readJsonFilePathToString(FileConstants.LOGIN_VALIDATION_FILE_PATH);
//		assertThat(String.valueOf(res.getStatusCode()),JsonPath.read(expectedValidation,"$.login_validation01[0]"));
		 token = res.jsonPath().getString("token").replaceAll("\\[", "").replaceAll("]", "");
		System.out.println(token);

		 userid = res.jsonPath().getString("userid").replaceAll("\\[", "").replaceAll("]", "");
		System.out.println(userid);

	}

}
