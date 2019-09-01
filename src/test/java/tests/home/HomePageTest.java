package tests.home;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.HomePage;
import tests.common.BaseTest;
import utils.UtilTest;
import utils.WebEventListener;

public class HomePageTest extends BaseTest {

    HomePage homePAGE;
    WebEventListener listener;
    UtilTest util;

    @BeforeMethod
    public void setUp() {
        homePAGE = new HomePage();
        listener = new WebEventListener();
    }

    @Test
    public void verifyHomePageTitleTest() {
        listener.log("test1");
        String homePageTitle = homePAGE.getTitle();
        listener.log("test2");
        Assert.assertEquals(homePageTitle, "prestashop-automation", "Home page title does not matched");
    }

    @Test
    public void verifySiteCurrencyEqualsProductsCurrencyTest() {
        listener.log("currency comparison");
        Assert.assertEquals(homePAGE.getCurrentProductCurrency(),
                homePAGE.getCurrentSiteCurrency(), "Currency does not match");
    }
}