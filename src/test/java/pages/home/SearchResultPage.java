package pages.home;

import common.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BaseTest {


    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@id, 'js-product-list-top')]//p")
    private WebElement totalProducts;

    public String getTotalProductsText() {
       return totalProducts.getText();
    }
}
