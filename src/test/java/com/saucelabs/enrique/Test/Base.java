package com.saucelabs.enrique.Test;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

@RunWith(ConcurrentParameterized.class)
public abstract class Base implements SauceOnDemandSessionIdProvider {

    private static final String USERNAME = System.getenv("SAUCE_USERNAME");
    private static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    static final String SAUCE_URL = String.format("https://%s:%s@ondemand.saucelabs.com:443/wd/hub", USERNAME,ACCESS_KEY);

    String sessionId;
    final String buildName;
    final String browser;
    String testName;

    Base(String browser) {
        this.browser = browser;
        this.buildName = System.getenv("BUILD_TAG");
    }

    /*
    * Authentication and TestWatcher are needed to send back to Sauce Labs if
    * test was successful
    *
    * Another method is to make a TestWatcher instance
    */
    private final SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);

    @Rule
    public SauceOnDemandTestWatcher testWatcher = new SauceOnDemandTestWatcher(this, authentication);


    @Rule
    public TestRule nameWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getMethodName();
        }

    };

    @Before
    public abstract void setup();

    @After
    public abstract void destroy();

    public abstract void setSessionId();

    @Override
    public String getSessionId() {
        return sessionId;
    }
}
