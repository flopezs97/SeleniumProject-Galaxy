package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    /* Web elements */
    public By registerLink = By.cssSelector("a[class='ico-register']");
    public By loginLink = By.cssSelector("a[class='ico-login']");

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
    
}
