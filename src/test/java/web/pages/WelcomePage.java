package web.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class WelcomePage {
    private SelenideElement headerTextForFirstSlide = $("div.USH3"),
                            headerTextForSecondSlide = $("div.USH3"),
                            skipButton = $("button.USButton_gray");


    public WelcomePage checkingHeaderTextForFirstSlide() {
        headerTextForFirstSlide.shouldHave(text("Давайте оставаться на связи"));
        return this;
    }

    public WelcomePage checkingHeaderTextForSecondSlide() {
        headerTextForSecondSlide.shouldHave(text("С чего начнём?"));
        return this;
    }

    public WelcomePage clickingOnSkip() {
        skipButton.click();
        return this;
    }
}
