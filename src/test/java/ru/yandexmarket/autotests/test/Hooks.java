package ru.yandexmarket.autotests.test;

import com.codeborne.selenide.Configuration;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Hooks {
    @Before
    public void option(){
        Configuration.timeout = 10000;
        Configuration.browser = "chrome";

        Configuration.browserSize = "1920x1080";

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-extensions");
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//        Configuration.browserCapabilities = capabilities;
    }

    @After
    public void tearDown(){
        // WebDriverRunner.getWebDriver().close();
        closeWebDriver();
    }
}
