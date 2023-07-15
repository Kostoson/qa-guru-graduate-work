package web.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import servicemethods.ServiceMethods;
import web.pages.LoginPage;
import web.pages.PersonalAccountPage;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("Unisender")
@Story("Авторизация")
@Tags({@Tag("Auth"), @Tag("WEB"), @Tag("remote")})
@DisplayName("Тестирование авторизации в личном кабинете")
public class LoginTests extends TestBase {

    LoginPage loginPage = new LoginPage();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();
    ServiceMethods serviceMethods = new ServiceMethods();

    @Test
    @Severity(BLOCKER)
    @DisplayName("Позитивный сценарий авторизации в личном кабинете")
    void positiveLoginTest() {
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount(email, password);});
        step("Пропуск приветственных банеров", () -> {
            serviceMethods.skippingSlidesOnWelcomePage();
        });
        step("Проверка входа в личный кабинет", () -> {
            personalAccountPage.checkingUserNameInThePersonalAccount("kostos1995@mail.ru");
        });
    }

    @Test
    @Severity(BLOCKER)
    @DisplayName("Негативный сценарий авторизации в личном кабинете с невалидным значением для поля 'Email'")
    void invalidEmailTest() {
        sleep(20000);
        step("Авторизация с невалидным логином", () -> {
            serviceMethods.authInThePersonalAccount(invalidEmail, password);
        });
        step("Проверка сообщения об ошибке", () -> {
            loginPage.checkingEmailFieldInfo();
        });
    }

    @Test
    @DisplayName("Негативный сценарий авторизации в личном кабинете с невалидным значением для поля 'Пароль'")
    @Severity(BLOCKER)
    void invalidPasswordTest() {
        sleep(20000);
        step("Авторизация с невалидным паролем", () -> {
            serviceMethods.authInThePersonalAccount(email, invalidPassword);
        });
        step("Проверка сообщения об ошибке", () -> {
            loginPage.checkingPasswordFieldInfo();
        });
    }
}
