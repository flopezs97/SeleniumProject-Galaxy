package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {

    /* Web elements */
    public By inputEmail = By.id("Email");
    public By inputPassword = By.id("Password");
    public By btnLogin = By.xpath("//button[text()='Log in']");
    public By labelErrorLogin = By.className("message-error");
    public By labelEmailError = By.id("Email-error");

    /* Constructor */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /* Methods */
    public void loginUser(String email, String password) {
        type(inputEmail, email);
        type(inputPassword, password);
    }

    public void clickOnLogin() {
        submit(btnLogin);
    }

    public void verifyValidCredentials(String invalidCredentials) {
        Assert.assertEquals(find(labelErrorLogin).getText(), invalidCredentials);
    }

    public void verifyEmptyLoginFields(String emptyLoginFields) {
        Assert.assertEquals(find(labelEmailError).getText(), emptyLoginFields);
    }

    public void verifyNoRegisterUser(String notFoundUser) {
        Assert.assertEquals(find(labelErrorLogin).getText(), notFoundUser);
    }

}
