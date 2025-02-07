package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import praktikum.pages.MainPage;

public class StatusTest {
    @ClassRule
    public static DriverFactory factory= new DriverFactory();

    @BeforeClass
    public static void closeCookies() {
        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        mainPage.openMainPage();
        mainPage.clickCookie();
    }

    @Test
    public void openMainPage() {
        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        mainPage.openMainPage();
        mainPage.clickOnStatus();

        String invalid ="123";
        mainPage.typeOrderId(invalid);

        var statusPage = mainPage.clickOnGo();
        statusPage.checkNotFoundMessage();
    }
}