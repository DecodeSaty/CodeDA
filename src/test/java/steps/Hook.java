package steps;


import base.BaseClass;
import io.cucumber.java.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hook extends BaseClass {

    private BaseClass base;

    public Hook(BaseClass base) {
        this.base = base;
    }

    @Before
    public void InitializeTest(Scenario scenario) {
        base.scenarioDef = base.features.createNode(scenario.getName());
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        base.wDriver = new ChromeDriver(chromeOptions);
    }


    @After
    public void TearDownTest(Scenario scenario) {
        base.wDriver.quit();
    }



}
