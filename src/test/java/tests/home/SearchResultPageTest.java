package tests.home;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.home.SearchResultPage;
import utils.WebEventListener;

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


    @Test
    public void searchByDressTest() {
        String dress = "dress";
        listener.log("search by dress");
        homePage.searchByWords(dress);
        searchResultPage.getTotalProductsText();
    }

    @Test
    public void countSearchProductsTest() {
       // searchResultPage.getTotalProductsText();

    }
 }
