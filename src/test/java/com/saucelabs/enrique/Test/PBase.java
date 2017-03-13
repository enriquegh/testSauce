package com.saucelabs.enrique.Test;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;


@Ignore
@RunWith(ConcurrentParameterized.class)
public class PBase implements SauceOnDemandSessionIdProvider {

    protected WebDriver driver;
    public static final String USERNAME = System.getenv("SAUCE_USERNAME");
    public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");


    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
    public static final String LOCAL_URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@localhost:4445/wd/hub";


    protected String sessionId;
    private String testName;

    /*
	 * Authentication and TestWatcher are needed to send back to Sauce Labs if
	 * test was successful
	 *
	 * Another method is to make a TestWatcher instance
	 */
    SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);

    @Rule
    public SauceOnDemandTestWatcher testWatcher = new SauceOnDemandTestWatcher(this, authentication);


    @Rule
    public TestRule nameWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getMethodName();
        }

    };


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
        LinkedList<String[]> browsers = new LinkedList<>();

        browsers.add(new String[]{"Windows 7","beta","firefox",null,null});


        return browsers;

    }

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(browser);
        caps.setVersion(version);
        caps.setCapability(CapabilityType.PLATFORM, os);
        caps.setCapability("name",testName);
        if (buildName != null) caps.setCapability("build", buildName);
        if (deviceName != null) caps.setCapability("deviceName", deviceName);
        if (deviceOrientation != null) caps.setCapability("device-orientation", deviceOrientation);

        driver = new RemoteWebDriver(new URL(URL), caps);

        sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
    }

    @After
    public void destroy() {
        driver.quit();
    }

    @Override
    public String getSessionId() {
        return sessionId;
    }

}
