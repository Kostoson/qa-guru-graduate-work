package tests.web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pages.WelcomePage;


public class LoginTests extends BaseClassLoginTest{


    LoginPage loginPage = new LoginPage();
    WelcomePage welcomePage = new WelcomePage();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();

    @Test
    void PositiveLoginTest() {
        loginPage.fillingInTheEmailField("kostos1995@mail.ru")
                .fillingInThePasswordField("Test123")
                .logInButtonClick();
        welcomePage.checkingHeaderTextForFirstSlide()
                   .clickingOnSkip()
                   .checkingHeaderTextForSecondSlide()
                   .clickingOnSkip();
       personalAccountPage.checkingUserNameInThePersonalAccount("kostos1995@mail.ru");
    }
    @Disabled
    @Test
    void InvalidEmailTest() {
        loginPage.fillingInTheEmailField("Negative")
                .fillingInThePasswordField("Test123")
                .logInButtonClick()
                .checkingEmailFieldInfo();
    }
    @Disabled
    @Test
    void InvalidPasswordTest() {
        loginPage.fillingInTheEmailField("kostos1995@mail.ru")
                .fillingInThePasswordField("NegativeTest123")
                .logInButtonClick()
                .checkingPasswordFieldInfo();
    }

}
