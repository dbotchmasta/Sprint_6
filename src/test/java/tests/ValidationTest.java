package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.OrderPage;
import utils.TestBase;

public class ValidationTest extends TestBase {

    @Test
    void emptyFieldsShouldShowErrors() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickTopOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextEmptyFields();
        Assertions.assertTrue(orderPage.isFirstNameErrorDisplayed());
        Assertions.assertTrue(orderPage.isLastNameErrorDisplayed());
        Assertions.assertTrue(orderPage.isAddressErrorDisplayed());
        Assertions.assertTrue(orderPage.isMetroErrorDisplayed());
        Assertions.assertTrue(orderPage.isPhoneErrorDisplayed());

    }

}