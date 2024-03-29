package pages.blocks;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import common.BaseTest;

public class Header extends BaseTest {

    public Header() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@id,'_desktop_currency_selector')]//a[contains(@class,'hidden-sm-down')]")
    WebElement currencyDropDown;

    @FindBy(xpath = "//div[contains(@class,'currency-selector')]//a[contains(text(),'USD $')]")
    WebElement usdCurrencyDropDownItem;

    @Step("Open the Currency dropdown")
    public void openCurrencyDropDown() {
        currencyDropDown.click();
    }

    @Step("Select the USD currency in the CurrencyDropDown")
    public void selectUsdCurrency() {
        usdCurrencyDropDownItem.click();
    }

}
