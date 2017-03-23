package com.saucelabs.enrique.Test;

import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

/**
 * Created by enriquegonzalez on 3/23/17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({DynamicLoadingTest.class,LoginTest.class,TakesTimeTest.class})
public class SeleniumTest {



}
