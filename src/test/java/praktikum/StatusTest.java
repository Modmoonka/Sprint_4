package praktikum;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

public class StatusTest {
    public DriverFactory factory = new DriverFactory();


    @Test
    public void openMainPage() {
        WebDriver driver = factory.getDriver();

        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnStatus();

        String invalid ="123";
        mainPage.typeOrderId(invalid);

        var statusPage = mainPage.clickOnGo();
        statusPage.checkNotFoundMessage();
    }


}