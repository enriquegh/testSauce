package com.saucelabs.enrique.Test;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidBase extends AppiumBase {

    AndroidDriver driver;

    AndroidBase(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }

    @Override
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName",deviceName);
        caps.setCapability("platformName",platformName);
        caps.setCapability("platformVersion",platformVersion);
        caps.setBrowserName(browser);
        caps.setCapability("deviceOrientation",deviceOrientation);
        caps.setCapability("appiumVersion",appiumVersion);
        if (app != null) caps.setCapability("app",app);

        caps.setCapability("name",testName);
        if (buildName != null) caps.setCapability("build",buildName);

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
