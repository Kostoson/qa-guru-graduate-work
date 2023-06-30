package pages;

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

    public LoginPage checkingEmailFieldInfo() {
        emailFieldInfo.shouldHave(Condition.text("Такой email не зарегистрирован в Unisender."));
        return this;
    }

    public LoginPage checkingPasswordFieldInfo() {
        passwordFieldInfo.shouldHave(Condition.text("Неверный пароль. Повторите попытку или нажмите на кнопку \"Восстановить\""));
        return this;
    }
}
