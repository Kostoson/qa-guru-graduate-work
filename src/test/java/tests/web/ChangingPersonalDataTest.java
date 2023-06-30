package tests.web;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.PersonalAccountPage;
import pages.PersonalDataPage;
import com.codeborne.selenide.Selenide;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.CRITICAL;

@Feature("Unisender")
@Story("Настройка аккаунта")
@Tags({@Tag("Settings"), @Tag("Web"), @Tag("Unisender")})
public class ChangingPersonalDataTest extends BaseClass {
    PersonalDataPage personalDataPage = new PersonalDataPage();
    ServiceMethods serviceMethods = new ServiceMethods();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();

    @Test
    @DisplayName("Позитивный сценарий изменения имени пользователя")
    @Severity(CRITICAL)
    void changingFirstNameTest() {
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123")
                    .skippingSlidesOnWelcomePage();
        });
        step("Переход в настройки аккаунта", () -> {
            personalAccountPage.openingUserSettings();
        });
        step("Проверка начального значения имени пользователя", () -> {
            personalDataPage.checkingFirstNameField("Константин");
        });
        step("Изменение значения имени пользователя", () -> {
            personalDataPage.changingFirstNameField("Константин тест");
        });
        step("Сохраниение нового значения имени пользователя", () -> {
            personalDataPage.savingUserSettings();
        });
        step("Перезагрузка страницы", () -> {
            Selenide.refresh();
        });
        step("Проверка нового значения имени пользователя", () -> {
            personalDataPage.checkingFirstNameField("Константин тест");
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
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123")
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
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123")
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
