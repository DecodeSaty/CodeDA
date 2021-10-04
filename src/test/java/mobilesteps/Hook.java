package mobilesteps;



import base.BaseClass;
import base.MobileBaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hook extends MobileBaseTest {

    private BaseClass base;
    public Hook(BaseClass base) {
        this.base = base;
    }

    static ThreadLocal<AppiumDriverLocalService> service = new ThreadLocal<>();
    static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    public AppiumDriver getDriver() {
        return driver.get();
    }



    @Before
    public void initializing(Scenario scenario) throws Exception {
        starttheServer();
        base.scenarioDef = base.features.createNode(scenario.getName());
        initializeDriver("Android");
    }

    @After
    public void stopServer() {

        if (driver.get() != null) {
            driver.get().quit();
        }
       afterSuite();

    }




    }





