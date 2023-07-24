package ru.yandex.practicum.diplom3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class ProfilePage extends AbstractPage {

    private static final String PROFILE_TEXT = ".//p[contains(text(),'В этом разделе')]";
    private static final String LOGOUT_BTN = ".//button[contains(text(),'Выход')]";

    @FindBy(how = How.XPATH, using = PROFILE_TEXT)
    private SelenideElement profilePageText;

    @FindBy(how = How.XPATH, using = LOGOUT_BTN)
    private SelenideElement logoutButton;

    public Boolean isProfilePage() {
        return profilePageText.getText().equals("В этом разделе вы можете изменить свои персональные данные");
    }

    public AuthorizedUserHomePage transitionByClickConstructorButton() {
        clickConstructorButton();

        authorizedUserHomePage = page(AuthorizedUserHomePage.class);
        authorizedUserHomePage.waitPage();

        return authorizedUserHomePage;
    }

    public AuthorizedUserHomePage transitionByClickLogoBurgers() {
        clickLogoBurgers();

        authorizedUserHomePage = page(AuthorizedUserHomePage.class);
        authorizedUserHomePage.waitPage();

        return authorizedUserHomePage;
    }

    public LoginPage logOut() {
        clickLogoutButton();

        loginPage = page(LoginPage.class);
        loginPage.waitPage();
        return loginPage;
    }

    private void clickLogoutButton() {
        logoutButton.click();
    }

    @Override
    public void waitPage() {
        profilePageText.should(visible);
    }
}
