package ru.yandex.practicum.diplom3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

abstract public class AbstractPage {
    protected HomePage homePage;
    protected AuthorizedUserHomePage authorizedUserHomePage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ProfilePage profilePage;
    protected RestorePasswordPage forgotPasswordPage;

    private static final String LK_BTN = ".//p[contains(text(),'Личный Кабинет')]";
    private static final String CONSTRUCTOR_BTN = ".//p[contains(text(),'Конструктор')]";
    private static final String LOGO_STELLAR_BURGERS = "/html/body/div/div/header/nav/div/a";

    @FindBy(how = How.XPATH, using = LK_BTN)
    protected SelenideElement lkButton;

    @FindBy(how = How.XPATH, using = CONSTRUCTOR_BTN)
    protected SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = LOGO_STELLAR_BURGERS)
    protected SelenideElement logoBurgers;

    protected void clickLkButton() {
        lkButton.click();
    }

    protected void clickLogoBurgers() {
        logoBurgers.should(visible).click();
    }

    protected void clickConstructorButton() {
        constructorButton.click();
    }

    public HomePage backToHomePage() {
        clickLogoBurgers();

        homePage = page(HomePage.class);
        homePage.waitPage();

        return homePage;
    }

    protected void waitPage() {
    }
}