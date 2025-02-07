package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

/**
 * Тест для проверки работы блока "Про аренду"
 */

public class RentalPage {

    private final WebDriver driver;
    //Поле для ввода даты
    private final By orderDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле срока аренды
    private final By rentalPeriod = By.xpath(".//div[text() = '* Срок аренды']");
    //Поле для выбора цвета самоката
    private final By colorField = By.xpath(".//div[text() = 'Цвет самоката']");
    //Поле даты
    private final By dropDatePiker = By.xpath(".//div[text() = 'Про аренду']");
    //Поле для комментария
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка заказать
    private final By orderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and contains(text(), 'Заказать')]");
    //Кнопка "Да" для подтверждения заказа
    private final By yesButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g') and contains(text(), 'Да')]");
    //Текст об успешном заказе
    private final By orderDone = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");
    //Кнопка "Посмотреть заказ"
    private  final By confirmationOrderButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Посмотреть статус']");

    public RentalPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод выбора даты
    public RentalPage enterDate (String rentalDate) {
          driver.findElement(orderDate).sendKeys(rentalDate);
          driver.findElement(dropDatePiker).click();
          return this;
    }

    //Метод срока аренды
    public RentalPage selectRentalDate (String rentPeriod) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath(".//div[(@class='Dropdown-option' and text()='" + rentPeriod + "')]")).click();
        return this;
    }

    //Метод выбора цвета
    public RentalPage selectRentalColor (String color) {
        driver.findElement(colorField).click();
        driver.findElement(By.xpath(".//label[@for='" + color + "']")).click();
        return this;
    }

    //Метод для комментария
    public RentalPage enterComment (String comment) {
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }

    //Клик по кнопке заказать
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }

    //Метод для клика по кнопке "Да"
    public void clickYesButton(){
        driver.findElement(yesButton).click();
    }

    // Ожидание заголовка оформления заказа
    public void waitOrder () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderDone));
    }

    //Метод клика по кнопке соглашения заказа
    public void clickConfirmtionOrderButton(){
        driver.findElement(confirmationOrderButton).click();
    }

    //Метод ввода данных
    public void enterRental(String rentalDate, String rentPeriod, String color, String comment){
        enterDate(rentalDate)
                .selectRentalDate(rentPeriod)
                .selectRentalColor(color)
                .enterComment(comment)
                .clickOrderButton();
        clickYesButton();
        waitOrder();
        clickConfirmtionOrderButton();
    }
}
