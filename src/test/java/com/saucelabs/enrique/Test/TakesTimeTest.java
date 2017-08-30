package com.saucelabs.enrique.Test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class TakesTimeTest extends SeleniumBase {

    private static final int NUM_CYCLES = 10;

    public TakesTimeTest(String platform, String version, String browser, String seleniumVersion) {
        super(platform, version, browser, seleniumVersion);
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
