package com.saucelabs.enrique.Test;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

public class Base implements SauceOnDemandSessionIdProvider {

	protected WebDriver driver;
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

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


	@Before
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities();
		// caps.setCapability("build", "testBuild2");
		caps.setBrowserName(System.getenv("SELENIUM_BROWSER"));
		caps.setVersion(System.getenv("SELENIUM_VERSION"));
		caps.setCapability(CapabilityType.PLATFORM, System.getenv("SELENIUM_PLATFORM"));
		caps.setCapability("name", testName);
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
