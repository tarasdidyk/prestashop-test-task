package tests.home;

import common.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.home.SearchResultPage;
import utils.Util;
import utils.WebEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPageTest extends BaseTest {

    HomePage homePage;
    SearchResultPage searchResultPage;
    WebEventListener listener;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        searchResultPage = new SearchResultPage();
        listener = new WebEventListener();
    }

    @Test(priority = 1)
    public void searchByDressTest() {
        String dress = "dress";
        listener.log("search by dress");
        homePage.searchByWords(dress);
    }

    @Test(priority = 2)
    public void countSearchProductsTest() {
        Assert.assertEquals(searchResultPage.getProductListSize(),
                searchResultPage.getSearchResultTitleCount(), "Search result does not match with search title");
    }

    @Test(priority = 3)
    public void productListCurrencyTest() {
        List<WebElement> productList = searchResultPage.getProductPriceList();

        for (WebElement productListItem : productList) {
            Assert.assertEquals(productListItem.getText()
                    .substring(productListItem.getText().length() - 1), "$", "Currency does not match usd");
        }

    }

    @Test(priority = 4)
    public void sortByDescendingTest() {
        searchResultPage.openSortingDropDown();
        searchResultPage.sortByDescending();
        Util.delay(0, 5);
        for (WebElement productListItem : searchResultPage.getProductPriceList()) {
            ArrayList<Double> priceProductItem = new ArrayList<Double>();
            priceProductItem
                    .add(Double.parseDouble(productListItem.getText().replaceAll("[\\D]", "")));
            System.out.println(priceProductItem);
        }
    }
}

