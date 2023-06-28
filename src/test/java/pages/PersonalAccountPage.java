package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {
    private SelenideElement dropDownUserMenu = $("div.userMenu__email");

    public PersonalAccountPage checkingUserNameInThePersonalAccount(String userName) {
        dropDownUserMenu.shouldHave(text(userName));
        return this;
    }
}
