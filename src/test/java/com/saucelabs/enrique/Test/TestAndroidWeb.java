package com.saucelabs.enrique.Test;

import com.saucelabs.enrique.Pages.Login;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enriquegonzalez on 3/30/17.
 */
public class TestAndroidWeb extends AndroidBase {


    public TestAndroidWeb(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }

    private Login login;

    @Test
    public void succeededTest() {

        login = new Login(driver);
        login.send("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue("Message is displayed", login.isSuccessMessageDisplayed());

    }

    @Test
    public void failedTest() {
        login = new Login(driver);
        login.send("tomsmith", "SuperSecretPassword!sdqwd");
        Assert.assertTrue("Message is displayed after providing incorrect credentials", login.isFailureMessageDisplayed());

    }
}
