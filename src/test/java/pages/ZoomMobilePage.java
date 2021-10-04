package pages;

import base.MobileBaseClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ZoomMobilePage extends MobileBaseClass {

    private final By joinaMeetingBtn = MobileBy.id("us.zoom.videomeetings:id/btnJoinConf");
    private final By joinBtn = MobileBy.id("us.zoom.videomeetings:id/btnJoin");
    private final By meeetingIDfield = MobileBy.id("us.zoom.videomeetings:id/edtConfNumber");
    private final By turnOffVideoToggle = MobileBy.id("us.zoom.videomeetings:id/chkNoVideo");
    private final By unabletoConnectTxt = MobileBy.id("us.zoom.videomeetings:id/txtMsg");

    public ZoomMobilePage(AppiumDriver driver) {
        super(driver);
    }

    public ZoomMobilePage clickJoinMeeting()
    {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(joinaMeetingBtn));
        e.click();
        return this ;
    }
    public ZoomMobilePage enterMeetingID()
    {

        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(meeetingIDfield));
        e.sendKeys("1200000000");
        return this ;
    }

    public ZoomMobilePage toggleMyVideo()
    {


        wait.until(ExpectedConditions.elementToBeClickable(turnOffVideoToggle)).click();
        return this ;
    }

    public ZoomMobilePage joinMeeting()
    {
        wait.until(ExpectedConditions.elementToBeClickable(joinBtn)).click();
        return this ;
    }

    public boolean isEnabled()
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(joinBtn)).isEnabled();

    }

    public String getErrorText()
    {

        return wait.until(ExpectedConditions.elementToBeClickable(unabletoConnectTxt)).getText();
    }

    public ZoomMobilePage hideKeyboards()
    {
        driver.hideKeyboard();
        return this ;
    }

    public ZoomMobilePage pushBackandReactivate()
    {
        hardWait();
        driver.runAppInBackground(Duration.ofMillis(15000));
        driver.activateApp("us.zoom.videomeetings");
        return this ;
    }

    public ZoomMobilePage hardWait()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this ;
    }
}
