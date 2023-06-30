package tests.web;


import com.codeborne.selenide.Selenide;
import config.web.Properties;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.MainPage;
import static com.codeborne.selenide.Selenide.switchTo;

public class BaseClass {

     Properties config = ConfigFactory.create(Properties.class, System.getProperties());
     String  email = config.getLogin(),
            password = config.getPassword();

    /*@BeforeAll
    static void beforeAll() {
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }*/

    @AfterEach
    void closeDriver() {
        Selenide.closeWebDriver();
    }
    @BeforeEach
    void  openLoginPage() {
        MainPage mainPage = new MainPage();
        mainPage.openPage()
                .logInButtonClick();
        switchTo().window(1);
    }
}
