package com.saucelabs.enrique.Test;

import com.saucelabs.enrique.Pages.DynamicLoading;
import org.junit.Assert;
import org.junit.Test;

public class DynamicLoadingTest extends SeleniumBase {

    private DynamicLoading loading;

    public DynamicLoadingTest(String platform, String version, String browser) {
        super(platform, version, browser);
    }

    @Test
    public void checkHiddenElementLoadsTest() {
        loading = new DynamicLoading(driver);
        loading.loadExample("1");
        Assert.assertTrue("Message isn't displayed after waiting", loading.isFinishTextPresent());

    }

    @Test
    public void checkElementRendersTest() {
        loading = new DynamicLoading(driver);
        loading.loadExample("2");
        Assert.assertTrue("Message isn't rendered after waiting", loading.isFinishTextPresent());

    }
}
