package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {

    /* Web elements */
    public By registerLink = By.cssSelector("a[class='ico-register']");
    public By loginLink = By.cssSelector("a[class='ico-login']");
    public By labelMyAccount = By.className("ico-account");

    /* Constructor */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    /* Methods */
    public void goToRegisterPage() {
        click(registerLink);
    }

    public void goToLoginPage() {
        click(loginLink);
    }

    public void verifyLoginSuccessful(String myAccount) {
        Assert.assertEquals(find(labelMyAccount).getText(), myAccount);
    }

}
