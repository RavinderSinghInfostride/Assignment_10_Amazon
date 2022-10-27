package Runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/FeatureFile/AddToCart.feature",
        glue = "StepDefinition",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/Cucumber.html",
        },
        monochrome = true
)
public class Runner {
}