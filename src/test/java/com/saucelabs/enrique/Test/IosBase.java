package com.saucelabs.enrique.Test;

import com.saucelabs.junit.ConcurrentParameterized;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class IosBase extends AppiumBase{

    IOSDriver driver;

    IosBase(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }

    @Override
    public void setup() {
        DesiredCapabilities caps = setSauceCapabilities();

        try {
            driver = new IOSDriver(new URL(SAUCE_URL),caps);
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
        mobileconfig.add(new String[]{"iPhone Simulator","iOS","9.3","Safari","portrait","1.6.3",null});
        mobileconfig.add(new String[]{"iPhone 6s Device","iOS","9.3","Safari","portrait","1.5.3",null});

        return mobileconfig;
    }
}
