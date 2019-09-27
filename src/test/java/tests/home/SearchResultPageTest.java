package tests.home;

import common.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.blocks.ProductThumbnail;
import pages.home.HomePage;
import pages.search.SearchResultPage;
import utils.Util;
import utils.WebEventListener;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPageTest extends BaseTest {

    HomePage homePage;
    SearchResultPage searchResultPage;
    WebEventListener listener;
    ProductThumbnail productThumbnail;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        searchResultPage = new SearchResultPage();
        listener = new WebEventListener();
        productThumbnail = new ProductThumbnail();
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
    public void sortByDescendingTest() throws ParseException {
        searchResultPage.openSortingDropDown();
        searchResultPage.sortByDescending();
        List<Double> productPriceWithOutDiscount = new ArrayList<>();

        Util.delay(0, 5);
        for (WebElement productListItem : searchResultPage.getProductPriceList()) {
            String b = productListItem.getText().substring(0, productListItem.getText().indexOf("$"));
            double c = DecimalFormat.getNumberInstance().parse(b).doubleValue();
            productPriceWithOutDiscount.add(c);
        }
        int count = 0;
        for (int listItem = 0; listItem < productPriceWithOutDiscount.size() - 1; listItem++) {
            if (productPriceWithOutDiscount.get(listItem) >= productPriceWithOutDiscount.get(listItem + 1)) {
                count++;
            }
        }
        Assert.assertEquals(count, productPriceWithOutDiscount.size() - 1, "Products did not sort from high to low price");
    }

    @Test(priority = 5)
    public void productsWithDiscountTest() throws IOException, ParseException {
      Assert.assertEquals(productThumbnail.discountBeforeAfterEqualToDiscountAmount(), "Discount is not equals");
    }
}

