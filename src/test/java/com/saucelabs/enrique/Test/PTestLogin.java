package com.saucelabs.enrique.Test;


import com.saucelabs.enrique.Pages.Login;
import org.junit.Assert;
import org.junit.Test;


public class PTestLogin extends PBase {


    public PTestLogin(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
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
