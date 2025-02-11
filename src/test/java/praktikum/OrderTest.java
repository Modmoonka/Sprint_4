package praktikum;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderPage;
import praktikum.pages.RentalPage;

import static praktikum.DriverFactory.driver;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String orderDate;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    @ClassRule
    public static DriverFactory factory= new DriverFactory();

    public OrderTest(String name, String surname, String address, String metro, String phone, String orderDate, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.orderDate = orderDate;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @BeforeClass
    public static void closeCookies() {
        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        mainPage.openMainPage();
        mainPage.clickCookie();
    }

    @Parameterized.Parameters
    public static Object[][] orderDataTest() {
        return new Object[][] {
            {"Иван","Иванов","ул.Молодежная д.8", "Сокольники","+79234567890","03.02.2025","двое суток","black","Привезти до обеда"},
            {"Ирина","Меркулова","ул.Смирнова д.10", "Лубянка","+79999999999","01.02.2025","сутки","grey","Х"},
        };
    }

    // Ввод значений для теста
    @Test
    public void clickOrderPage() {
        WebDriver driver = DriverFactory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickHeaderOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        RentalPage rentalPage = new RentalPage(driver);
        orderPage.enterOrderData(name, surname, address, metro, phone);
        rentalPage.enterRental(orderDate, rentalPeriod, color, comment);
    }

    @Test
    public void createOrderInMainPage(){
        MainPage mainPage = new MainPage(DriverFactory.getDriver());
        mainPage.clickMainOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        RentalPage rentalPage = new RentalPage(driver);

        mainPage.clickMainOrderButton();
        orderPage.enterOrderData(name, surname, address, metro, phone);
        rentalPage.enterRental(orderDate, rentalPeriod, color, comment);
    }
}
