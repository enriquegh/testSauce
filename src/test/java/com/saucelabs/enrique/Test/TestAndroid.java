package com.saucelabs.enrique.Test;

import io.appium.java_client.android.AndroidElement;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;

public class TestAndroid extends AndroidBase {

    public TestAndroid(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }
    @BeforeClass
    public static void setupApp() {
        File appFilePath;
        boolean isContinousIntegration = Boolean.parseBoolean(System.getenv("TRAVIS"));

        if (isContinousIntegration) {
            String homeDir = System.getenv("TRAVIS_BUILD_DIR");
            appFilePath = new File(homeDir + "/enrique.apk");

        }
        else {
            appFilePath = new File("/Users/enriquegonzalez/saucelabs/apps/Android/enrique.apk");

        }

        uploadFileSauceStorage(appName,appFilePath);
    }

    @Test
    public void sendKeystoApp() {

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));


        username.sendKeys("csteam@saucelabs.com");
        password.sendKeys("pancakes!");

    }
}
