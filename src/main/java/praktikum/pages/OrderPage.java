package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Тест для проверки работы блока "Заказать"
 */

public class OrderPage {
    private final WebDriver driver;

    //Поле для ввода имени
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле для ввода фамилии
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле для ввода адреса куда везти
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Пикер для выбора станции
    private final By metroField = By.xpath(".//div[@class='select-search']");
    //Поле для ввода номера телефона
    private final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private final By nextPageButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Конструктор класса
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ввода имени
    public OrderPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }

    //Метод ввода фамилии
    public OrderPage enterSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }

    //Метод ввода адреса
    public OrderPage enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
        return this;
    }

    //Метод ввода станции метро
    public OrderPage enterMetro(String metro) {
        driver.findElement(metroField).click();
        driver.findElement(By.xpath(".//ul[@class='select-search__options']/li//div[text()='" + metro + "']/parent::button")).click();
        return this;
    }

   //Метод ввода телефона
    public OrderPage enterPhoneNumber(String phone) {
        driver.findElement(phoneNumberField).sendKeys(phone);
        return this;
    }

    //Метод клика по кнопке далее
    public void clickNextButton() {
        driver.findElement(nextPageButton).click();
    }

    //Метод ввода данных
    public void enterOrderData(String name, String surname, String address, String metro, String phone){
        enterName(name)
                .enterSurname(surname)
                .enterAddress(address)
                .enterMetro(metro)
                .enterPhoneNumber(phone)
                .clickNextButton();
    }
}
