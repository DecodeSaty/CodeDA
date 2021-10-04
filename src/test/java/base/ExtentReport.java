package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class ExtentReport  extends BaseClass{


    static ExtentReports extent;
    final static String macPath = System.getProperty("user.dir") + "//Report//Test-Report.html";
    static Map<Integer, ExtentTest> extentTestMap = new HashMap();

    public  ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter html = new ExtentSparkReporter(macPath);;
            extent = new ExtentReports();
            extent.attachReporter(html);
        }

        return extent;
    }

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }


    public void ExtentReportScreenshot() throws IOException {

        var scr = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scr.toPath(), new File(reportLocation + "screenshot.png").toPath());
        scenarioDef.fail("details").addScreenCaptureFromPath(reportLocation + "screenshot.png");
    }


    public void FlushReport(){
        extent.flush();
    }



}
