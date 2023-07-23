package servicemethods;

import web.pages.LoginPage;
import web.pages.TemplateCreationPage;
import web.pages.TemplatesTab;
import web.pages.WelcomePage;

public class ServiceMethods {
    LoginPage loginPage = new LoginPage();
    WelcomePage welcomePage = new WelcomePage();
    TemplatesTab templatesTab = new TemplatesTab();
    TemplateCreationPage templateCreationPage = new TemplateCreationPage();

    public ServiceMethods authInThePersonalAccount(String login, String password) {
        loginPage.fillingInTheEmailField(login)
                .fillingInThePasswordField(password)
                .logInButtonClick();
        return this;
    }

    public ServiceMethods skippingSlidesOnWelcomePage() {
        welcomePage.checkingHeaderTextForFirstSlide()
                .clickingOnSkip()
                .checkingHeaderTextForSecondSlide()
                .clickingOnSkip();
        return this;
    }

    public ServiceMethods templateCreation(String templateName, String templateSubject) {
        templateCreationPage.checkingHeader().
                fillingInTheTemplateNameField(templateName)
                .fillingInTheTemplateSubjectField(templateSubject)
                .yourDesignButtonClick();
        return this;
    }

    public ServiceMethods templateCreationWithoutTemplateName(String templateSubject) {
        templateCreationPage.checkingHeader()
                .fillingInTheTemplateSubjectField(templateSubject)
                .yourDesignButtonClick();
        return this;
    }

    public ServiceMethods templateCreationWithoutTemplateSubject(String templateName) {
        templateCreationPage.checkingHeader()
                .fillingInTheTemplateNameField(templateName)
                .yourDesignButtonClick();
        return this;
    }

    public ServiceMethods templateDelete() {
        templatesTab.clickingDeleteTemplatesButton();
        return this;
    }
}
