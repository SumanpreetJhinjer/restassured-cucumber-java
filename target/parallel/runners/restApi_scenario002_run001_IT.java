

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"target/parallel/features/restApi_scenario002_run001_IT.feature"},
        plugin = {"json:target/cucumber-report/restApi_scenario002_run001_IT.json"}
)
public class restApi_scenario002_run001_IT {

}

// Generated by Cucable from src/test/java/runner/cucable.java
