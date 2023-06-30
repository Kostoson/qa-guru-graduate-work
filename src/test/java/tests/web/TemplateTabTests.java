package tests.web;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.BLOCKER;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Feature("Unisender")
@Story("Вкладка 'Мои шаблоны'")
@Tags({@Tag("Template"), @Tag("Web"), @Tag("Unisender")})
public class TemplateTabTests extends BaseClass {
    ServiceMethods serviceMethods = new ServiceMethods();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();
    TemplatesTab templatesTab = new TemplatesTab();
    TemplateCreationPage templateCreationPage = new TemplateCreationPage();
    YouDesignEditorPage youDesignEditorPage = new YouDesignEditorPage();

   static final String MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES = "По заданным параметрам шаблонов не найдено";
    static final String COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES = "Записей 0 из 0";
    static final String COUNTER_MESSAGE_IN_THE_PRESENCE_OF_TEMPLATES = "Записей 1 из 1";

    @Test
    @DisplayName("Проверка переходы во вкладку 'Мои шаблоны'")
    @Severity(BLOCKER)
    void CheckingTheTransitionToTemplatesTab() {
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123")
                    .skippingSlidesOnWelcomePage();
        });
        step("Переход на вкладку 'Мои шаблоны'", () -> {
            personalAccountPage.switchingToTemplates();
        });
        step("Проверка перехода во вкладку 'Мои шаблоны'", () -> {
            personalAccountPage.checkingTemplateTabHeader();
        });

    }
    @Test
    @DisplayName("Позитивный сценарий создания шаблона")
    @Severity(BLOCKER)
    void PositiveTemplateCreationTest() {
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123").
                    skippingSlidesOnWelcomePage();
        });
        step("Переход на вкладку 'Мои шаблоны'", () -> {
            personalAccountPage.switchingToTemplates();
        });
        step("Проверка изначального значения счетчика шаблонов", () -> {
            templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES);
        });
        step("Проверка сообщения об отсутсвии шаблонов", () -> {
            templatesTab.checkingMessageAboutSearchResults(MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES);
        });
        step("Нажатие на кнопку 'создать шаблон письма'", () -> {
            templatesTab.clickingCreateTemplatesButton();
        });
        step("Создание шаблона", () -> {
            serviceMethods.templateCreation("Test", "Текст для теста");
        });
        step("Выход из редактора с сохранением созданного шаблона", () -> {
            youDesignEditorPage.saveAndExitButtonClick();
        });
        step("Переход на вкладку 'Мои шаблоны'", () -> {
            personalAccountPage.switchingToTemplates();
        });
        step("Проверка наличия созданного шаблона", () -> {
            templateCreationPage.checkingTemplateNameInTheList("Test");
        });
        step("Проверка значения счетчика шаблонов, после создания шаблона", () -> {
            templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_PRESENCE_OF_TEMPLATES);
        });
        step("Удаление шаблона", () -> {
            serviceMethods.templateDelete();
            templatesTab.removeTemplateConfirm().
                    checkingAlertInfoAfterDeleteTemplate();
            Selenide.refresh();});
        step("Проверка, что шаблон удален", () -> {
            templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES);
        });

    }
    @Test
    @DisplayName("Негативный сценарий создания шаблона без указания имени шаблона")
    @Severity(NORMAL)
    void TemplateCreationWithoutTemplateNameTest() {
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123").
                    skippingSlidesOnWelcomePage();
        });
        step("Переход на вкладку 'Мои шаблоны'", () -> {
            personalAccountPage.switchingToTemplates();
        });
        step("Нажатие на кнопку 'создать шаблон письма'", () -> {
            templatesTab.clickingCreateTemplatesButton();
        });
        step("Создание шаблона без указания имени шаблона", () -> {
            serviceMethods.templateCreationWithoutTemplateName("Текст для теста");
        });
        step("Проверка сообщения об ошибке", () -> {
            templateCreationPage.checkingErrorAlertForNameField();
        });
        step("Проверка отсутсвия шаблона", () -> {
            personalAccountPage.switchingToTemplates();
            templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES)
                    .checkingMessageAboutSearchResults(MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES);
        });
    }
    @Test
    @DisplayName("Негативный сценарий создания шаблона без указания темы письма шаблона")
    @Severity(NORMAL)
    void TemplateCreationWithoutTemplateSubjectTest() {
        step("Авторизация в личном кабинете", () -> {
            serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123").
                    skippingSlidesOnWelcomePage();
        });
        step("Переход на вкладку 'Мои шаблоны'", () -> {
            personalAccountPage.switchingToTemplates();
        });
        step("Нажатие на кнопку 'создать шаблон письма'", () -> {
            templatesTab.clickingCreateTemplatesButton();
        });
        step("Создание шаблона без указания темы письма", () -> {
            serviceMethods.templateCreationWithoutTemplateSubject("Test");
        });
        step("Проверка сообщения об ошибке", () -> {
            templateCreationPage.checkingErrorAlertForNameField();
        });
        step("Проверка отсутсвия шаблона", () -> {
            personalAccountPage.switchingToTemplates();
            templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES)
                    .checkingMessageAboutSearchResults(MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES);
        });
    }
}
