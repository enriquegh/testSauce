package com.saucelabs.enrique.Test;

import com.saucelabs.enrique.Pages.DynamicLoading;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by enriquegonzalez on 9/22/16.
 */
public class PTestDynamicLoading extends PBase {


    public PTestDynamicLoading(String os, String version, String browser, String deviceName, String deviceOrientation) {
        super(os, version, browser, deviceName, deviceOrientation);
    }

    private DynamicLoading loading;



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
