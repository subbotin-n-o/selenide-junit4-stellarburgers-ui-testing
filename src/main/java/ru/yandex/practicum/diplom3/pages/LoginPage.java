package ru.yandex.practicum.diplom3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.practicum.diplom3.api.User;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class LoginPage extends AbstractPage {

    private static final String LOGIN_HEADER = ".//h2[contains(text(),'Вход')]";
    private static final String FIELD_EMAIL = ".//form/fieldset[1]/div/div/input";
    private static final String FIELD_PASSWORD = ".//form/fieldset[2]/div/div/input";
    private static final String REGISTER_BTN = ".//a[contains(text(),'Зарегистрироваться')]";
    private static final String RESTORE_PASSWORD_BTN = ".//a[contains(text(),'Восстановить пароль')]";

    @FindBy(how = How.XPATH, using = LOGIN_HEADER)
    protected SelenideElement loginHeader;

    @FindBy(how = How.XPATH, using = FIELD_EMAIL)
    protected SelenideElement fieldEmail;

    @FindBy(how = How.XPATH, using = FIELD_PASSWORD)
    protected SelenideElement fieldPassword;

    @FindBy(how = How.XPATH, using = REGISTER_BTN)
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = RESTORE_PASSWORD_BTN)
    private SelenideElement restorePasswordButton;

    public AuthorizedUserHomePage signIn(User user) {
        setFieldEmail(user.getEmail());
        setFieldPassword(user.getPassword());

        authorizedUserHomePage = page(AuthorizedUserHomePage.class);
        authorizedUserHomePage.waitPage();
        return authorizedUserHomePage;
    }

    private void setFieldEmail(String email) {
        fieldEmail.click();
        if (fieldEmail.getValue().equals(email)) {
            fieldEmail.pressTab();
        } else {
            fieldEmail.setValue(email);
        }
    }

    private void setFieldPassword(String password) {
        fieldPassword.click();
        if (fieldPassword.should(enabled).getValue().equals(password)) {
            fieldPassword.pressEnter();
        } else {
            fieldPassword.setValue(password).pressEnter();
        }
    }

    public RegisterPage openRegisterPage() {
        clickRegisterButton();

        registerPage = page(RegisterPage.class);
        registerPage.waitPage();
        return registerPage;
    }

    public RestorePasswordPage openRestorePasswordPage() {
        clickRestorePasswordButton();

        forgotPasswordPage = page(RestorePasswordPage.class);
        forgotPasswordPage.waitPage();
        return forgotPasswordPage;
    }

    private void clickRegisterButton() {
        registerButton.click();
    }

    private void clickRestorePasswordButton() {
        restorePasswordButton.click();
    }

    public String getTextLoginHeader() {
        return loginHeader.getText();
    }

    @Override
    public void waitPage() {
        loginHeader.should(visible);
    }

}