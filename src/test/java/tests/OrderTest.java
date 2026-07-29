package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.OrderPage;
import utils.TestBase;

public class OrderTest extends TestBase {

    @ParameterizedTest
    @MethodSource("data.OrderData#orderData")
    void createOrderTest(String name, String surname, String address, String metro, String phone, String date, String rentPeriod, boolean blackColor, String comment, boolean topButton) {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.acceptCookies();
        if (topButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        orderPage.createOrder(name, surname, address, metro, phone, date, rentPeriod, blackColor, comment);

        Assertions.assertTrue(orderPage.isOrderCreated());
    }
}