package com.saucelabs.enrique.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	private WebDriver driver;

	By usernameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By loginForm = By.id("login");
	By successMessage = By.cssSelector(".flash.success");
	By failureMessage = By.cssSelector(".flash.error");	
	
	public Login(WebDriver driver) {
		
		this.driver = driver;
		driver.get("http://the-internet.herokuapp.com/login");
		Assert.assertTrue("The login form is not present", driver.findElement(loginForm).isDisplayed());
	}
	
	public void send(String username, String password) {
		driver.findElement(usernameLocator).sendKeys(username);
		driver.findElement(passwordLocator).sendKeys(password);
		driver.findElement(loginForm).submit();
		
	}
	
	public boolean isSuccessMessageDisplayed() {
		return driver.findElement(successMessage).isDisplayed();
	}
	
	public boolean isFailureMessageDisplayed() {
		return driver.findElement(failureMessage).isDisplayed();
	}
}
