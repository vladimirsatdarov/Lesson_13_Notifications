package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.RegistrationPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class RemoteTestBase {

    @BeforeAll
    static void beforeAll() {
//        Configuration.baseUrl = "https://demoqa.com";
//        Configuration.browserSize = "1920x1080";
//        Configuration.browser = "chrome";
//        Configuration.holdBrowserOpen = true;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browser = System.getProperty("browser","chrome");
        Configuration.browserSize = System.getProperty("browserSize","1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion","128.0");

        Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                System.getProperty("selenoid_login", "user1"),
                System.getProperty("selenoid_password", "1234"),
                System.getProperty("selenoid_host", "selenoid.autotests.cloud"));


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }
}
