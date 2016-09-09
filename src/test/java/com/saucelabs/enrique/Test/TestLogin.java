package com.saucelabs.enrique.Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.Assert;


import com.saucelabs.enrique.Pages.Login;
import com.saucelabs.enrique.Test.groups.Shallow;

public class TestLogin extends Base{

	private Login login;

	
	@Test
	@Category(Shallow.class)
	public void succeeded() {

		login = new Login(driver);
		login.send("tomsmith", "SuperSecretPassword!");
		Assert.assertTrue("Message is displayed", login.isSuccessMessageDisplayed());
		
	}
	
	@Test
	@Category(Shallow.class)
	public void failed() {
		login = new Login(driver);
		login.send("tomsmith", "SuperSecretPassword!sdqwd");
		Assert.assertTrue("Message is displayed after providing incorrect credentials", login.isFailureMessageDisplayed());
		
	}
	
	
}
