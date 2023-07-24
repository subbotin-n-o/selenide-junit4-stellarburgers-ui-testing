package ru.yandex.practicum.diplom3.web;

import io.qameta.allure.Description;
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
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.diplom3.web.BrowserType.GOOGLE_CHROME;
import static ru.yandex.practicum.diplom3.web.BrowserType.YANDEX_BROWSER;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Parameterized.class)
public class ButtonTransitionTest extends BaseTest {

    private static final String SIGN_IN_BUTTON = "signInButton";

    private static final String LOGIN = "Вход";

    public ButtonTransitionTest(BrowserType browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {GOOGLE_CHROME},
                {YANDEX_BROWSER}
        };
    }

    @Before
    public void setUP() throws IOException {
        initBrowser(browserType);
        createUser();
    }

    @Test
    @DisplayName("Check transition by clicking on the \"Personal Account\"")
    @Description("Checking the transition by the \"Personal Account\" button from the Home page")
    public void a_transitionClickLkButtonTest() {
        registerUserAndGetAccessToken();

        assertTrue(openHomePage()
                .openLoginPage(SIGN_IN_BUTTON)
                .signIn(user)
                .openProfilePage()
                .isProfilePage());
    }

    @Test
    @DisplayName("Check the transition by clicking on the \"Constructor\"")
    @Description("Checking the transition by the \"Constructor\" button from the Personal Account page")
    public void b_transitionByClickConstructorButtonTest() {
        registerUserAndGetAccessToken();

        assertTrue(openHomePage()
                .openLoginPage(SIGN_IN_BUTTON)
                .signIn(user)
                .openProfilePage()
                .transitionByClickConstructorButton()
                .isAuthorizedUserHomePage());
    }

    @Test
    @DisplayName("Check the transition by clicking on the \"Logo\"")
    @Description("Checking the transition by the \"Stellar Burgers\" button from the Personal Account page")
    public void c_transitionByClickLogoBurgersTest() {
        registerUserAndGetAccessToken();

        assertTrue(openHomePage()
                .openLoginPage(SIGN_IN_BUTTON)
                .signIn(user)
                .openProfilePage()
                .transitionByClickLogoBurgers()
                .isAuthorizedUserHomePage());
    }

    @Test
    @DisplayName("Check Logout")
    @Description("Checking out of your account")
    public void d_logoutTest() {
        registerUserAndGetAccessToken();

        assertEquals(LOGIN, openHomePage()
                .openLoginPage(SIGN_IN_BUTTON)
                .signIn(user)
                .openProfilePage()
                .logOut()
                .getTextLoginHeader());
    }

    @Test
    @DisplayName("Check transition to buns section")
    public void e_transitionToBunsSectionTest() {
        assertTrue(openHomePage()
                .clickSaucesButton()
                .clickFillingButton()
                .clickBunsButton()
                .isBunsHeaderVisible());
    }

    @Test
    @DisplayName("Check transition to sauces section")
    public void f_transitionToSaucesSectionTest() {
        assertTrue(openHomePage()
                .clickFillingButton()
                .clickBunsButton()
                .clickSaucesButton()
                .isSaucesHeaderVisible());
    }

    @Test
    @DisplayName("Check transition to filling section")
    public void g_transitionToFillingSectionTest() {
            assertTrue(openHomePage()
                    .clickSaucesButton()
                    .clickBunsButton()
                    .clickFillingButton()
                    .isFillingHeaderVisible());
    }

    @After
    public void tearDown() {
        if(accessToken != null) {
            clearUserDate(accessToken);
        }
        browserClose();
    }
}