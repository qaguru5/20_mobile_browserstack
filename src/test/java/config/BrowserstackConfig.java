package config;

import org.aeonbits.owner.Config;

import java.net.URL;

import static org.aeonbits.owner.Config.LoadType.MERGE;

@Config.LoadPolicy(MERGE)
@Config.Sources({"system:properties",
                "classpath:config/browserstack.properties",
                 "classpath:config/browserstack_${platform}.properties"})
public interface BrowserstackConfig extends Config {

    @DefaultValue("android")
    String mobilePlatform();
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
