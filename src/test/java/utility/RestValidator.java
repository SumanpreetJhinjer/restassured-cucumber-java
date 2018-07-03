package utility;

import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import cucumber.api.Scenario;

public class RestValidator {

	private RestResponse response;
	private Scenario scenario;

	RestValidator(RestResponse response) {
		this.response = response;
		this.scenario = hook.getScenario();
	}

	public RestValidator expectCode(int expectedCode) {
		scenario.write("Expected Status Code: "+expectedCode+" Actual status code: "+response.getResponseCode());
		Assert.assertEquals("Incorrect Response Code", expectedCode, response.getResponseCode());
		return this;
	}

	public RestValidator expectMessage(String message) {
		Assert.assertEquals("Incorrect Response Message", message, response.getResponseMessage());
		return this;
	}

	public RestValidator expectHeader(String headerName, String headerValue) {
		Assert.assertEquals("Incorrect header - " + headerName, headerValue, response.getHeader(headerName));
		return this;
	}

	public RestValidator expectHeaders(HashMap<String, String> headers) {
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			Assert.assertEquals("Incorrect header - " + key, headers.get(key), response.getHeader(key));
		}
		return this;
	}

	public RestValidator expectInBody(String content) {
		scenario.write("Expected body content: "+content+" Actual body content: "+response.getResponseBody().contains(content));
		Assert.assertTrue("Body doesnt contain string : " + content, response.getResponseBody().contains(content));
		return this;
	}
	
	public RestValidator expectBodyKeyValue(Map<String, String> body) {
		//Set<String> keys = body.keySet();
		for (Map.Entry<String, String> field : body.entrySet()) {
			scenario.write("Expected body Key: "+field.getKey()+" Actual body content: "+response.getResponseBody().valueOf(field.getKey()));
			scenario.write("Expected body Value: "+field.getValue()+" Actual body content: "+response.getResponseBody().valueOf(field.getValue()));
			Assert.assertEquals("Body doesnt contain Key - " + field.getKey(), field.getKey(), response.getResponseBody().valueOf(field.getKey()));
			Assert.assertEquals("Body doesnt contain Value - " + field.getValue(), field.getValue(), response.getResponseBody().valueOf(field.getValue()));
		}
		return this;
	}
	
	
	
	public RestValidator printBody(){
		System.out.println(response.getResponseBody());
		return this;
	}
	
	public RestResponse getResponse(){
		return response;
	}

}
