package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="C:\\Users\\LENOVO\\eclipse-workspace\\restassured\\src\\test\\java\\features\\addplace.feature",
		glue= {"StepDefinitions"},
		plugin= {"pretty",
				"html:reports/myreport.html",
				"json:report/myreport.json"
		}

		)

public class runner {

}
