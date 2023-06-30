package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {
    private SelenideElement dropDownUserMenu = $("div.userMenu__email"),
     switchingToSending = $("a[data-test='link_navigationCampaigns']"),
    templateTab = $("a[data-test='link_lettersTemplate']"),
            templateTabHeader = $("h1.sideheader"),
            userMenuButton = $("svg.userMenu__buttonArrow"),
            userSettingsButton = $("a[data-test='link_settings']");

    public PersonalAccountPage checkingUserNameInThePersonalAccount(String userName) {
        dropDownUserMenu.shouldHave(text(userName));
        return this;
    }

    public PersonalAccountPage switchingToTemplates() {
        switchingToSending.click();
        templateTab.click();
        return this;
    }

    public PersonalAccountPage checkingTemplateTabHeader() {
        templateTabHeader.shouldHave(text("Мои шаблоны"));
        return this;
    }
    public PersonalAccountPage openingUserSettings() {
        userMenuButton.click();
        userSettingsButton.click();
        return this;
    }

}
