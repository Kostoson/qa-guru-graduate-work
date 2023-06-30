package config.web;


import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:config/app.properties"
})
public interface Properties extends Config {
    @Key("login")
    String getLogin();
    @Key("password")
    String getPassword();
    @Key("invalidLogin")
    String getInvalidLogin();
    @Key("invalidPassword")
    String getInvalidPassword();
}
