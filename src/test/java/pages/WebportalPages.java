package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebportalPages {

    WebDriverWait wait;

    public WebportalPages(WebDriver driver) {

        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver,380);
    }

    // Elements of Test Case 1

    @FindBy(how = How.XPATH, using = "//div[@class='sc-16r8icm-0 ekMmID table-control-page-sizer']//div[@class='sc-16r8icm-0 tu1guj-0 cSTqvK'][normalize-space()='100']")
    public WebElement dropDown;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='100']")
    public WebElement dropDownValue50;

    @FindBy(how = How.CSS, using = "[class='h7vnx2-2 czTsgW cmc-table  '] tr")
    public List<WebElement> recordsNo;


    // Elements of test case 2


    @FindBy(how = How.XPATH, using = "//body//div//div//div//div//div//div//div//div//div//div//button[contains(text(),'Filters')]")
    public WebElement filterBtn;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='+ Add Filter']")
    public WebElement addFilterBtn;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Market Cap']")
    public WebElement marketCapBTN;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$0']")
    public WebElement minInputFld;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$999,999,999,999']")
    public WebElement maxInputFld;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Apply Filter']")
    public WebElement applyFilter;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Price']")
    public WebElement priceBtn;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Show results']")
    public WebElement showResultBtn;

    @FindBy(how = How.XPATH, using = "//div[5]/table[1]/tbody[1]/tr")
    public List<WebElement> tableRecordRow;

    @FindBy(how = How.TAG_NAME, using = "td")
    public List<WebElement> tabelRecordColumn;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$99,999']")
    public WebElement maxNumFld ;


    public WebportalPages clickonShowRows()
    {
        wait.until(ExpectedConditions.elementToBeClickable(dropDown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dropDownValue50)).click();
        return this ;
    }

    public int getRecordCount()
    {

        return hardWait().recordsNo.size();
    }

    //TestCase 2 - Methods

    public WebportalPages clickonFilters()
    {
        wait.until(ExpectedConditions.elementToBeClickable(filterBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addFilterBtn)).click();
        return this ;
    }

    public WebportalPages setMarketCapFilterValues()
    {
        wait.until(ExpectedConditions.elementToBeClickable(marketCapBTN)).click();
        wait.until(ExpectedConditions.elementToBeClickable(minInputFld)).sendKeys("1000000000");
        wait.until(ExpectedConditions.elementToBeClickable(maxInputFld)).sendKeys("10000000000");
        wait.until(ExpectedConditions.elementToBeClickable(applyFilter)).click();
        return this ;
    }

    public WebportalPages setPriceFilterValues()
    {


        wait.until(ExpectedConditions.elementToBeClickable(priceBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(minInputFld)).sendKeys("1001");
        wait.until(ExpectedConditions.elementToBeClickable(maxNumFld)).sendKeys("10000");
        wait.until(ExpectedConditions.elementToBeClickable(applyFilter)).click();
        return this ;
    }

    public WebportalPages hardWait()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this ;
    }

    public WebportalPages clickOnShowResults()
    {

        hardWait().wait.until(ExpectedConditions.elementToBeClickable(showResultBtn)).click();
        return this ;
    }

    public boolean validateFiltersReturns(){

        List<WebElement> records = tableRecordRow ;
        boolean flag = false ;
        int i = 0 ;

        for (WebElement element : records) {
            WebElement row =  records.get(i);

            List<WebElement> column = hardWait().wait.until(ExpectedConditions.visibilityOfAllElements(tabelRecordColumn));

            String priceTxt = column.get(3).getText();
            String mCapTxt = column.get(6).getText();

            String priceSubstring = priceTxt.substring(1);
            String priceReplaced = priceSubstring.replace(",","");

            String mCapSubstring = mCapTxt.substring(7);
            String mCapRepalced = mCapSubstring.replace(",","");

            Float finalPrice= Float.parseFloat(priceReplaced);
            Long finalMcao = Long.parseLong(mCapRepalced);

            if (( finalPrice > 1001 && finalPrice < 10000 ) && (finalMcao > 100000000) && (finalMcao < 10000000000L))
                flag = true ;

            i++;

        }

        return flag ;

    }

}
