package pages.search;

import common.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SearchResultPage extends BaseTest {

    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@id, 'js-product-list-top')]")
    private WebElement searchResultTitle;

    @FindBy(xpath = "//div[contains(@class, 'product-price-and-shipping')]")
    private List<WebElement> productsPriceList;

    @FindBy(xpath = "//article[contains(@class, 'product-miniature js-product-miniature')]")
    private List<WebElement> productList;

    @FindBy(xpath = "//a[contains(@class, 'select-title')]")
    private WebElement sortingDropDownList;

    @FindBy(xpath = "//a[contains(text(), 'Цене: от высокой к низкой')]")
    private WebElement sortItemByDescending;


    public String getWebElementText(WebElement element) {
        return element.getText();
    }

    @Step("Get search result amount")
    public int getSearchResultTitleAmount() {
        return Integer.parseInt(getWebElementText(searchResultTitle).replaceAll("[\\D]", ""));
    }

    @Step("Get list size")
    public int getWebElementListSize(List<WebElement> element) {
        return element.size();
    }

    @Step("Get product list size")
    public int getProductListSize() {
        return getWebElementListSize(productList);
    }

    @Step("Get product price list")
    public List<WebElement> getProductPriceList() {
        return productsPriceList;
    }

    @Step("Open Sort dropdown")
    public void openSortingDropDown() {
        sortingDropDownList.click();
    }

    @Step("Sorting by Descending")
    public void sortByDescending() {
        sortingDropDownList.isEnabled();
        sortItemByDescending.click();
    }
}
