package web.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.remote.DesiredCapabilities;
import web.config.Properties;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import web.helpers.Attach;
import web.pages.MainPage;
import java.util.Map;
import static com.codeborne.selenide.Selenide.switchTo;

public class BaseTest {

     Properties config = ConfigFactory.create(Properties.class, System.getProperties());
     String  email = config.getLogin(),
             password = config.getPassword(),
             invalidEmail = "Negative",
             invalidPassword = "NegativeTest123";

    @BeforeAll
    static void beforeAll() {
        /*Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true, //чтобы отображалось в селенойде, что внутри просиходит
                "enableVideo", true //чтобы писалась запись видео
        ));

        Configuration.browserCapabilities = capabilities;
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void  openLoginPage() {
        MainPage mainPage = new MainPage();
        mainPage.openPage()
                .logInButtonClick();
        switchTo().window(1);
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        /*Selenide.closeWebDriver();*/
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
