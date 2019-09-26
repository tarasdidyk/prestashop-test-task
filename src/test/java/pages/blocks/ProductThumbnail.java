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

                flag = false;
                String a = element.getText().substring(0, element.getText().indexOf("$"));
                String b = element.getText().substring(element.getText().indexOf("-"), element.getText().indexOf("%"));
                String c = element.getText().substring(element.getText().indexOf("%") + 2, element.getText().lastIndexOf("$") - 1);
                double a1 = DecimalFormat.getNumberInstance().parse(a).doubleValue();
                System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttt");
                System.out.println(a1);
                System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttt");
                double b1 = DecimalFormat.getNumberInstance().parse(b).doubleValue();
                System.out.println(b1);
                System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttt");
                double c1 = DecimalFormat.getNumberInstance().parse(c).doubleValue();
                //double c1 = DecimalFormat.getNumberInstance().parse("1,08").doubleValue();
                System.out.println(c1);
            }
        }
       /* for(String a: lines) {
            System.out.println(a);
        }*/
        return flag;
    }
}
