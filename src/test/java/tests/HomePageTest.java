package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.blocks.Header;
import pages.home.HomePage;
import common.BaseTest;
import utils.Util;
import utils.WebEventListener;
import utils.enums.Currency;

public class HomePageTest extends BaseTest {

    HomePage homePAGE;
    Header header;

    @BeforeMethod
    public void setUp() {
        homePAGE = new HomePage();
        header = new Header();
        homePAGE.openMainUrl();
    }

    @Test(priority = 1)
    public void verifyHomePageTitleTest() {
        String homePageTitle = homePAGE.getTitle();
        Assert.assertEquals(homePageTitle, "prestashop-automation", "Home page title does not matched");
    }

    @Test(priority = 2)
    public void verifySiteCurrencyEqualsProductsCurrencyTest() {
        Assert.assertEquals(homePAGE.getCurrentProductCurrency(),
                homePAGE.getCurrentSiteCurrency(), "Currency does not match");
    }

    @Test(priority = 3)
    public void changeCurrentCurrencyToUsdTest() {
        header.openCurrencyDropDown();
        header.selectUsdCurrency();
        Assert.assertEquals(homePAGE.getCurrentProductCurrency(), Currency.USD.toString(), "Currency is not USD");
    }

}
