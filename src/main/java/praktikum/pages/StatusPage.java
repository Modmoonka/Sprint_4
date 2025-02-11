package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import static praktikum.EnvConfig.EXPLICIT_WAIT;

/**
 * Тест для проверки работы блока 'Статус заказа'
 */

public class StatusPage {
    private final WebDriver driver;
    //Картинка отсутствия заказа
    private final By notFoundImage = By.cssSelector("img[alt='Not found']");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ожидание появления картинки отсутствия заказа
    public void checkNotFoundMessage() {

        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT))
                    .until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));

            assertTrue(driver.findElement(notFoundImage).isDisplayed());
        }
    }
