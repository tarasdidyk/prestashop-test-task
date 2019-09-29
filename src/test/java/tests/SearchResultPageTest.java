package tests;

import common.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.blocks.ProductThumbnail;
import pages.home.HomePage;
import pages.search.SearchResultPage;
import utils.enums.SearchWord;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SearchResultPageTest extends BaseTest {

    HomePage homePage;
    SearchResultPage searchResultPage;
    ProductThumbnail productThumbnail;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        searchResultPage = new SearchResultPage();
        productThumbnail = new ProductThumbnail();
        homePage.openMainUrl();
    }

    @Test(priority = 1)
    public void searchByDressTest() {
        homePage.searchByWords(SearchWord.DRESS.toString());
    }

    @Test(priority = 2)
    public void countSearchProductsTest() {
        homePage.searchByWords(SearchWord.DRESS.toString());
        Assert.assertEquals(searchResultPage.getProductListSize(),
                searchResultPage.getSearchResultTitleAmount(), "Search result does not match with search title");
    }

    @Test(priority = 3)
    public void productListCurrencyTest() {
        homePage.searchByWords(SearchWord.DRESS.toString());
        List<WebElement> productList = searchResultPage.getProductPriceList();

        for (WebElement productListItem : productList) {
            Assert.assertEquals(productListItem.getText()
                    .substring(productListItem.getText().length() - 1), "$", "Currency does not match usd");
        }
    }

    @Test(priority = 4)
    public void sortByDescendingTest() throws ParseException {
        homePage.searchByWords(SearchWord.DRESS.toString());
        searchResultPage.openSortingDropDown();
        searchResultPage.sortByDescending();
        Assert.assertTrue(productThumbnail.isSortByDescending(), "Products are not sorted high to low");
    }

    @Test(priority = 5)
    public void productsWithDiscountTest() throws IOException, ParseException {
        homePage.searchByWords(SearchWord.DRESS.toString());
        Assert.assertTrue(productThumbnail.discountBeforeAfterEqualToDiscountAmount(), "Discount is not equals");
    }
}

