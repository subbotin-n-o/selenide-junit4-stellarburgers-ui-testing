package ru.yandex.practicum.diplom3.web;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ru.yandex.practicum.diplom3.web.BrowserType.GOOGLE_CHROME;
import static ru.yandex.practicum.diplom3.web.BrowserType.YANDEX_BROWSER;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class RegisterTest extends BaseTest {

    private final String buttonChoice;

    private static final String LK_BUTTON = "lkButton";
    private static final String SIGN_IN_BUTTON = "signInButton";

    private static final String LOGIN = "Вход";
    private static final String INCORRECT_PASSWORD = "Некорректный пароль";

    public RegisterTest(String buttonChoice, BrowserType browserType) {
        this.buttonChoice = buttonChoice;
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {LK_BUTTON, GOOGLE_CHROME},
                {SIGN_IN_BUTTON, GOOGLE_CHROME},
                {LK_BUTTON, YANDEX_BROWSER},
                {SIGN_IN_BUTTON, YANDEX_BROWSER}
        };
    }

    @Before
    public void setUP() throws IOException {
        initBrowser(browserType);
        createUser();
    }

    @Test
    @DisplayName("Check user registration with valid data")
    public void a_registerValidPasswordTest() {
        assertEquals(LOGIN, openHomePage()
                .openLoginPage(buttonChoice)
                .openRegisterPage()
                .registrationUserValidData(user)
                .getTextLoginHeader());

        loginUserAndGetAccessToken();
    }

    @Test
    @DisplayName("Check user registration with invalid password")
    public void b_registerNotValidPasswordTest() {
        assertEquals(INCORRECT_PASSWORD, openHomePage()
                .openLoginPage(buttonChoice)
                .openRegisterPage()
                .registrationUserNotValidData(user)
                .getTextErrorMessage());
    }

    @After
    public void tearDown() {
        if(accessToken != null) {
            clearUserDate(accessToken);
        }

        browserClose();
    }

}