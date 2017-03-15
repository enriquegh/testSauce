package com.saucelabs.enrique.Test;


import com.saucelabs.junit.ConcurrentParameterized;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Ignore;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

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

    @ConcurrentParameterized.Parameters
    public static LinkedList configOptions() {
        LinkedList<String[]> mobileconfig = new LinkedList<>();
        mobileconfig.add(new String[]{"Android Emulator","Android","5.1","","portrait","1.5.3","sauce-storage:enrique.apk"});
        mobileconfig.add(new String[]{"Samsung Galaxy S6 Device","Android","6.0","","portrait","1.5.3","sauce-storage:enrique.apk"});

        return mobileconfig;
    }

}
