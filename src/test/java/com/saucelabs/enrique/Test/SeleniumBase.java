package com.saucelabs.enrique.Test;

import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.Ignore;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

@Ignore
public class SeleniumBase extends Base {

    private final String platform;
    private final String version;
    RemoteWebDriver driver;


    SeleniumBase(String platform, String version, String browser) {
        super(browser);
        this.platform = platform;
        this.version = version;

    }


    @Override
    public void setup() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.PLATFORM,platform);
        caps.setCapability(CapabilityType.VERSION,version);
        caps.setBrowserName(browser);
        caps.setCapability("name",testName);
        if (buildName != null) caps.setCapability("build",buildName);

        try {
            driver = new RemoteWebDriver(new URL(SAUCE_URL),caps);
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
        LinkedList<String[]> browsers = new LinkedList<>();
        browsers.add(new String[]{"Windows 7","beta","chrome"});
        browsers.add(new String[]{"Windows 7","55","chrome"});
        browsers.add(new String[]{"Windows 7","50","chrome"});

        return browsers;
    }
}
