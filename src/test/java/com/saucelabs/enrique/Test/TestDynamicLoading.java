package com.saucelabs.enrique.Test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.saucelabs.enrique.Pages.DynamicLoading;
import com.saucelabs.enrique.Test.groups.Deep;
import com.saucelabs.enrique.Test.groups.Shallow;

@Category(Deep.class)
public class TestDynamicLoading extends Base{
	
	private DynamicLoading loading;



	@Test
	public void checkHiddenElementLoads() {
		loading = new DynamicLoading(driver);
		loading.loadExample("1");
		Assert.assertTrue("Message isn't displayed after waiting", loading.isFinishTextPresent());
		
	}
	
	@Test
	public void checkElementRenders() {
		loading = new DynamicLoading(driver);
		loading.loadExample("2");
		Assert.assertTrue("Message isn't rendered after waiting", loading.isFinishTextPresent());
		
	}
	


}
