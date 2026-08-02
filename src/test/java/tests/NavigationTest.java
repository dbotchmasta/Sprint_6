package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import utils.TestBase;
import constants.Urls;

public class NavigationTest extends TestBase {
    @Test
    void clickScooterLogoShouldOpenMainPage() {
        MainPage page = new MainPage(driver);
        page.acceptCookies();
        page.clickTopOrderButton();
        page.clickScooterLogo();
        Assertions.assertEquals(Urls.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    void clickYandexLogoShouldOpenYandex() {
        MainPage page = new MainPage(driver);
        page.acceptCookies();

        // Ждём открытия второй вкладки
        page.clickYandexLogo();
        page.switchToYandexTab();

        Assertions.assertTrue(page.isYandexOpened());

    }
}