package config;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources("classpath:browserstack.properties")
public interface BrowserstackConfig extends Config {

    String browserstackUser();
    String browserstackKey();
    String androidAppUrl();
    String iosAppUrl();
    String androidDevice();
    String iosDevice();
    String androidVersion();
    String iosVersion();
    String androidDriverUrl();
    String iosDriverUrl();
}
