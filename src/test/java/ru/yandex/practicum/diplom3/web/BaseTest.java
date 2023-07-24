package ru.yandex.practicum.diplom3.web;

import com.codeborne.selenide.Configuration;
import ru.yandex.practicum.diplom3.api.Api;
import ru.yandex.practicum.diplom3.api.User;
import ru.yandex.practicum.diplom3.api.UserCredentials;
import ru.yandex.practicum.diplom3.pages.HomePage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static ru.yandex.practicum.diplom3.api.Api.deleteUser;
import static ru.yandex.practicum.diplom3.api.Api.loginUser;
import static ru.yandex.practicum.diplom3.helpers.UserDataGenerator.getUser;
import static ru.yandex.practicum.diplom3.web.BrowserType.GOOGLE_CHROME;
import static ru.yandex.practicum.diplom3.web.BrowserType.YANDEX_BROWSER;

public class BaseTest {
    final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    protected BrowserType browserType;
    protected static User user;

    private static final String CHROME = "chrome";
    private static final String YA_BINARY = "/Applications/Yandex.app/Contents/MacOS/Yandex";
    private static final String FULL_HD_SIZE = "1920x1080";

    protected static String accessToken;

    protected HomePage openHomePage() {
        return open(BASE_URL, HomePage.class);
    }

    protected static void initBrowser(BrowserType type) throws IOException {
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

    protected void createUser() {
        user = getUser();
    }

    protected void browserClose() {
        clearBrowserCookies();
        closeWebDriver();
    }

    protected void loginUserAndGetAccessToken() {
        accessToken = new StringBuilder(loginUser(UserCredentials.from(user))
                .extract()
                .path("accessToken"))
                .substring(7);
    }

    protected void registerUserAndGetAccessToken() {
        accessToken = new StringBuilder(Api.registerUser(user)
                .extract()
                .path("accessToken"))
                .substring(7);
    }

    protected static void clearUserDate(String token) {
        deleteUser(token);
    }
}