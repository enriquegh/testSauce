package com.saucelabs.enrique.PTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by enriquegonzalez on 3/1/17.
 */
public class PTestTakesTime extends PBase {

    private static final int NUM_CYCLES = 10;

    public PTestTakesTime(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }


    @Test
    public void clickTest() throws InterruptedException {

        int i = 0;

        driver.get("https://enriquegh.com");

        while (i < NUM_CYCLES) {
            WebElement header = driver.findElement(By.id("header"));
            if (!header.isDisplayed()) {
                driver.findElement(By.xpath("//*[@id=\"titleBar\"]/a")).click();
            }



            Thread.sleep(2000);
            driver.findElement(By.id("two-link")).click();

            driver.get("https://enriquegh.com");
            Thread.sleep(2000);

            i++;

        }

    }
}
