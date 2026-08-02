package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import pages.OrderPage;
import utils.TestBase;

public class ValidationTest extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = {
            "Имя",
            "Фамилия",
            "Адрес",
            "Метро",
            "Телефон"
    })
    void emptyFieldsShouldShowErrors(String field) {

        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.clickNextEmptyFields();

        Assertions.assertTrue(
                orderPage.isErrorDisplayed(field),
                "Не отображается ошибка для поля: " + field
        );
    }
}