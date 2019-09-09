package pages.home;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.BaseTest;

public class HomePage extends BaseTest {

    @FindBy(xpath = "//span[contains(@class,'price')]")
    private WebElement productsPrice;

    @FindBy(xpath = "//span[contains(@class, 'expand-more _gray-darker hidden-sm-down')]")
    private WebElement currencyDropDown;

    @FindBy(xpath = "//div[contains(@id, 'search_widget')]//input[contains(@type, 'text')]")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@id, 'search_widget')]//button[contains(@type, 'submit')]")
    private WebElement searchButton;

    // init Page objects
    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @Step
    public String getTitle() {
        return driver.getTitle();
    }

    @Step
    public String getCurrentProductCurrency() {
        String UAH = "₴";
        String USD = "$";
        String EUR = "€";

        if (currencyDropDown.getText().contains(UAH)) {
            return UAH;
        }

        if (currencyDropDown.getText().contains(USD)) {
            return USD;
        }

        if (currencyDropDown.getText().contains(EUR)) {
            return EUR;
        }

        return null;
    }

    @Step
    public String getCurrentSiteCurrency() {
        String UAH = "₴";
        String USD = "$";
        String EUR = "€";

        if (productsPrice.getText().contains(UAH)) {
            return UAH;
        }
        if (productsPrice.getText().contains(USD)) {
            return USD;
        }
        if (productsPrice.getText().contains(EUR)) {
            return EUR;
        }
        return null;
    }

    @Step
    public void searchByWords(String searchQuery) {
        searchField.sendKeys(searchQuery);
        searchButton.click();
    }

    public void openMainUrl() {
            driver.get(prop.getProperty("url"));
    }

}
