package web.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement emailField = $("input[data-test='input-login']"),
                            passwordField = $("input[data-test='input-password']"),

                            logInButton = $("button[data-test='btn-login']"),
                            emailFieldInfo = $("div[data-test='input-loginInfo']"),
                            passwordFieldInfo = $("div[data-test='input-passwordInfo']");

    public LoginPage fillingInTheEmailField(String login) {
        emailField.setValue(login);
        return this;
    }

    public LoginPage fillingInThePasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    public LoginPage logInButtonClick() {
        logInButton.click();
        return this;
    }

    public LoginPage checkingEmailFieldInfo(String message) {
        emailFieldInfo.shouldHave(Condition.text(message));
        return this;
    }

    public LoginPage checkingPasswordFieldInfo(String message) {
        passwordFieldInfo.shouldHave(Condition.text(message));
        return this;
    }
}
