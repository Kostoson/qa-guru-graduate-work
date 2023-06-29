package tests.web;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import pages.*;
public class TemplateTabTests extends BaseClassLoginTest{
    ServiceMethods serviceMethods = new ServiceMethods();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();
    TemplatesTab templatesTab = new TemplatesTab();
    TemplateCreationPage templateCreationPage = new TemplateCreationPage();
    YouDesignEditorPage youDesignEditorPage = new YouDesignEditorPage();

   static final String MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES = "По заданным параметрам шаблонов не найдено";
    static final String COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES = "Записей 0 из 0";
    static final String COUNTER_MESSAGE_IN_THE_PRESENCE_OF_TEMPLATES = "Записей 1 из 1";
    @Test
    void CheckingTheTransitionToTemplatesTab() {
        serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123")
                .skippingSlidesOnWelcomePage();
        personalAccountPage.switchingToTemplates()
                           .checkingTemplateTabHeader();

    }
    @Test
    void PositiveTemplateCreationTest() {
        serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123").
                skippingSlidesOnWelcomePage();
        personalAccountPage.switchingToTemplates();
        templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES)
                .checkingMessageAboutSearchResults(MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES)
                .clickingCreateTemplatesButton();
        serviceMethods.templateCreation("Test", "Текст для теста");
        youDesignEditorPage.saveAndExitButtonClick();
        personalAccountPage.switchingToTemplates();
        templateCreationPage.checkingTemplateNameInTheList("Test");
        templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_PRESENCE_OF_TEMPLATES);
        serviceMethods.templateDelete();
        templatesTab.removeTemplateConfirm().
                    checkingAlertInfoAfterDeleteTemplate();
        Selenide.refresh();
        templatesTab.checkingTemplatesCounter(COUNTER_MESSAGE_IN_THE_ABSENCE_OF_TEMPLATES);
    }

}
