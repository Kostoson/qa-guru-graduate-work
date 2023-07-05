package web.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import web.pages.LoginPage;
import web.pages.PersonalAccountPage;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;

@Feature("Unisender")
@Story("Авторизация")
@Tags({@Tag("Auth"), @Tag("WEB"), @Tag("Regress")})
@DisplayName("Тестирование авторизации в личном кабинете")
public class LoginTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();
    ServiceMethods serviceMethods = new ServiceMethods();

    @Test
    @DisplayName("Позитивный сценарий авторизации в личном кабинете")
    @Severity(BLOCKER)
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

    @Disabled
    @Test
    @Severity(BLOCKER)
    @DisplayName("Негативный сценарий авторизации в личном кабинете с невалидным значением для поля 'Email'")
    void invalidEmailTest() {
        step("Авторизация с невалидным логином", () -> {
            serviceMethods.authInThePersonalAccount(invalidEmail, password);
        });
        step("Проверка сообщения об ошибке", () -> {
            loginPage.checkingEmailFieldInfo();
        });
    }

    @Disabled
    @Test
    @DisplayName("Негативный сценарий авторизации в личном кабинете с невалидным значением для поля 'Пароль'")
    @Severity(BLOCKER)
    void invalidPasswordTest() {
        step("Авторизация с невалидным паролем", () -> {
            serviceMethods.authInThePersonalAccount(email, invalidPassword);
        });
        step("Проверка сообщения об ошибке", () -> {
            loginPage.checkingPasswordFieldInfo();
        });
    }
}
