package steps;

import base.BaseClass;
import com.aventstack.extentreports.GherkinKeyword;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.WebportalPages;

public class WebPortalTest extends BaseClass {

    private  BaseClass base;
    WebportalPages page = new WebportalPages(base.wDriver);

    public WebPortalTest(BaseClass base) {
        this.base = base;
    }

    @Given("^I launch the application$")
    public void ilaunchtheApplication() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Given"), "I launch the application");
        base.wDriver.navigate().to("https://coinmarketcap.com/");
        base.wDriver.manage().window().maximize();
    }


    @And("^I select the Showrows drop dwon value to 100$")
    public void iClickOnShowRows() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("And"), "I select the Showrows drop dwon value to 100");
        //PortalPage page = new PortalPage(base.Driver);
        page =  page.clickonShowRows();
    }


    @Then("^I should see the 100 rows$")
    public void iShouldSee100Records() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "I should see the 100 rows");
        Assert.assertEquals(100,page.getRecordCount()-1);
    }

    @Then("^Click on 'Filters' button$")
    public void iClickOnFilterButtons() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Click on 'Filters' button");
        page = page.clickonFilters();
    }

    @And("^Filter records by MarketCap 1B  10B and Price 101  1000$")
    public void iSelectDifferentFilters() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("And"), "Filter records by 'MarketCap' = $1B - $10B and 'Price': $101 - $1000");
        //PortalPage page = new PortalPage(base.Driver);
        page =  page.setMarketCapFilterValues()
                .setPriceFilterValues().hardWait()
                .clickOnShowResults().hardWait();
    }

    @Then("^Check records displayed on page are correct as per the filter applied$")
    public void iShouldSeetheFilterMachingRecords() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Check records displayed on page are correct as per the filter applied");
        Assert.assertEquals(true,page.validateFiltersReturns());
    }
}
