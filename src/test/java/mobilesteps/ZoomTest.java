package mobilesteps;


import base.BaseClass;
import base.MobileBaseTest;
import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.ZoomMobilePage;

public class ZoomTest extends MobileBaseTest {


    private BaseClass base;
    ZoomMobilePage zoomPage =  new ZoomMobilePage(getDriver());


    public ZoomTest(BaseClass base) {
        this.base = base;
    }

    @Given("^I launch the zooom app$")
    public void ilaunchtheApplication() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Given"), "I launch the application");

    }


    @When("^Click Join a meeting$")
    public void iClickJoinMetingBUtton() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("When"), "Click Join a meeting");
        zoomPage = zoomPage.clickJoinMeeting();

    }


    @Then("^Join button is disabled$")
    public void iShouldSeeTheButtonDisabled() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Join button is disabled");
        Assert.assertEquals(false, zoomPage.isEnabled());
    }

    @When("^Enter any 9 digits meeting id and Join button is enabled$")
    public void iEntertheIDandJoin() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("When"), "Enter any 9 digits meeting id and Join button is enabled");
        zoomPage = zoomPage.enterMeetingID().hideKeyboards();
        Assert.assertEquals(true, zoomPage.isEnabled());
    }

    @Then("^Toggle on Turn off my video and Click on Join$")
    public void iToggleVideo() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Toggle on Turn off my video and Click on Join");
        zoomPage = zoomPage.toggleMyVideo().joinMeeting();
    }

    @Then("^Put the app in background and launch to foreground Invalid meeting Id text in pop up$")
    public void iPusTheAppinBackGroundandCall() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Put the app in background and launch to foreground Invalid meeting Id text in pop up");
        Assert.assertEquals(true, zoomPage.pushBackandReactivate().getErrorText());

    }


    @When("^Enter any 9 digits meeting id and join the meeting$")
    public void iEnterTheMeetinGODandJoin() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("When"), "Enter any 9 digits meeting id and join the meeting");
        zoomPage = zoomPage.enterMeetingID().hideKeyboards();

    }

    @Then("^Toggle on Turn off my video and Put the app in background$")
    public void iToggelVideoOnOff() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Toggle on Turn off my video and Put the app in background");
        zoomPage = zoomPage.toggleMyVideo().joinMeeting();
    }

    @Then("^launch to foreground Invalid meeting Id text in pop up$")
    public void iShoulSeetheInvalidText() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "launch to foreground Invalid meeting Id text in pop up");
        Assert.assertEquals("Invalid meeting ID. Please check and try again.", zoomPage.pushBackandReactivate().getErrorText());

    }
}
