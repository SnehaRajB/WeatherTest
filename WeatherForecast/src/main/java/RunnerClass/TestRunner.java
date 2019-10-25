package RunnerClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "/Users/sainath/Documents/BBCProj/WeatherForecast/src/main/java/Features",		
		glue = {"com.stepDefinations"}, 
		format = {"pretty","html:test-output"}, 
		monochrome = true
		)

public class TestRunner {

}
