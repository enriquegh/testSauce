package com.saucelabs.enrique.Test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class PTestTakesTime extends SeleniumBase {

    private static final int NUM_CYCLES = 10;

    public PTestTakesTime(String platform, String version, String browser) {
        super(platform, version, browser);
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
