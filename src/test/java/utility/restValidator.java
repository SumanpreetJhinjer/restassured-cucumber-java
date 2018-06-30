package utility;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class restValidator {

	private static ValidatableResponse json;
	public static ValidatableResponse statusValidation(Response response, int statusCode ) {
		return json = response.then().statusCode(statusCode);
	}
	
	public static ValidatableResponse bodyEqualValidation(Map.Entry<String, String> field) {
		if(StringUtils.isNumeric(field.getValue())){
			
			return json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
		}
		else{
			return json.body(field.getKey(), equalTo(field.getValue()));
		}
	}
	
	public static ValidatableResponse bodyInAnyOrderValidation(Map.Entry<String, String> field) {
		if(StringUtils.isNumeric(field.getValue())){
			
			return json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
		}
		else{
			return json.body(field.getKey(), containsInAnyOrder(field.getValue()));
		}
	}
	
}
