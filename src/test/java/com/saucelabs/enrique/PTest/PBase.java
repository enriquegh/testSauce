package com.saucelabs.enrique.PTest;

import com.saucelabs.enrique.Test.Base;
import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by enriquegonzalez on 9/22/16.
 */
@Ignore
@RunWith(ConcurrentParameterized.class)
public class PBase extends Base {

    private final String os;
    private final String version;
    private final String browser;
    private final String deviceName;
    private final String deviceOrientation;
    private final String buildName;

    public PBase(String os, String version, String browser, String deviceName, String deviceOrientation) {


        this.os = os;
        this.version = version;
        this.browser = browser;
        this.deviceName = deviceName;
        this.deviceOrientation = deviceOrientation;
        this.buildName = System.getenv("BUILD_TAG");
    }


    @ConcurrentParameterized.Parameters
    public static LinkedList browserNames() {
        LinkedList browsers = new LinkedList();

        browsers.add(new String[]{"Windows 7","beta","firefox",null,null});


        return browsers;

    }

    @Override
    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        caps.setVersion(version);
        caps.setCapability(CapabilityType.PLATFORM, os);
        if (buildName != null) caps.setCapability("build", buildName);
        if (deviceName != null) caps.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) caps.setCapability("device-orientation", deviceOrientation);

        driver = new RemoteWebDriver(new URL(URL), caps);

        sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
    }

    @Override
    @After
    public void destroy() {
        driver.quit();
    }



}
