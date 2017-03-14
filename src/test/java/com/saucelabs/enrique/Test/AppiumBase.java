package com.saucelabs.enrique.Test;

import com.saucelabs.junit.ConcurrentParameterized;
import io.appium.java_client.AppiumDriver;
import org.junit.runner.RunWith;

public abstract class AppiumBase extends Base {

    String appiumVersion;
    String deviceName;
    String app;
    String platformName;
    String platformVersion;
    String deviceOrientation;

    AppiumBase(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(browser);
        this.deviceName = deviceName;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.deviceOrientation = deviceOrientation;
        this.appiumVersion = appiumVersion;
        this.app = app;
    }
}
