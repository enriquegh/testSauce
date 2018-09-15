package com.saucelabs.enrique.Test;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

@Ignore
@RunWith(ConcurrentParameterized.class)
public abstract class Base implements SauceOnDemandSessionIdProvider {

    private static final String USERNAME = System.getenv("SAUCE_USERNAME");
    private static final String ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    static final String SAUCE_URL = String.format("https://%s:%s@ondemand.saucelabs.com:443/wd/hub", USERNAME,ACCESS_KEY);
    static final String SC_URL = String.format("http://%s:%s@127.0.0.1:4445/wd/hub",USERNAME,ACCESS_KEY);

    String sessionId;
    static String buildName;
    final String browser;
    String testName;
    boolean useSCRelay = false;

    Base(String browser) {
        this.browser = browser;
//        this.buildName = System.getenv("BUILD_TAG");
        this.useSCRelay = Boolean.parseBoolean(System.getenv("USE_SC"));
    }

    /*
    * Authentication and TestWatcher are needed to send back to Sauce Labs if
    * test was successful
    *
    * Another method is to make a TestWatcher instance
    */
    private final SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(USERNAME, ACCESS_KEY);

    @Rule
    public SauceOnDemandTestWatcher testWatcher = new SauceOnDemandTestWatcher(this, authentication);


    @Rule
    public TestRule nameWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getMethodName();
        }

    };

    @Before
    public abstract void setup();

    @After
    public abstract void destroy();

    public abstract void setSessionId();

    @Override
    public String getSessionId() {
        return sessionId;
    }

    /***
     * Function that can upload an app or pre-run executable to Sauce Storage
     * @param sauceFileName Name that file will take on Sauce Storage and be accessed as "sauce-storage:sauceFilename"
     * @param filePath Complete filepath where file is located
     */
    public static void uploadFileSauceStorage(String sauceFileName, File filePath) {

        //:TODO Check if application is there and make overwrite optional

        final String BASE_URL = "https://saucelabs.com/rest/v1/storage/%s/%s?overwrite=true";

        String url = String.format(BASE_URL,USERNAME,sauceFileName);

        CredentialsProvider credProv = new BasicCredentialsProvider();

        credProv.setCredentials(new AuthScope("saucelabs.com",443),
                                new UsernamePasswordCredentials(USERNAME,ACCESS_KEY));

        FileEntity fileEntity = new FileEntity(filePath);

        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeader("Content-Type","application/octet-stream");
        postRequest.setEntity(fileEntity);


        try (CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credProv).build();
             CloseableHttpResponse response = httpClient.execute(postRequest)) {

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @BeforeClass
    public static void setupClass() {
        buildName = System.getenv("BUILD_TAG");
        if (buildName == null) {
            buildName = System.getenv("SAUCE_BUILD_NAME");
        }
    }

}
