package web.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public class PersonalDataPage {

    private SelenideElement firstNameField = $("input#user_info_firstname"),
            lastNameField = $("input#user_info_lastname"),
            companyField = $("input#user_info_company"),
            saveUserSettingsButton = $("button.btn-success");

    public PersonalDataPage checkingFirstNameField(String firstName) {
        firstNameField.shouldHave(attribute("value", firstName));
        return this;
    }

    public PersonalDataPage checkingLastNameField(String lastName) {
        lastNameField.shouldHave(attribute("value", lastName));
        return this;
    }

    public PersonalDataPage checkingCompanyField(String company) {
        companyField.shouldHave(attribute("value", company));
        return this;
    }

    public PersonalDataPage changingFirstNameField(String firstName) {
        firstNameField.setValue(firstName);
        return this;
    }

    public PersonalDataPage changingLastNameField(String lastName) {
        lastNameField.setValue(lastName);
        return this;
    }

    public PersonalDataPage changingCompanyField(String company) {
        companyField.setValue(company);
        return this;
    }

    public PersonalDataPage savingUserSettings() {
        saveUserSettingsButton.click();
        return this;
    }
}
