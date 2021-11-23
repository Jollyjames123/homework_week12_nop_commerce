package electronics;

import com.google.common.base.Verify;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities_self.Utility;

public class ElectronicsTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        doMouseHoverOnFirstThenSecondAndClick(By.linkText("Electronics"), By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        doAssertToVerifyElements("Cell phones message is not displayed correctly", "Cell phones", By.xpath("//h1[contains(text(),'Cell phones')]"));
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        //calling method from above for steps 2.1, 2.2, 2.3
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();

        //2.4
        doClickOnElement(By.xpath("//a[contains(text(),'List')]"));
        Thread.sleep(2000);
        //2.5
        doClickOnElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"));

        //2.6
        doAssertToVerifyElements("Nokia Lumia 1020 is displayed incorrectly", "Nokia Lumia 1020", By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        //2.7
        doAssertToVerifyElements("Price is displayed incorrectly", "$349.00", By.xpath("//span[@id='price-value-20']"));

        //2.8
        driver.findElement(By.id("product_enteredQuantity_20")).clear();
        doSendTextToElement(By.id("product_enteredQuantity_20"), "2");
        //2.9
        doClickOnElement(By.id("add-to-cart-button-20"));

        //2.10
        doAssertToVerifyElements("Product is not added", "The product has been added to your shopping cart", By.xpath("//p[@class='content']"));
        doClickOnElement(By.cssSelector("span[title='Close']"));

        //2.11
        doMouseHoverAction(By.xpath("//span[text()='Shopping cart']"));
        Thread.sleep(1000);
        doClickOnElement(By.xpath("//button[text()='Go to cart']"));

        //2.12 Verify the message "Shopping cart"
	    doAssertToVerifyElements("Shopping cart displayed incorrectly", "Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));

        //2.13
        //verifyElements("2", By.xpath("//tbody/tr[1]/td[5]/input[1]"), "Quantity is displayed incorrectly");
        //verified using the get attribute method
        String expectedMessage = "2";
        String actualMessage = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/input[1]")).getAttribute("value");
        Assert.assertEquals("Quantity is not correct", expectedMessage, actualMessage);
        Thread.sleep(500);
        //2.14
        doAssertToVerifyElements("Amount is displayed incorrectly", "$698.00", By.cssSelector(".product-subtotal"));

        //2.15, 2.16
        doClickOnElement(By.id("termsofservice"));
        doClickOnElement(By.id("checkout"));

        //2.17
        doAssertToVerifyElements("User is not on the Sign in Page", "Welcome, Please Sign In!", By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));

        //2.18
        doClickOnElement(By.xpath("//button[contains(text(),'Register')]"));

        //2.19
        doAssertToVerifyElements("Registration message is not displayed correctly", "Register", By.xpath("//h1[contains(text(),'Register')]"));


        //2.20 Fill the all mandatory field
        doSendTextToElement(By.xpath("//input[@id='FirstName']"), "Jolly");//firstname
        doSendTextToElement(By.xpath("//input[@id='LastName']"), "James");//lastname
        doSendTextToElement(By.cssSelector("#Email"), emailString() + "@gmail.com");//random email selection
        doSendTextToElement(By.xpath("//input[@id='Password']"), "asdfgh");
        doSendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "asdfgh");

        //2.21Click on “REGISTER”
        doClickOnElement(By.xpath("//button[@id='register-button']"));
        Thread.sleep(500);

        //2.22Verify the message “Your registration completed”
        doAssertToVerifyElements("Registration is not completed", "Your registration completed", By.xpath("//div[@class='result']"));

        //2.23Click on Continue Tab
        doClickOnElement(By.xpath("//a[contains(text(),'Continue')]"));

        //2.24Verify the text "Shopping Cart"
        doAssertToVerifyElements("Shopping cart is not displayed", "Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));

        //2.25
        doClickOnElement(By.xpath("//input[@id='termsofservice']"));
        //2.26Click on “CHECKOUT”
        doClickOnElement(By.xpath("//button[@id='checkout']"));

        //2.27
        doSelectByIndexFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), 5);
        doSendTextToElement(By.cssSelector("#BillingNewAddress_City"), "London");
        doSendTextToElement(By.cssSelector("#BillingNewAddress_Address1"), "999 Prime Road");
        doSendTextToElement(By.cssSelector("#BillingNewAddress_ZipPostalCode"), "LL5 5LU");
        doSendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07905698312");
        //2.28
        doClickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //2.29Click on Radio Button “2nd Day Air($0.00)”
        doClickOnElement(By.id("shippingoption_2"));

        //2.30
        doClickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));


        //2.31Select Radio Button “Credit Card”
        doClickOnElement(By.id("paymentmethod_1"));
        doClickOnElement(By.xpath("//button[@name='save' and @class='button-1 payment-method-next-step-button']"));

        //2.32
        doSelectByIndexFromDropDown(By.xpath("//select[@id='CreditCardType']"), 0);
        //2.33
        doSendTextToElement(By.xpath("//input[@id='CardholderName']"), "Jolly James");
        doSendTextToElement(By.xpath("//input[@id='CardNumber']"), "4700 1234 5678 7840");
        doSendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "01");
        doSendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2022");
        doSendTextToElement(By.xpath("//input[@id='CardCode']"), "999");
        //2.34
        doClickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.35
        doAssertToVerifyElements("Payment method is displayed incorrectly", "Credit Card", By.xpath("//span[contains(text(),'Credit Card')]"));
        Thread.sleep(500);
        //2.36
        doAssertToVerifyElements("Shipping Method is displayed incorrectly", "2nd Day Air", By.cssSelector("li[class='shipping-method'] span[class='value']"));
        //2.37
        doAssertToVerifyElements("Total Amount is displayed incorrectly", "$698.00", By.xpath("//span[contains(text(),'$698.00')]"));

        //2.38 Click on “CONFIRM”
        doClickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        //2.39Verify the Text “Thank You”
        doAssertToVerifyElements("Thank You Message is incorrectly displayed", "Thank you", By.xpath("//h1[contains(text(),'Thank you')]"));

        //2.40Verify the message “Your order has been successfully processed!”
        doAssertToVerifyElements("Order has not been processed successfully","Your order has been successfully processed!", By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));

        //2.41
        doClickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        //2.42Verify the text “Welcome to our store”
        doAssertToVerifyElements("Message has been incorrectly displayed", "Welcome to our store", By.xpath("//h2[contains(text(),'Welcome to our store')]"));

        //2.43Click on “Logout” link
        doClickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        //2.44
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals("The URL is incorrect", "https://demo.nopcommerce.com/", pageUrl);

    }


    @After
    public void tearDown() {
        closeBrowser();
    }

}
