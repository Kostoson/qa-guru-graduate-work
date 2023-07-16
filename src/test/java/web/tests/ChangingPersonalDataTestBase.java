package web.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.ValueSource;
import servicemethods.ServiceMethods;
import web.pages.PersonalAccountPage;
import web.pages.PersonalDataPage;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.sleep;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import org.junit.jupiter.params.ParameterizedTest;

@Feature("Unisender")
@Story("Настройка аккаунта")
@Tags({@Tag("Settings"), @Tag("Web"), @Tag("remote")})
@DisplayName("Тестирование изменения персональных данных пользователя")
public class ChangingPersonalDataTestBase extends TestBase {
    PersonalDataPage personalDataPage = new PersonalDataPage();
    ServiceMethods serviceMethods = new ServiceMethods();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();

    @ValueSource(strings = {"Константин тест", "Konstantin test"})
    @ParameterizedTest(name = "Позитивный сценарий изменения имени пользователя на {0}")
    @Severity(CRITICAL)
    void changingFirstNameTest(String name) {
        sleep(10000);
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount(email, password)
                    .skippingSlidesOnWelcomePage();
        });
        step("Переход в настройки аккаунта", () -> {
            personalAccountPage.openingUserSettings();
        });
        step("Проверка начального значения имени пользователя", () -> {
            personalDataPage.checkingFirstNameField("Константин");
        });
        step("Изменение значения имени пользователя", () -> {
            personalDataPage.changingFirstNameField(name);
        });
        step("Сохраниение нового значения имени пользователя", () -> {
            personalDataPage.savingUserSettings();
        });
        step("Перезагрузка страницы", () -> {
            Selenide.refresh();
        });
        step("Проверка нового значения имени пользователя", () -> {
            personalDataPage.checkingFirstNameField(name);
        });
        step("Возврат к изначальному состоянию теста", () -> {
            personalDataPage.changingFirstNameField("Константин")
                    .savingUserSettings();
        });
    }

    @Test
    @DisplayName("Позитивный сценарий изменения фамилии пользователя")
    @Severity(CRITICAL)
    void changingLastNameTest() {
        sleep(10000);
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount(email, password)
                    .skippingSlidesOnWelcomePage();
        });
        step("Переход в настройки аккаунта", () -> {
            personalAccountPage.openingUserSettings();
        });
        step("Проверка начального значения фамилии пользователя", () -> {
            personalDataPage.checkingLastNameField("QA");
        });
        step("Изменение значения фамилии пользователя", () -> {
            personalDataPage.changingLastNameField("QA тест");
        });
        step("Сохраниение нового значения фамилии пользователя", () -> {
            personalDataPage.savingUserSettings();
        });
        step("Перезагрузка страницы", () -> {
            Selenide.refresh();
        });
        step("Проверка нового значения фамилии пользователя", () -> {
            personalDataPage.checkingLastNameField("QA тест");
        });
        step("Возврат к изначальному состоянию теста", () -> {
            personalDataPage.changingLastNameField("QA")
                    .savingUserSettings();
        });
    }

    @Test
    @DisplayName("Позитивный сценарий изменения компании пользователя")
    @Severity(CRITICAL)
    void changingCompanyNameTest() {
        sleep(10000);
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount(email, password)
                    .skippingSlidesOnWelcomePage();
        });
        step("Переход в настройки аккаунта", () -> {
            personalAccountPage.openingUserSettings();
        });
        step("Проверка начального значения названия компании пользователя", () -> {
            personalDataPage.checkingCompanyField("Alfa-bank");
        });
        step("Изменение значения названия компании пользователя", () -> {
            personalDataPage.changingCompanyField("Alfa-bank test");
        });
        step("Сохраниение нового значения названия компании пользователя", () -> {
            personalDataPage.savingUserSettings();
        });
        step("Перезагрузка страницы", () -> {
            Selenide.refresh();
        });
        step("Проверка нового значения названия компании пользователя", () -> {
            personalDataPage.checkingCompanyField("Alfa-bank test");
        });
        step("Возврат к изначальному состоянию теста", () -> {
            personalDataPage.changingCompanyField("Alfa-bank")
                    .savingUserSettings();
        });
    }
}
