package com.properties;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTestsHW {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser","chrome");
        Configuration.browserSize = System.getProperty("browserSize","1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion","128.0");

        String selenoidHost = System.getProperty("selenoid_host", "selenoid.autotests.cloud");
        String selenoidLogin = System.getProperty("selenoid_login", "user1");
        String selenoidPassword = System.getProperty("selenoid_password", "1234");
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                selenoidLogin,
                selenoidPassword,
                selenoidHost);

        Configuration.timeout = 10000;
    }

    @Test
    @Tag("propertiesHW")
    void systemPropertiesHWTest() {

    }
}
