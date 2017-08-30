package com.saucelabs.enrique.Test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class ActionsClassTest extends SeleniumBase {


    public ActionsClassTest(String platform, String version, String browser, String seleniumVersion) {
        super(platform, version, browser, seleniumVersion);
    }

    @Test
    public void moveToElementTest() {

        String baseUrl = "http://newtours.demoaut.com/";

        driver.get(baseUrl);
        WebElement link_home=driver.findElement(By.linkText("Home"));
        WebElement td_home=driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[1]"));

        Actions builder=new Actions(driver);

        Action moveOverHome=builder.click().moveToElement(link_home).build();

        String bgColorBefore=td_home.getCssValue("background-color");
        
        moveOverHome.perform();

        String bgColorAfter = td_home.getCssValue("background-color");


        driver.quit();

        Assert.assertNotSame(bgColorBefore,bgColorAfter);

    }
}
