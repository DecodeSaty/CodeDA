package runner;

import base.ExtentReport;
import com.aventstack.extentreports.gherkin.model.Feature;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static base.BaseClass.features;
import static base.BaseClass.scenarioDef;


public class TesNGlistener implements ITestListener {

    ExtentReport extentReportUtil = new ExtentReport();

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        scenarioDef.pass("Pass");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        scenarioDef.fail("Fail");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        scenarioDef.skip("Skip");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("On test percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("On start");
        extentReportUtil.getReporter();
        features = extentReportUtil.getReporter().createTest(Feature.class, "Mobile Feature");
        features = extentReportUtil.getReporter().createTest(Feature.class, "Web Feature");
        features = extentReportUtil.getReporter().createTest(Feature.class, "Backend Feature");

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

        extentReportUtil.getReporter().flush();
    }
}
