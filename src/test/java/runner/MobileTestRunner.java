package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

public class MobileTestRunner {

    @CucumberOptions(features = {"src/test/java/features/mobile.feature"} , plugin = {"json:target/cucumber.json", "pretty"},
            glue = "mobilesteps")
    public class TestRunner extends AbstractTestNGCucumberTests {


        @Override
        @DataProvider
        public Object[][] scenarios() {
            return super.scenarios();
        }

    }
}
