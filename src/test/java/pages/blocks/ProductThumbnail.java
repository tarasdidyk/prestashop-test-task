package pages.blocks;

import common.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pages.search.SearchResultPage;
import utils.Util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProductThumbnail extends BaseTest {

    public ProductThumbnail() {
        PageFactory.initElements(driver, this);
    }

    @Step("Checking sort by descending")
    public boolean isSortByDescending() throws ParseException {

        boolean flag = false;

        SearchResultPage searchResultPage = new SearchResultPage();
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
        if (count == productPriceWithOutDiscount.size() - 1) {
            flag = true;
        }
        return flag;
    }

    @Step("Checking discount is expected")
    public boolean discountBeforeAfterEqualToDiscountAmount() throws ParseException {
        SearchResultPage searchResultPage = new SearchResultPage();
        boolean flag = true;
        for (WebElement element : searchResultPage.getProductPriceList()) {
            if (element.getText().contains("%")) {

                double priceBeforeDiscountInCent = Math.round(100 * DecimalFormat.getNumberInstance()
                        .parse(element.getText().substring(0, element.getText().indexOf("$"))).doubleValue());
                double discountInPercent = Math.round(-1 * DecimalFormat.getNumberInstance()
                        .parse(element.getText().substring(element.getText().indexOf("-"), element.getText().indexOf("%"))).doubleValue());
                double priceAfterDiscountInCent = Math.round(100 * DecimalFormat.getNumberInstance()
                        .parse(element.getText().substring(element.getText().indexOf("%") + 2, element.getText().lastIndexOf("$") - 1)).doubleValue());

                if (Math.round(priceBeforeDiscountInCent - priceAfterDiscountInCent) != Math.round(priceBeforeDiscountInCent / 100 * (discountInPercent))) {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
