package utility;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

public class restRequest {

	private static RequestSpecification request;
	
	public static RequestSpecification reqSpec(String... args) {
		
		request = given().param("q", "isbn:" + args[0]);
		
		return request;
	}
}
