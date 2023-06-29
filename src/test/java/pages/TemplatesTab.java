package pages;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TemplatesTab {
    private SelenideElement templatesCounter = $("span.filteredCounter"),
     messageAboutSearchResults = $("div.msg-to"),
     createTemplatesButton = $("a.btn-success"),
            deleteTemplatesButton = $("i.icon-remove"),
            deleteTemplates = $("td.remove"),
            removeTemplateConfirm = $("strong#removeTemplateConfirm"),
            removeTemplateAlert = $("div.alert-info");




    public TemplatesTab checkingTemplatesCounter(String counterMessage) {
        templatesCounter.shouldHave(text(counterMessage));
        return this;
    }
    public TemplatesTab checkingMessageAboutSearchResults(String message) {
        messageAboutSearchResults.shouldHave(text(message));
        return this;
    }
    public TemplatesTab clickingCreateTemplatesButton() {
        createTemplatesButton.click();
        return this;
    }
    public TemplatesTab clickingDeleteTemplatesButton() {
        deleteTemplates.hover();
        deleteTemplatesButton.click();
        return this;
    }

    public TemplatesTab removeTemplateConfirm() {
        removeTemplateConfirm.click();
        return this;
    }

    public TemplatesTab checkingAlertInfoAfterDeleteTemplate() {
        removeTemplateAlert.shouldHave(text("Шаблон сообщения успешно удален"));
        return this;
    }

}
