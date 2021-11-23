package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities_self.Utility;

import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    /**
     * This method will click on the menu depending on whatever string
     * is passed as parameter
     * @param menu
     */
    public void selectMenu(String menu) {

        List<WebElement> topMenuNames = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']//li"));

        for (WebElement name : topMenuNames) {
           if (name.getText().equalsIgnoreCase(menu)) {
             name.click();
                break;
            }
        }
    }

    @Test
    public void verifyComputersPageNavigation()  {

        selectMenu("Computers");
        //using assert method from utility for Verification
        doAssertToVerifyElements("User has not navigated to the Computers Page","Computers", By.partialLinkText("Computers"));

    }

    @Test
    public void verifyElectronicsPageNavigation() {
        selectMenu("Electronics");
        doAssertToVerifyElements("User has not navigated to the Electronics Page", "Electronics", By.partialLinkText("Electronics"));
    }

    @Test
    public void verifyApparelPageNavigation() {
        selectMenu("Apparel");
        doAssertToVerifyElements("User has not navigated to the Apparel Page", "Apparel",By.partialLinkText("Apparel"));
    }

    @Test
    public void verifyDigitalDownloadsPageNavigation() {
        selectMenu("Digital downloads");
        doAssertToVerifyElements("User has not navigated to the Digital downloads Page", "Digital downloads",By.partialLinkText("Digital downloads"));
    }

    @Test
    public void verifyBooksPageNavigation() {
        selectMenu("Books");
        doAssertToVerifyElements("User has not navigated to the Books Page", "Books",By.partialLinkText("Books"));
    }

    @Test
    public void verifyJewelryPageNavigation() {
        selectMenu("Jewelry");
        doAssertToVerifyElements("User has not navigated to the Jewellery Page", "Jewelry",By.partialLinkText("Jewelry"));
    }

    @Test
    public void verifyGiftPageNavigation() {
        selectMenu("Gift Cards");
        doAssertToVerifyElements("User has not navigated to the Gift Cards Page", "Gift Cards",By.partialLinkText("Gift Cards"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
