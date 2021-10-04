package base;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;

public class BaseClass {

    public  static AppiumDriver Driver;

    public  static WebDriver wDriver;
    public static ExtentTest scenarioDef;
    public static ExtentTest features;
    public static String reportLocation = "..//Mobile-Assignment-Cucumber/Report/Test-Report.html";

}
