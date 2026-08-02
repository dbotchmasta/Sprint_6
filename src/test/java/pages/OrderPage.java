package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import data.ScooterColor;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    // Поле "Имя"
    private final By firstName =
            By.xpath("//input[@placeholder='* Имя']");

    // Поле "Фамилия"
    private final By lastName =
            By.xpath("//input[@placeholder='* Фамилия']");

    // Поле "Адрес"
    private final By address =
            By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле "Станция метро"
    private final By metro =
            By.xpath("//input[@placeholder='* Станция метро']");

    // Поле "Телефон"
    private final By phone =
            By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private final By nextButton =
            By.xpath("//button[text()='Далее']");

    // Дата доставки
    private final By deliveryDate =
            By.xpath("//input[@placeholder='* Когда привезти самокат']");

    // Срок аренды
    private final By rentPeriod =
            By.className("Dropdown-control");

    // Комментарий
    private final By comment =
            By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Цвета
    private final By blackColor =
            By.id("black");

    private final By greyColor =
            By.id("grey");

    // Кнопка "Заказать"
    private final By orderButton =
            By.xpath("//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");

    // Кнопка "Да"
    private final By yesButton =
            By.xpath("//button[text()='Да']");

    // Заголовок успешного заказа
    private final By successHeader =
            By.xpath("//*[contains(text(),'Заказ оформлен')]");

    private final By firstNameError =
            By.xpath("//input[@placeholder='* Имя']/following-sibling::div");

    private final By lastNameError =
            By.xpath("//input[@placeholder='* Фамилия']/following-sibling::div");

    private final By addressError =
            By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']/following-sibling::div");

    private final By metroError =
            By.xpath("//input[@placeholder='* Станция метро']/parent::div/following::div[text()='Выберите станцию']");

    private final By phoneError =
            By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']/following-sibling::div");

    public void setFirstName(String value) {
        type(firstName, value);
    }

    public void setLastName(String value) {
        type(lastName, value);
    }

    public void setAddress(String value) {
        type(address, value);
    }

    public void setMetro(String station) {
        type(metro, station);
        find(metro).sendKeys(Keys.ARROW_DOWN);
        find(metro).sendKeys(Keys.ENTER);
    }

    public void setPhone(String value) {
        type(phone, value);
    }

    public void clickNext() {
        click(nextButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDate));
    }

    public void clickNextEmptyFields(){
        click(nextButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameError));
    }

    public void setDeliveryDate(String date) {
        type(deliveryDate, date);
        find(deliveryDate).sendKeys(Keys.ENTER);
    }

    public void selectRentPeriod(String period) {
        click(rentPeriod);
        By option = By.xpath("//div[contains(@class,'Dropdown-option') and text()='" + period + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option));
        click(option);
    }

    public void selectBlackColor() {
        click(blackColor);
    }

    public void selectGreyColor() {
        click(greyColor);
    }

    public void setComment(String text) {
        type(comment, text);
    }

    public void clickOrder() {
        click(orderButton);
    }

    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(yesButton));
        click(yesButton);
    }

    public boolean isOrderCreated() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successHeader));
        return isDisplayed(successHeader);
    }

    public void fillCustomerData(String name, String surname, String addr, String metroStation, String phoneNumber) {
        setFirstName(name);
        setLastName(surname);
        setAddress(addr);
        setMetro(metroStation);
        setPhone(phoneNumber);
        clickNext();
    }

    public void fillRentData(String date, String period, ScooterColor color, String courierComment) {
        setDeliveryDate(date);
        selectRentPeriod(period);

        switch (color) {
            case BLACK:
                selectBlackColor();
                break;
            case GREY:
                selectGreyColor();
                break;
        }
        setComment(courierComment);
    }

    public boolean isErrorDisplayed(String field) {
        switch (field) {
            case "Имя":
                return isDisplayed(firstNameError);
            case "Фамилия":
                return isDisplayed(lastNameError);
            case "Адрес":
                return isDisplayed(addressError);
            case "Метро":
                return isDisplayed(metroError);
            case "Телефон":
                return isDisplayed(phoneError);
            default:
                throw new IllegalArgumentException("Неизвестное поле: " + field);
        }
    }
}