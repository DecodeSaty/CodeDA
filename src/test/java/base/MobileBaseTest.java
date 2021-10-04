package base;


import com.aventstack.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class MobileBaseTest {

    protected static ThreadLocal <AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
    protected static ThreadLocal <Properties> props = new ThreadLocal<Properties>();
    protected static ThreadLocal <HashMap<String, String>> strings = new ThreadLocal<HashMap<String, String>>();
    protected static ThreadLocal <String> platform = new ThreadLocal<String>();
    protected static ThreadLocal <String> dateTime = new ThreadLocal<String>();
    protected static ThreadLocal <String> deviceName = new ThreadLocal<String>();
    private static AppiumDriverLocalService serverAndroid;
    protected static ExtentTest test;


    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver2) {
        driver.set(driver2);
    }
   /* public Properties getProps() {
        return props.get();
    }
    public void setProps(Properties props2) {
        props.set(props2);
    }
    public HashMap<String, String> getStrings() {
        return strings.get();
    }
    public void setStrings(HashMap<String, String> strings2) {
        strings.set(strings2);
    }
    public String getPlatform() {
        return platform.get();
    }
    public void setPlatform(String platform2) {
        platform.set(platform2);
    }
    public String getDateTime() {
        return dateTime.get();
    }
    public void setDateTime(String dateTime2) {
        dateTime.set(dateTime2);
    }
    public String getDeviceName() {
        return deviceName.get();
    }
    public void setDeviceName(String deviceName2) {
        deviceName.set(deviceName2);
    }*/

    public MobileBaseTest() {
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }



    public void starttheServer() throws Exception, Exception {

        serverAndroid = getAppiumService(4723);
        if(!(checkIfAppiumServerIsRunnning(4723)))  {
            serverAndroid.stop();
            serverAndroid.start();
            serverAndroid.clearOutPutStreams();
        }

    }

    public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
        boolean isAppiumServerRunning = false;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.close();
        } catch (IOException e) {
            isAppiumServerRunning = true;
        } finally {
            socket = null;
        }
        return isAppiumServerRunning;
    }

    @AfterSuite()
    public void afterSuite() {
        serverAndroid.stop();


    }

    public AppiumDriverLocalService getAppiumService(int port) {
        HashMap<String, String> environment = new HashMap<String, String>();
        environment.put("PATH", "/Library/Internet Plug-Ins/JavaAppletPlugin.plugin/Contents/Home/bin:/Users/sathya/Library/Android/sdk/tools:/Users/sathya/Library/Android/sdk/platform-tools:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin" + System.getenv("PATH"));
        environment.put("ANDROID_HOME", "/Users/sathya/Library/Android/sdk");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .usingPort(port)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withEnvironment(environment)
                .withLogFile(new File("ServerLogs/server.log")));
    }



    public void initializeDriver(String PlatformName) throws Exception {

        URL url;
        InputStream inputStream = null;
        InputStream stringsis = null;
        Properties props = new Properties();
        AppiumDriver driver;
        try {
            props = new Properties();
            String propFileName = "config.properties";
            String xmlFileName = "strings/strings.xml";


            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            url = new URL("http://localhost:4723/wd/hub");

            switch(PlatformName) {
                case "Android":
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"ANDROID");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel_XL_API_28:5554");
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    desiredCapabilities.setCapability( MobileCapabilityType.APP, "/Users/sathya/Documents/AbuDhabhi/Mobile-Backend-Web/src/test/resourcesApp/Zoom.apk");
                    desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET,true );
                    desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,150000);
                    url = new URL("http://localhost:4723/wd/hub");
                    driver = new AppiumDriver<MobileElement>(url, desiredCapabilities);
                    break;
                case "iOS":
                   // IOS DesiredCapability section
                default:
                    throw new Exception("Invalid platform! - " + PlatformName);
            }
            setDriver(driver);


        } catch (Exception e) {
            throw e;
        } finally {
            if(inputStream != null) {
                inputStream.close();
            }
            if(stringsis != null) {
                stringsis.close();
            }
        }
    }


}
