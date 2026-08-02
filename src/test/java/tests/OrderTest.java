package tests;

import data.OrderButton;
import data.ScooterColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.OrderPage;
import utils.TestBase;

public class OrderTest extends TestBase {

    @ParameterizedTest
    @MethodSource("data.OrderData#orderData")
    void createOrderTest(String name, String surname, String address, String metro, String phone, String date, String rentPeriod, ScooterColor color, String comment, OrderButton button) {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        mainPage.acceptCookies();

        switch (button) {
            case TOP:
                mainPage.clickTopOrderButton();
                break;
            case BOTTOM:
                mainPage.clickBottomOrderButton();
                break;
        }

        orderPage.fillCustomerData(name, surname, address, metro, phone);

        orderPage.fillRentData(date, rentPeriod, color, comment);

        orderPage.clickOrder();
        orderPage.confirmOrder();

        Assertions.assertTrue(orderPage.isOrderCreated());
    }
}