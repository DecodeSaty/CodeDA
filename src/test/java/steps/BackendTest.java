package steps;


import base.BaseClass;
import com.aventstack.extentreports.GherkinKeyword;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pages.resources.Currency;
import pages.resources.JacksonUtils;
import pages.resources.Utilities;



import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class BackendTest extends Utilities {

    Response response;
    Response responseThird ;
    Currency currency;
    private BaseClass base;
    Response anotherResponse;
    int[]  idArray = new int[3] ;
    JsonPath jsonpath;

    public BackendTest(BaseClass  base) {
        this.base = base;
    }

    @Given("^The crytpo map API$")
    public void iNaviagtetoTheUri() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Given"), "The crytpo map API");
        RestAssured.baseURI = "https://pro-api.coinmarketcap.com";
        response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .param("CMC_PRO_API_KEY", "0961ef6e-b890-47a7-843e-1308f49634d4")
                .when()
                .get("v1/cryptocurrency/map")
                .then().extract().response();
    }

    @When("^Retrieve the ID of bitcoin , usd tether , and Ethereum$")
    public void iRetereivetheID() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("When"), "Retrieve the ID of bitcoin usd tether and Ethereum");

        jsonpath = new JsonPath(response.asString());
        int sizeData = jsonpath.getInt("data.size()");
        System.out.println("Size " + sizeData);

        for (int i = 0; i < sizeData; i++) {
            String symbol = jsonpath.getString("data[" + i + "].symbol");

            if (symbol.equalsIgnoreCase("BTC"))
                idArray[0] = jsonpath.getInt("data[" + i + "].id");
            else if (symbol.equalsIgnoreCase("USDT"))
                idArray[1] = jsonpath.getInt("data[" + i + "].id");
            else if (symbol.equalsIgnoreCase("ETH"))
                idArray[2] = jsonpath.getInt("data[" + i + "].id");
        }

    }


    @Then("^Once you have retrieved the IDs of these currencies, convert them to Bolivian Boliviano$")
    public void iShoulConvertheCurrency() throws Throwable {
       base.scenarioDef.createNode(new GherkinKeyword("Then"), "Once you have retrieved the IDs of these currencies, convert them to Bolivian Boliviano");

       for( int id : idArray) {

           anotherResponse = given()
                   .contentType(ContentType.JSON)
                   .param("CMC_PRO_API_KEY", "0961ef6e-b890-47a7-843e-1308f49634d4")
                   .queryParam("id", id)
                   .queryParam("amount", "1000")
                   .queryParam("convert", "BOB")
                   .when()
                   .get("v1/tools/price-conversion")
                   .then().extract().response();

           System.out.println(anotherResponse.asString());
       }
    }

    @Given("^The crytpo info API Retrieve the Ethereum ID 1027 technical documentation website$")
    public void iShouldSeetheEID() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "The crytpo info API Retrieve the Ethereum ID 1027 technical documentation website");
        RestAssured.baseURI = "https://pro-api.coinmarketcap.com";
          response  =   given()
                .contentType(ContentType.JSON)
                .param("CMC_PRO_API_KEY","0961ef6e-b890-47a7-843e-1308f49634d4")
                .queryParam("id","1027")
                .when()
                .get("v1/cryptocurrency/info")
                .then().extract().response();

    }

    @Then("^Confirm that the following logo URL is present$")
    public void iShouldSeetheLogo() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Confirm that the following logo URL is present");
        jsonpath = new JsonPath(response.asString());
        System.out.println(jsonpath);
        String actualLogo  = jsonpath.getString("data.1027.logo");
        assertEquals(actualLogo,getGlobalValue("expectedLogo") );

    }

    @Then("^Confirm that the technical_doc URL is present$")
    public void iShouldSeeetheDoc() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Confirm that the technical_doc URL is present");
        String actualDocURL = jsonpath.getString("data.1027.urls.technical_doc[0]");
        assertEquals(actualDocURL,getGlobalValue("expectedDocURL") );

    }

    @Then("^Confirm that the symbol of the currency is ETH$")
    public void iShouldSeeTheCorrectSymbol() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Confirm that the symbol of the currency is ETH");
        String actualSymbol = jsonpath.getString("data.1027.symbol");
        assertEquals(actualSymbol,getGlobalValue("expectedSymbol") );
    }

    @Then("^Confirm the date added$")
    public void iShouldSeetheDateAdded() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Confirm the date added");
        String datAdded = jsonpath.getString("data.1027.date_added");
        assertEquals(datAdded,getGlobalValue("dateAdded"));
    }

    @Then("^Confirm that the currency has the mineable tag associated with it$")
    public void iShouldSAeetheRelevanatTag() throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Confirm that the currency has the mineable tag associated with it");
        String availableTags = jsonpath.getString("data.1027.tags");
        assertEquals(true,availableTags.contains(getGlobalValue("tag")));

    }

    @Given("The crytpoInfo  API Retrieve the first {string}  currencies")
    public void iShouldSeetheSysmbols(String index) throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "The crytpoInfo  API Retrieve the first {string}  currencies");
        RestAssured.baseURI = "https://pro-api.coinmarketcap.com";
        responseThird  =   given()
                .contentType(ContentType.JSON)
                .param("CMC_PRO_API_KEY","0961ef6e-b890-47a7-843e-1308f49634d4")
                .queryParam("id", index)
                .when()
                .get("v1/cryptocurrency/info")
                .then().extract().response();

    }


    @Then("Check which currencies have the mineable tag associated with them {string}")
    public void iShouldSeetheTags(String index) throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Check which currencies have the mineable tag associated with them {string}");

        int in = Integer.parseInt(index);
      jsonpath = new JsonPath(responseThird.asString());

      System.out.println(jsonpath);

      String ss = "1" ;
      int gg = 1;
      String availableTags = jsonpath.getString("data."+ index +".tags");
        System.out.println(availableTags);
        assertEquals(true,availableTags.contains(getGlobalValue("tag")));



    }

    @Then("Verify that the correct cryptocurrencies have been printed out {string}")
    public void iVeriftheSysmbols(String index) throws Throwable {
        base.scenarioDef.createNode(new GherkinKeyword("Then"), "Verify that the correct cryptocurrencies have been printed out {string}");
        currency = new Currency(index);
        System.out.println(currency.getCurrency());
        JacksonUtils.deserializeJson("currency.json",Currency[].class);
        String actualSymbol = jsonpath.getString("data."+ index+ ".symbol");
        assertEquals(actualSymbol, currency.getCurrency());

    }
}
