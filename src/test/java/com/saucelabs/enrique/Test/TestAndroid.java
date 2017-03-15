package com.saucelabs.enrique.Test;

import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestAndroid extends AndroidBase {

    public TestAndroid(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }
    @Test
    public void sendKeystoApp() {
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));


        username.sendKeys("csteam@saucelabs.com");
        password.sendKeys("pancakes!");

    }
}
