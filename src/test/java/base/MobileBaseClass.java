package base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MobileBaseClass {

    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public MobileBaseClass(AppiumDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 1000);

    }
}
