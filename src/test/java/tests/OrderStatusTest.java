package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import utils.TestBase;

import java.time.Duration;

public class OrderStatusTest extends TestBase {

    @Test
    void wrongOrderNumberShouldShowNotFoundMessage() {
        MainPage page = new MainPage(driver);
        page.acceptCookies();
        page.clickOrderStatus();
        page.enterOrderNumber("999999999");
        page.clickGo();
        Assertions.assertTrue(driver.getPageSource().contains("Not found"));
    }
}