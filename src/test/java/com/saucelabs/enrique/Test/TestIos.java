package com.saucelabs.enrique.Test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class TestIos extends IosBase{


    public TestIos(String deviceName, String platformName, String platformVersion, String browser, String deviceOrientation, String appiumVersion, String app) {
        super(deviceName, platformName, platformVersion, browser, deviceOrientation, appiumVersion, app);
    }

    @Test
    public void clickTest() {

        driver.get("https://enriquegh.com");
        WebElement header = driver.findElement(By.id("header"));
        if (!header.isDisplayed()) {
            driver.findElement(By.xpath("//*[@id=\"titleBar\"]/a")).click();
        }

        driver.findElement(By.id("two-link")).click();

        driver.get("https://enriquegh.com");

    }
}
