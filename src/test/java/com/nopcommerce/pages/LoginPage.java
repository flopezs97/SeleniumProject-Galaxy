package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    /* Web elements */
    public By inputEmail = By.id("Email");
    public By inputPassword = By.id("Password");
    public By btnLogin = By.xpath("//button[text()='Log in']");

    /* Constructor */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /* Methods */
    public void loginUser(String email, String password) {
        type(inputEmail, email);
        type(inputPassword, password);
        submit(btnLogin);
    }

}
