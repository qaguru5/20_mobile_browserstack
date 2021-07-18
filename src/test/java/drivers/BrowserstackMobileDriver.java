package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.aeonbits.owner.ConfigFactory;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {
    static BrowserstackConfig config = ConfigFactory.create(BrowserstackConfig.class, System.getProperties());
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        if (System.getProperty("platform").equals("android")) {
            return getAndroidDriver();
        } else if (System.getProperty("platform").equals("ios")) {
            return getIosDriver();
        }
        return null;
    }

    private DesiredCapabilities commonCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserstack.user", config.browserstackUser());
        capabilities.setCapability("browserstack.key", config.browserstackKey());
        capabilities.setCapability("project", "First Java Project");
        capabilities.setCapability("name", "first_test");

        return capabilities;
    }

    public AndroidDriver getAndroidDriver() {
        DesiredCapabilities capabilities = commonCapabilities();
        capabilities.setCapability("app", config.androidAppUrl());
        capabilities.setCapability("device", config.androidDevice());
        capabilities.setCapability("os_version", config.androidVersion());
        capabilities.setCapability("build", "Java Android");

        return new AndroidDriver(getBrowserstackAndroidAppUrl(), capabilities);
    }

    public IOSDriver getIosDriver() {
        DesiredCapabilities capabilities = commonCapabilities();
        capabilities.setCapability("app", config.iosAppUrl());
        capabilities.setCapability("device", config.iosDevice());
        capabilities.setCapability("os_version", config.iosVersion());
        capabilities.setCapability("build", "Java iOS");

        return new IOSDriver(getBrowserstackIosAppUrl(), capabilities);
    }

    public static URL getBrowserstackAndroidAppUrl() {
        try {
            return new URL(config.androidDriverUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL getBrowserstackIosAppUrl() {
        try {
            return new URL(config.iosDriverUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
