package ru.yandex.practicum.diplom3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class HomePage extends AbstractPage {

    private static final String SIGN_IN_BTN = ".//button[contains(text(),'Войти в аккаунт')]";

    private static final String BUNS_BTN = ".//main/section[1]/div[1]/div[1]";
    private static final String SAUCES_BTN = ".//main/section[1]/div[1]/div[2]";
    private static final String FILLING_BTN = ".//main/section[1]/div[1]/div[3]";

    private static final String BUNS_HEADER = ".//h2[contains(text(),'Булки')]";
    private static final String SAUCES_HEADER = ".//h2[contains(text(),'Соусы')]";
    private static final String FILLING_HEADER = ".//h2[contains(text(),'Начинки')]";

    @FindBy(how = How.XPATH, using = SIGN_IN_BTN)
    private SelenideElement signInButton;

    @FindBy(how = How.XPATH, using = BUNS_BTN)
    private SelenideElement bunsButton;

    @FindBy(how = How.XPATH, using = SAUCES_BTN)
    private SelenideElement saucesButton;

    @FindBy(how = How.XPATH, using = FILLING_BTN)
    private SelenideElement fillingButton;

    @FindBy(how = How.XPATH, using = BUNS_HEADER)
    private SelenideElement bunsHeader;

    @FindBy(how = How.XPATH, using = SAUCES_HEADER)
    private SelenideElement saucesHeader;

    @FindBy(how = How.XPATH, using = FILLING_HEADER)
    private SelenideElement fillingHeader;

    public LoginPage openLoginPage(String buttonName) {
        if (buttonName.equals("lkButton")) {
            clickLkButton();

            loginPage = page(LoginPage.class);
            loginPage.waitPage();
            return loginPage;

        } else if (buttonName.equals("signInButton")) {
            clickSignInButton();

            loginPage = page(LoginPage.class);
            loginPage.waitPage();
            return loginPage;
        }
        return null;
    }

    private void clickSignInButton() {
        signInButton.click();
    }

    public HomePage clickBunsButton() {
        bunsButton.click();

        return this;
    }

    public HomePage clickSaucesButton() {
        saucesButton.click();

        return this;
    }

    public HomePage clickFillingButton() {
        fillingButton.click();

        return this;
    }

    public boolean isBunsHeaderVisible() {
        return bunsHeader.should(visible, Duration.ofSeconds(6000)).getText().equals("Булки");
    }

    public boolean isSaucesHeaderVisible() {
        return saucesHeader.should(visible, Duration.ofSeconds(6000)).getText().equals("Соусы");
    }

    public boolean isFillingHeaderVisible() {
        return fillingHeader.should(visible, Duration.ofSeconds(6000)).getText().equals("Начинки");
    }

    @Override
    public void waitPage() {
        signInButton.should(visible);
    }
}