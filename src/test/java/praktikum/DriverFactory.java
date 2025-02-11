package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static praktikum.EnvConfig.IMPLICIT_WAIT;

public class DriverFactory extends ExternalResource {
public static WebDriver driver;


    @Override
    protected void before() {
        initDriver();
    }

    @Override
    protected void after() {
        driver.close();
        driver.quit();
    }

    public void initDriver() {
        if ("firefox".equalsIgnoreCase(System.getProperty("browser"))) {
            startUpFirefox();
        } else {
            startUpChrome();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void startUpChrome() {
        WebDriverManager.chromedriver().setup(); //инициализация WebDriverManager
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
    }

    public void startUpFirefox() {
        WebDriverManager.firefoxdriver().setup(); //инициализация WebDriverManager
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
    }
}
