package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import utils.TestBase;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AccordionTest extends TestBase {
    static Stream<org.junit.jupiter.params.provider.Arguments> accordionData() {
        return Stream.of(arguments(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                arguments(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                arguments(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                arguments(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                arguments(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                arguments(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                arguments(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                arguments(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest
    @MethodSource("accordionData")
    void checkAccordionAnswers(int index, String expectedText) {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.scrollToQuestion(index);
        mainPage.openQuestion(index);
        Assertions.assertEquals(expectedText, mainPage.getAnswerText(index));
    }
}