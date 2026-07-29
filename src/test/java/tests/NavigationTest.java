package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import utils.TestBase;

import java.time.Duration;

public class NavigationTest extends TestBase {
    @Test
    void clickScooterLogoShouldOpenMainPage() {
        MainPage page = new MainPage(driver);
        page.acceptCookies();
        page.clickTopOrderButton();
        page.clickScooterLogo();
        Assertions.assertEquals("https://qa-scooter.education-services.ru/", driver.getCurrentUrl());
    }

    @Test
    void clickYandexLogoShouldOpenYandex() {
        MainPage page = new MainPage(driver);
        page.acceptCookies();
        page.clickYandexLogo();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ждём открытия второй вкладки
        wait.until(driver -> driver.getWindowHandles().size() == 2);

        String originalWindow = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Ждём, пока URL перестанет быть about:blank
        wait.until(driver -> !driver.getCurrentUrl().equals("about:blank"));

        String url = driver.getCurrentUrl();
        System.out.println(url);

        Assertions.assertTrue(
                url.contains("yandex")
                        || url.contains("ya.ru")
                        || url.contains("dzen.ru")
        );
    }
}