package config.web;


import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:app.properties"
})
public interface Properties extends Config {
    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();
}
