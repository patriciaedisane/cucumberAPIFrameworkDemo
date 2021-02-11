package cucumber.Options;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = {"stepDefinitions"}, tags={"@AddPlace"})
//@CucumberOptions(features = "src/test/java/features", plugin = "json:target/jsonReports/cucumber-reports.json", glue = {"stepDefinitions"})
public class TestRunner {
}
