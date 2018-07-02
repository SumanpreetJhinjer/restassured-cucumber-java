package stepDefs;

import java.util.Map;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import utility.RestExecutor;
import utility.RestResponse;
import utility.RestValidator;
import utility.hook;

public class apiTest {
	public static RestValidator validatedResponse;
	private RestExecutor executor;
	private RestResponse response;
	private Scenario scenario;

	public apiTest() {
		this.executor= hook.getExecutor();
		this.scenario = hook.scenario;
	}

	@Given("^a user exist in the api with id (\\d+)$")
	public RestValidator a_user_exists_with_id(String id){
		validatedResponse = executor.get("/users/"+id);
		
		response = validatedResponse.getResponse();		
		return hook.validatedResponse=validatedResponse;
	}
	
	@Given("^i create following user$")
	public RestValidator userCreation(Map<String,String> responseFields) {
		String body = responseFields.entrySet().toString();
		body = body.substring(1,body.length()-1);
		body = body.replaceAll("=", ": ");
		System.out.println("{"+body+"}");
		
		validatedResponse= executor.post("/users/", "{"+body+"}", "application/json");
		
		return hook.validatedResponse=validatedResponse;
	}


	


}
