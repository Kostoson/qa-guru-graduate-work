package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private SelenideElement logInButton = $("div.header_wrap__right").$("li.login");

    public MainPage openPage() {
        open("https://www.unisender.com/");
        return this;
    }

    public MainPage logInButtonClick() {
        logInButton.click();
        return this;
    }

}
