package utility;


import utility.RestExecutor;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class hook {

	public static String apiURL;
	public static Scenario scenario;
	public static RestExecutor executor;
	public static RestValidator validatedResponse;
	@Before("@googleAPI")
	public void setupGoogle(Scenario scenario) {
		 
		this.scenario = scenario;    
		apiURL = "https://www.googleapis.com/books/v1/volumes";
		executor = new RestExecutor(apiURL);
	} 
	@Before("@reqire")
	public void setupReqire(Scenario scenario) {
		 
		this.scenario = scenario;    
		apiURL = "https://reqres.in/api";
		executor = new RestExecutor(apiURL);
	} 
	
	@After
	public void closer(Scenario scenario) {
		if(scenario.isFailed()) {
			String message1 ="<html><font size=\"3\" face=\"verdana\" color=\"red\">Scenario is failed please see the stacktrace for more info</font></html>";
			scenario.write(message1);
			
		}
	}
	public static RestExecutor getExecutor() //replaced webdriver with android driver
	{
		
		return executor;
		
	}
	public static Scenario getScenario() //replaced webdriver with android driver
	{
		
		return scenario;
	}
}
