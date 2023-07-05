package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.web.Properties;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.MainPage;
import static com.codeborne.selenide.Selenide.switchTo;

public class BaseTest {

     Properties config = ConfigFactory.create(Properties.class, System.getProperties());
     String  email = config.getLogin(),
             password = config.getPassword(),
             invalidEmail = "Negative",
             invalidPassword = "NegativeTest123";

    @BeforeAll
    static void beforeAll() {
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @BeforeEach
    void  openLoginPage() {
        MainPage mainPage = new MainPage();
        mainPage.openPage()
                .logInButtonClick();
        switchTo().window(1);
    }

    @AfterEach
    void closeDriver() {
        Selenide.closeWebDriver();
    }

}
