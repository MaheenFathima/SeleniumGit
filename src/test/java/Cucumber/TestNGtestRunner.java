package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "qaAcademy.StepDefinitions", monochrome = true,tags="@ErrorValidation",  plugin = {
		"html:reports/cucumbertest.html"})
public class TestNGtestRunner extends AbstractTestNGCucumberTests {

}
//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