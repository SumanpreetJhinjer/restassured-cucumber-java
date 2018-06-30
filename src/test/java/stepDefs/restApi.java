package stepDefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Color;
import java.util.Map;

import javax.swing.JTextArea;

import org.apache.commons.lang3.StringUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class restApi {
	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;
	

	//private String ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";
	public String ENDPOINT_GET_BOOK_BY_ISBN;
	public Scenario scenario;
	@Before
	public void setup(Scenario scenario) {
		 
		        this.scenario = scenario;
		    
		ENDPOINT_GET_BOOK_BY_ISBN = "https://www.googleapis.com/books/v1/volumes";
	} 

	@Given("a book exists with an isbn of (.*)")
	public void a_book_exists_with_isbn(String isbn){
		request = given().param("q", "isbn:" + isbn);
		
	}

	@When("a user retrieves the book by isbn")
	public void a_user_retrieves_the_book_by_isbn(){
		response = request.when().get(ENDPOINT_GET_BOOK_BY_ISBN);
		System.out.println("response: " + response.prettyPrint());
		scenario.write(response.body().prettyPrint());
	}

	@Then("the status code is (\\d+)")
	public void verify_status_code(int statusCode){
		scenario.write("Expected Status Code: "+statusCode+" Actual status code: "+response.getStatusCode());
		json = response.then().statusCode(statusCode);
		
	}

	@And("response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}

	@And("response includes the following in any order")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}
	
	@After
	public void closer(Scenario scenario) {
		if(scenario.isFailed()) {
			String message1 ="<html><font size=\"3\" face=\"verdana\" color=\"red\">Scenario is failed please see the stacktrace for more info</font></html>";
			scenario.write(message1);
			
		}
	}

}
