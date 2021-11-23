package computer;

import com.google.common.base.Verify;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities_self.Utility;

import java.awt.*;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {

        //using utility method doClickOnElement
        doClickOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));

        doClickOnElement(By.xpath("//div[@class='block block-category-navigation']/descendant::a[2]"));

        doSelectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");

        String expectedMessage = "Name: Z to A";
        Thread.sleep(2000);
        String actualMessage = doGetTextFromElement(By.xpath("//option[contains(text(),'Name: Z to A')]"));
        doAssertToVerifyElements("Name:Z to A has not been arranged in descending order.", "Name: Z to A", By.xpath("//option[contains(text(),'Name: Z to A')]"));

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessfully() throws InterruptedException {

        //calling verifyProductArrangedInAlphabeticalOrder method from above for steps 2.1 to 2.3
        verifyProductArrangeInAlphabeticalOrder();

        //2.4
        doClickOnElement(By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000020_build-your-own-computer_415.jpeg']"));

        //2.5 doAssert method from utility
        doAssertToVerifyElements("User has not navigated to next page", "Build your own computer", By.xpath("//h1[contains(text(),'Build your own computer')]"));

        //2.6
        doSelectByVisibleTextFromDropDown(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7
        doSelectByIndexFromDropDown(By.id("product_attribute_2"), 3);

        //2.8
        doClickOnElement(By.id("product_attribute_3_7"));

        //2.9
        doClickOnElement(By.id("product_attribute_4_9"));

        //2.10
        //doClickOnElement(By.id("product_attribute_5_10"));
        // Thread.sleep(2000);
        doClickOnElement(By.id("product_attribute_5_12"));
        Thread.sleep(4000);
        //doClickOnElement(By.id("product_attribute_5_10"));
        //Thread.sleep(4000);

        //2.11
        doAssertToVerifyElements("Inaccurate total", "$1,475.00", By.xpath("//span[@id='price-value-1']"));

        //2.12
        doClickOnElement(By.id("add-to-cart-button-1"));

        //2.13
        //String expectedMessage2 = "The product has been added to your shopping cart";
        //String actualMessage2 = getTextFromElement(By.xpath("//p[text()='The product has been added to your ']"));
        //Assert.assertEquals(, expectedMessage2, actualMessage2);
        doAssertToVerifyElements("Product has not been added to the cart", "The product has been added to your shopping cart", By.xpath("//p[text()='The product has been added to your ']"));

        //2.13
        doClickOnElement(By.xpath("//span[@title='Close']"));
        Thread.sleep(1500);

        //2.14
        doMouseHoverOnFirstThenSecondAndClick(By.cssSelector(".cart-label"), By.cssSelector(".button-1.cart-button"));

        //2.15 Verify the message "Shopping cart"
        doAssertToVerifyElements("Shopping cart is not displayed correctly", "Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));

        //2.16
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();//clearing the existing data
        Thread.sleep(1000);
        doSendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        doClickOnElement(By.xpath("//div[@class='common-buttons']/child::button[1]"));
        Thread.sleep(1000);

        //2.17
        doAssertToVerifyElements("The order total is not correct", "$2,950.00", By.xpath("//span[@class='product-subtotal']"));

        //2.18
        doClickOnElement(By.id("termsofservice"));

        //2.19
        doClickOnElement(By.id("checkout"));

        //2.20
        doAssertToVerifyElements("User is not on the Sign in Page", "Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));

        //2.21
        doClickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        //2.22
        doSendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Jolly");//firstname
        doSendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "James");//lastname
        doSendTextToElement(By.cssSelector("#BillingNewAddress_Email"), emailString() + "@gmail.com");//random email selection
        doSelectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        doSendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");//city
        doSendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "999 Prime Road");//Address1
        doSendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "LL5 5LU");//Zip/Postal code
        doSendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07905698312");//Phone number

        //2.23
        doClickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //2.24
        doClickOnElement(By.id("shippingoption_1"));

        //2.25
        doClickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.26
        doClickOnElement(By.id("paymentmethod_1"));
        doClickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));

        //2.27
        doSelectByIndexFromDropDown(By.xpath("//select[@id='CreditCardType']"), 1);
        //2.28
        doSendTextToElement(By.xpath("//input[@id='CardholderName']"), "Jolly James");
        doSendTextToElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        doSendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "01");
        doSendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2022");
        doSendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        //2.29
        doClickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.30
        doAssertToVerifyElements("Payment method is displayed incorrectly", "Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"));
        //2.32
        doAssertToVerifyElements("Shipping Method is displayed incorrectly", "Next Day Air", By.xpath("//span[contains(text(),'\n" +
                "                                Next Day Air\n" +
                "                            ')]"));
        Thread.sleep(1000);
        //2.33
        doAssertToVerifyElements("Total Amount is displayed incorrectly", "$2,950.00", By.xpath("//span[contains(text(),'$2,950.00')]"));
        Thread.sleep(500);

        //2.34
        doClickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.35
        doAssertToVerifyElements("Thank You Message is incorrectly displayed", "Thank you", By.xpath("//h1[contains(text(),'Thank you')]"));

        //2.36
        doAssertToVerifyElements("Order has not been processed successfully", "Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));

        //2.37
        doClickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //2.37
        doAssertToVerifyElements("Welcome to our store has been incorrectly displayed", "Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}


