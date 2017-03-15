package com.saucelabs.enrique.Test;


import io.appium.java_client.android.AndroidDriver;
import org.junit.Ignore;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

@Ignore
public class AndroidBase extends AppiumBase {

    AndroidDriver driver;

    AndroidBase(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }

    @Override
    public void setup() {
        DesiredCapabilities caps = setSauceCapabilities();

        try {
            driver = new AndroidDriver(new URL(SAUCE_URL),caps);
            setSessionId();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        driver.quit();
    }

    @Override
    public void setSessionId() {
        sessionId = ((RemoteWebDriver)driver).getSessionId().toString();

    }

}
