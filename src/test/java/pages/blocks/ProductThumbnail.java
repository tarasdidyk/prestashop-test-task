package pages.blocks;

import common.BaseTest;
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

    public void sortByDescendingTest() throws ParseException {
        SearchResultPage searchResultPage = new SearchResultPage();
        searchResultPage.openSortingDropDown();
        searchResultPage.sortByDescending();
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
        Assert.assertEquals(count, productPriceWithOutDiscount.size() - 1, "Products did not sort from high to low price");
    }

    public boolean discountBeforeAfterEqualToDiscountAmount() throws IOException, ParseException {
        SearchResultPage searchResultPage = new SearchResultPage();
        boolean flag = true;
        for (WebElement element : searchResultPage.getProductPriceList()) {
            if (element.getText().contains("%")) {

                double priceBeforeDiscount = DecimalFormat.getNumberInstance()
                        .parse(element.getText().substring(0, element.getText().indexOf("$"))).doubleValue();
                double discountInPercent = DecimalFormat.getNumberInstance()
                        .parse(element.getText().substring(element.getText().indexOf("-"), element.getText().indexOf("%"))).doubleValue();
                double priceAfterDiscount = DecimalFormat.getNumberInstance()
                        .parse(element.getText().substring(element.getText().indexOf("%") + 2, element.getText().lastIndexOf("$") - 1)).doubleValue();

                System.out.println((priceBeforeDiscount - priceAfterDiscount));
                discountInPercent= discountInPercent*-1;
                System.out.println((priceBeforeDiscount / 100 * discountInPercent));

                if ((Math.round(priceBeforeDiscount - priceAfterDiscount)) != Math.round(priceBeforeDiscount / 100 * (discountInPercent*-1))) {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
