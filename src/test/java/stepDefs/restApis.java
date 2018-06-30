package stepDefs;

import java.util.Map;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import utility.restRequest;
import utility.restResponse;
import utility.restValidator;
import utility.hook;

public class restApis {
	private Response response;
	private RequestSpecification request;
	private Scenario scenario;

	public restApis() {
		this.scenario = hook.getScenario();
	}

	@Given("^a book exists with an isbn of (\\d+)$")
	public void a_book_exists_with_isbn(String isbn){
		//request = given().param("q", "isbn:" + isbn);
		request=restRequest.reqSpec(isbn);
		
	}

	@When("^a user retrieves the book by isbn$")
	public void a_user_retrieves_the_book_by_isbn(){
		response=restResponse.getRequest(request);
		//response = request.when().get(apiURL);
		
		scenario.write(response.body().prettyPrint());
	}

	@Then("^the status code is (\\d+)$")
	public void verify_status_code(int statusCode){
		scenario.write("Expected Status Code: "+statusCode+" Actual status code: "+response.getStatusCode());
		restValidator.statusValidation(response, statusCode);
		//json = response.then().statusCode(statusCode);
		
	}

	@And("^response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			
				restValidator.bodyEqualValidation(field);
			
		}
	}

	@And("^response includes the following in any order$")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			restValidator.bodyInAnyOrderValidation(field);
		}
	}
	


}
