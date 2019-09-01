package pages.home;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.common.BaseTest;

public class HomePage extends BaseTest {

    @FindBy(xpath = "//span[contains(@class,'price')]")
    private WebElement productsPrice;

    @FindBy(xpath = "//span[contains(@class, 'expand-more _gray-darker hidden-sm-down')]")
    private WebElement currencyDropDown;

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
}