package com.saucelabs.enrique.Test;

import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.Ignore;

import java.util.LinkedList;

@Ignore
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

    @ConcurrentParameterized.Parameters
    public static LinkedList configOptions() {
        LinkedList<String[]> mobileconfig = new LinkedList<>();
        mobileconfig.add(new String[]{"Android Emulator","Android","5.1","","portrait","1.5.3","sauce-storage:enrique.apk"});
        mobileconfig.add(new String[]{"Samsung Galaxy S6 Device","Android","6.0","","portrait","1.5.3","sauce-storage:enrique.apk"});

        return mobileconfig;
    }
}
