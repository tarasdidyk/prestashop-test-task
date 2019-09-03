package pages.blocks;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.common.BaseTest;

public class Header extends BaseTest {


    public Header() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@id,'desktop_currency_selector')]")
    WebElement currencyDropDown;


    @FindBy(xpath = ".//a[contains(@class, 'dropdown-item')]")
    WebElement usdCurrencyDropDownItem;

    @Step
    public void openCurrencyDropDown() {
        currencyDropDown.click();
    }

    @Step
    public void selectUsdCurrency() {
        usdCurrencyDropDownItem.click();
    }


}
