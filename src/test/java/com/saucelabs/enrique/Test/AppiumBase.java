package com.saucelabs.enrique.Test;

import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.Ignore;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.LinkedList;

@Ignore
public abstract class AppiumBase extends Base {

    private String appiumVersion;
    private String deviceName;
    private String app;
    private String platformName;
    private String platformVersion;
    private String deviceOrientation;

    AppiumBase(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(browser);
        this.deviceName = deviceName;
        this.platformName = platformName;
        this.platformVersion = platformVersion;
        this.deviceOrientation = deviceOrientation;
        this.appiumVersion = appiumVersion;
        this.app = app;
    }

    @ConcurrentParameterized.Parameters
    public static LinkedList configOptions() {
        LinkedList<String[]> mobileconfig = new LinkedList<>();
        mobileconfig.add(new String[]{"Android Emulator","Android","5.1","","portrait","1.5.3","sauce-storage:enrique.apk"});
        mobileconfig.add(new String[]{"Samsung Galaxy S6 Device","Android","6.0","","portrait","1.5.3","sauce-storage:enrique.apk"});

        return mobileconfig;
    }

    DesiredCapabilities setSauceCapabilities() {
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

        return caps;

    }
}
