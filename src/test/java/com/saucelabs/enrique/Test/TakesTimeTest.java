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

        driver.get("https://google.com");

    }

//    @Test
//    public void clickTest2() throws InterruptedException {
//
//        int i = 0;
//
//        driver.get("http://photobucket.com/");
//
//    }

}
