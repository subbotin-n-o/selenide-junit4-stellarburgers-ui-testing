package ru.yandex.practicum.diplom3.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class AuthorizedUserHomePage extends HomePage {

    private static final String CHECKOUT = ".//button[contains(text(),'Оформить заказ')]";

    @FindBy(how = How.XPATH, using = CHECKOUT)
    private SelenideElement checkoutButton;

    public ProfilePage openProfilePage() {
        clickLkButton();

        profilePage = page(ProfilePage.class);
        profilePage.waitPage();
        return profilePage;
    }

    public Boolean isAuthorizedUserHomePage() {
        return checkoutButton.getText().equals("Оформить заказ");
    }

    @Override
    public void waitPage() {
        checkoutButton.should(visible);
    }
}