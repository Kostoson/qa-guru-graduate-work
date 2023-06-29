package tests.web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.PersonalAccountPage;
import pages.WelcomePage;


public class LoginTests extends BaseClassLoginTest{


    LoginPage loginPage = new LoginPage();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();
    ServiceMethods serviceMethods = new ServiceMethods();



    @Test
    void PositiveLoginTest() {
        serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "Test123")
                      .skippingSlidesOnWelcomePage();
       personalAccountPage.checkingUserNameInThePersonalAccount("kostos1995@mail.ru");
    }
    @Disabled
    @Test
    void InvalidEmailTest() {
        serviceMethods.authInThePersonalAccount("Negative", "Test123");
        loginPage.checkingEmailFieldInfo();
    }
    @Disabled
    @Test
    void InvalidPasswordTest() {
        serviceMethods.authInThePersonalAccount("kostos1995@mail.ru", "NegativeTest123");
        loginPage.checkingPasswordFieldInfo();
    }

}
