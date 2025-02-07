package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import  praktikum.EnvConfig;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

/**
 * Тест для проверки работы блока 'Статус заказа' и блока 'Вопросы о важном'
 */

public class MainPage {
    private final WebDriver driver;

    //Локаторы блока "Статус заказа"
    protected By goButton = By.cssSelector(".Header_Button__28dPO");
    protected By orderInputField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')]");
    protected By statusButton = By.className("Header_Link__1TAG7");

    //Блок 'Вопросы о важном'
    static String question = "accordion__heading-";
    static String answer = "accordion__panel-";
    private final By accordion = By.xpath(".//*[@class='accordion']");

    //Кнопка принятия куки
    private final By cookieButton = By.xpath(".//button[text()='да все привыкли']");

    //Кнопка заказа в хедере
    private final By headerOrderButton = By.xpath(".//button[@class='Button_Button__ra12g']");

    //Кнопка заказа на странице
    private final By mainOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(EnvConfig.BASE_URL);
    }

    //Клик на согласие использования куков
    public void clickCookie() {
        this.driver.findElement(this.cookieButton).click();
    }

    //Метод скрола до блока вопросы о важном
    public void scrollFaq() {
        WebElement element = driver.findElement(accordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод открытия первого элемент списка
    public void clickToAnswer(int itemId) {
        driver.findElement(By.id(question + itemId)).click();
    }

    //Метод получения ответа из accordion на главной странице
    public String getAnswer(int itemId) {
        return (driver.findElement(By.id(answer + itemId)).getText());
    }

    //Метод получения вопроса из accordion на главной странице
    public String getQuestion(int itemId) {
        return (driver.findElement(By.id(question + itemId)).getText());
    }

    //Метод клика кнопки 'Идти'
    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }

    //Метод ввода значения заказа
    public void typeOrderId(String orderId) {
        driver.findElement(orderInputField).sendKeys(orderId);
    }

    //Метод клика кнопки статуса
    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }

    //Метод открытия главной страницы-URL
    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    //Метод клика заказать в хедере
    public void clickHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    //Метод клика заказать на главной странице
    public void clickMainOrderButton() {
        driver.findElement(mainOrderButton).click();
    }

    //Метод сравнения ответа с ОР
    public void equalsAnswer(int itemId, String expectedAnswers){
        String actualAnswer = getAnswer(itemId);
        assertEquals("Ответы не совпадают", actualAnswer, expectedAnswers);
    }

    //Метод сравнения вопроса с ОР
    public void equalsQuestion(int itemId, String expectedQuestion){
        String actualQuestion = getQuestion(itemId);
        assertEquals("Вопросы не совпадают", actualQuestion, expectedQuestion);
    }

    //Метод ожидания появления блока с ответом
    public void waitAcardion(int itemId) {

        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answer + itemId)));

        assertTrue(driver.findElement(By.id(answer + itemId)).isDisplayed());
    }
}