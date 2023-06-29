package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TemplateCreationPage {

  private SelenideElement header = $("h3.sideheader-simple"),
   templateNameField = $("input#create_email_params_title"),
   templateSubjectField = $("input#create_email_params_subject"),
   yourDesignButton = $("span.mtemplate"),
          templateNameInTheList = $("a.h4");



  public TemplateCreationPage checkingHeader() {
    header.shouldHave(text("Email-рассылка"));
    return this;
  }

  public TemplateCreationPage fillingInTheTemplateNameField(String templateName) {
    templateNameField.setValue(templateName);
    return this;
  }
  public TemplateCreationPage fillingInTheTemplateSubjectField(String templateSubject) {
    templateSubjectField.setValue(templateSubject);
    return this;
  }

  public TemplateCreationPage yourDesignButtonClick() {
    yourDesignButton.click();
    return this;
  }
  public TemplateCreationPage checkingTemplateNameInTheList(String templateName) {
    templateNameInTheList.shouldHave(text(templateName));
    return this;
  }



}
