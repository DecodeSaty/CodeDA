package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/java/features/backend.feature" ,"src/test/java/features/webportal.feature" } ,
        plugin = {"json:target/cucumber.json", "pretty"},
        glue = "steps")
public class TestRunner extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }

}


