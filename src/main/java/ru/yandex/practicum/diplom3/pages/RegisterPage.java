package ru.yandex.practicum.diplom3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.practicum.diplom3.api.User;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class RegisterPage extends AbstractPage {

    private static final String REGISTER_HEADER = ".//h2[contains(text(),'Регистрация')]";
    private static final String FIELD_NAME = ".//form/fieldset[1]/div/div/input";
    private static final String FIELD_EMAIL = ".//form/fieldset[2]/div/div/input";
    private static final String FIELD_PASSWORD = ".//form/fieldset[3]/div/div/input";
    private static final String REGISTER_BTN = ".//button[contains(text(),'Зарегистрироваться')]";
    private static final String SIGN_IN_BTN = ".//a[contains(text(),'Войти')]";
    private static final String ERROR_MESSAGE = ".input__error";

    @FindBy(how = How.XPATH, using = REGISTER_HEADER)
    private SelenideElement registerHeader;

    @FindBy(how = How.XPATH, using = FIELD_NAME)
    private SelenideElement fieldName;

    @FindBy(how = How.XPATH, using = FIELD_EMAIL)
    private SelenideElement fieldEmail;

    @FindBy(how = How.XPATH, using = FIELD_PASSWORD)
    private SelenideElement fieldPassword;

    @FindBy(how = How.XPATH, using = REGISTER_BTN)
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = SIGN_IN_BTN)
    private SelenideElement signInButton;

    @FindBy(how = How.CSS, using = ERROR_MESSAGE)
    private SelenideElement errorMessage;

    public LoginPage registrationUserValidData(User user) {
        setFieldName(user.getName());
        setFieldEmail(user.getEmail());
        setFieldPassword(user.getPassword());
        clickRegisterButton();

        loginPage = page(LoginPage.class);
        loginPage.waitPage();

        return loginPage;
    }

    public RegisterPage registrationUserNotValidData(User user) {
        setFieldName(user.getName());
        setFieldEmail(user.getEmail());
        setFieldPassword(user.getNotValidPassword());
        clickRegisterButton();

        return this;
    }

    public LoginPage openLoginPage() {
        clickSignInButton();

        loginPage = page(LoginPage.class);
        loginPage.waitPage();

        return loginPage;
    }

    private void setFieldName(String name) {
        fieldName.setValue(name);
    }

    private void setFieldEmail(String email) {
        fieldEmail.setValue(email);
    }

    private void setFieldPassword(String password) {
        fieldPassword.setValue(password);
    }

    private void clickRegisterButton() {
        registerButton.click();
    }

    private void clickSignInButton() {
        signInButton.click();
    }

    public String getTextErrorMessage() {
        return errorMessage.should(visible).getText();
    }

    @Override
    public void waitPage() {
        registerHeader.should(visible);
    }

}