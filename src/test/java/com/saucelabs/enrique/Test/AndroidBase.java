package com.saucelabs.enrique.Test;


import com.saucelabs.junit.ConcurrentParameterized;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

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
        sessionId = driver.getSessionId().toString();

    }

    @ConcurrentParameterized.Parameters
    public static LinkedList configOptions() {
        LinkedList<String[]> browsers = new LinkedList<>();
        browsers.add(new String[]{"Android Emulator","Android","5.1","","portrait","1.5.3","sauce-storage:enrique.apk"});
        browsers.add(new String[]{"Samsung Galaxy S6 Device","Android","6.0","","portrait","1.5.3","sauce-storage:enrique.apk"});

        return browsers;
    }
}
