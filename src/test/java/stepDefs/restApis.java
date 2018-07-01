package stepDefs;

import java.util.Map;

import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utility.RestExecutor;
import utility.RestResponse;
import utility.RestValidator;
import utility.hook;

public class restApis {
	private RestValidator validatedResponse;
	private RestExecutor executor;
	private RestResponse response;
	private Scenario scenario;

	public restApis() {
		this.executor= hook.getExecutor();
		this.validatedResponse = hook.validatedResponse;
		this.scenario = hook.scenario;
	}

	@Given("^a book exists with an isbn of (\\d+)$")
	public void a_book_exists_with_isbn(String isbn){
		validatedResponse = executor.get("?q="+isbn);
		
		
	}

	@When("^a user retrieves the book by isbn$")
	public void a_user_retrieves_the_book_by_isbn(){
		response = validatedResponse.getResponse();
	}

	@Then("^the status code is (\\d+)$")
	public void verify_status_code(int statusCode){
		
		validatedResponse.expectCode(statusCode);
		
	}

	@And("^response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			validatedResponse.expectInBody(field.getKey().toString())
			.expectInBody(field.getValue().toString());
		}
			
		
	}

	@And("^response includes the following in any order$")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		
		for (Map.Entry<String, String> field : responseFields.entrySet()) {

			
			validatedResponse.expectInBody(field.getValue().toString());
		}
	}
	


}
