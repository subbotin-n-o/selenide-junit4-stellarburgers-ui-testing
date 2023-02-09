package ru.yandex.practicum.diplom3.web;

import org.junit.Before;
import org.junit.Test;
import ru.yandex.practicum.diplom3.pages.HomePage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;
import static ru.yandex.practicum.diplom3.web.BrowserType.GOOGLE_CHROME;
import static ru.yandex.practicum.diplom3.web.BrowserType.YANDEX_BROWSER;

public class LoginTest extends BaseTest {

    private static final String LK_BUTTON = "lkButton";
    private static final String SIGN_IN_BUTTON = "signInButton";

    @Before
    public void setUP() throws IOException {
        initBrowser(GOOGLE_CHROME);
    }

    @Test
    public void checkLoginRegisteredUser() {

        assertTrue(open(BASE_URL, HomePage.class)
                .openLoginPage(SIGN_IN_BUTTON)
                .openRegisterPage()
                .registrationUserValidData("katlyn", "katlyn.towne@yahoo.com", "0cxihbc8")
                .signIn("katlyn.towne@yahoo.com", "0cxihbc8")
                .openProfilePage()
                .isProfilePage());
    }

}