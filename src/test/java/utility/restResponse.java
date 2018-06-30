package utility;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class restResponse {

	private static Response response;
	
	public static Response getRequest(RequestSpecification request) {
		
		response = request.when().get(hook.apiURL);
		return response;
	}
}
