package pages.home;

import common.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends BaseTest {

    @FindBy(xpath = "//div[contains(@id, 'js-product-list-top')]//p[contains(text,\"\")]")
    private WebElement totalProducts;

    public String getTotalProductsText() {
       return totalProducts.getText();
    }
}
