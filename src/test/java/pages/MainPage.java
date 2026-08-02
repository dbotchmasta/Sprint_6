package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Верхняя кнопка "Заказать"
    private final By topOrderButton =
            By.xpath("//div[contains(@class,'Header_Nav')]//button[text()='Заказать']");

    // Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath("//button[contains(@class,'Button_UltraBig')]");

    // Кнопка принятия cookies
    private final By cookieButton = By.id("rcc-confirm-button");

    // Логотип Самоката
    private final By scooterLogo = By.xpath(".//a[contains(@class, 'Scooter')]");

    // Логотип Яндекса
    private final By yandexLogo = By.xpath(".//a[contains(@class, 'Yandex')]");

    // Кнопка "Статус заказа"
    private final By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");

    // Поле номера заказа
    private final By orderNumberField = By.xpath("//input[@placeholder='Введите номер заказа']");

    // Кнопка Go
    private final By goButton = By.xpath("//button[contains(text(),'Go!')]");

    private final By orderNotFoundMessage = By.xpath("//img[@alt='Not found']");

    // Вопросы FAQ
    private final By[] questions = {
            By.id("accordion__heading-0"),
            By.id("accordion__heading-1"),
            By.id("accordion__heading-2"),
            By.id("accordion__heading-3"),
            By.id("accordion__heading-4"),
            By.id("accordion__heading-5"),
            By.id("accordion__heading-6"),
            By.id("accordion__heading-7")
    };

    // Ответы FAQ
    private final By[] answers = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    public void acceptCookies() {
        click(cookieButton);
    }

    public void clickTopOrderButton() {
        click(topOrderButton);
    }

    public void clickBottomOrderButton() {
        scrollToBottomOrderButton();
        click(bottomOrderButton);
    }

    public void scrollToBottomOrderButton() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();",
                        find(bottomOrderButton));
    }

    public void scrollToQuestion(int index) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();",
                        find(questions[index]));
    }

    public void openQuestion(int index) {
        click(questions[index]);
    }

    public String getAnswerText(int index) {
        return getText(answers[index]);
    }

    public boolean isAnswerDisplayed(int index) {
        return isDisplayed(answers[index]);
    }

    public void clickScooterLogo() {
        click(scooterLogo);
    }

    public void clickYandexLogo() {
        click(yandexLogo);
    }

    public void clickOrderStatus() {
        click(orderStatusButton);
    }

    public void enterOrderNumber(String number) {
        type(orderNumberField, number);
    }

    public void clickGo() {
        wait.until(ExpectedConditions.elementToBeClickable(goButton));
        click(goButton);
    }

    public void switchToYandexTab() {
        wait.until(driver -> driver.getWindowHandles().size() == 2);

        String currentWindow = driver.getWindowHandle();

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        wait.until(driver -> !driver.getCurrentUrl().equals("about:blank"));
    }

    public boolean isYandexOpened() {
        String url = driver.getCurrentUrl();
        return url.contains("ya.ru")
                || url.contains("yandex")
                || url.contains("dzen");
    }

    public boolean isOrderNotFoundMessageDisplayed() {
        return isDisplayed(orderNotFoundMessage);
    }

}