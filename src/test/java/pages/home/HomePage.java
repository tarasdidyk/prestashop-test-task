package pages.home;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.BaseTest;
import utils.Util;
import utils.enums.Currency;

public class HomePage extends BaseTest {

    @FindBy(xpath = "//span[contains(@class,'price')]")
    private WebElement productsPrice;

    @FindBy(xpath = "//span[contains(@class, 'expand-more _gray-darker hidden-sm-down')]")
    private WebElement currencyDropDown;

    @FindBy(xpath = "//div[contains(@id, 'search_widget')]//input[contains(@type, 'text')]")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@id, 'search_widget')]//button[contains(@type, 'submit')]")
    private WebElement searchButton;

    @FindBy(xpath = "//header[contains(@id, 'header')]")
    private WebElement header;

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Get page Title")
    public String getTitle() {
        return driver.getTitle();
    }

    @Step("Get actual products currency")
    public String getCurrentProductCurrency() {

        if (currencyDropDown.getText().contains(Currency.UAH.toString())) {
            return Currency.UAH.toString();
        }

        if (currencyDropDown.getText().contains(Currency.USD.toString())) {
            return Currency.USD.toString();
        }

        if (currencyDropDown.getText().contains(Currency.EUR.toString())) {
            return Currency.EUR.toString();
        }
        return null;
    }

    @Step("Get site actual currency")
    public String getCurrentSiteCurrency() {

        if (productsPrice.getText().contains(Currency.UAH.toString())) {
            return Currency.UAH.toString();
        }
        if (productsPrice.getText().contains(Currency.USD.toString())) {
            return Currency.USD.toString();
        }
        if (productsPrice.getText().contains(Currency.EUR.toString())) {
            return Currency.EUR.toString();
        }
        return null;
    }

    @Step("Search by word")
    public void searchByWords(String searchQuery) {
        searchField.sendKeys(searchQuery);
        searchButton.click();
    }

    @Step("Open main URL")
    public void openMainUrl() {
        driver.get(prop.getProperty("url"));
        if (!header.isDisplayed()) {
            Util.reloadPage();
        }
    }
}
