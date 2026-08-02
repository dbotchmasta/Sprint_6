package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import utils.TestBase;

public class OrderStatusTest extends TestBase {

    @Test
    void wrongOrderNumberShouldShowNotFoundMessage() {
        MainPage page = new MainPage(driver);
        page.acceptCookies();
        page.clickOrderStatus();
        page.enterOrderNumber("999999999");
        page.clickGo();
        Assertions.assertTrue(page.isOrderNotFoundMessageDisplayed(), "Сообщение 'Такого заказа нет' не отображается");
    }
}