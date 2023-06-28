package tests.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.switchTo;

public class BaseClassLoginTest {

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
