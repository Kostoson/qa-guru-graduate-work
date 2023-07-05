package web.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class YouDesignEditorPage {
   private SelenideElement backToPersonalAccountButton = $("a.logo");

    public YouDesignEditorPage saveAndExitButtonClick() {
        backToPersonalAccountButton.click();
        return this;
    }
}
