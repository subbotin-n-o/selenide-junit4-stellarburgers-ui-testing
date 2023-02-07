package ru.yandex.practicum.diplom3.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.io.IOException;

import static ru.yandex.practicum.diplom3.web.BrowserType.GOOGLE_CHROME;
import static ru.yandex.practicum.diplom3.web.BrowserType.YANDEX_BROWSER;

public class BaseTest {
    private static final String CHROME = "chrome";
    private static final String YA_BINARY = "/Applications/Yandex.app/Contents/MacOS/Yandex";
    private static final String FULL_HD_SIZE = "1920x1080";

    public static void initBrowser(BrowserType type) throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));

        if (type.equals(GOOGLE_CHROME)) {
            Configuration.browser = CHROME;
            setOptions();
        } else if (type.equals(YANDEX_BROWSER)) {
            Configuration.browserBinary = YA_BINARY;
            setOptions();
        }
    }

    private static void setOptions() {
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = FULL_HD_SIZE;
        Configuration.headless = true;
    }

    public void browserClose() {
        Selenide.closeWebDriver();
    }
}