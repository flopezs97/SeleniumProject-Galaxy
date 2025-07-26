package com.nopcommerce.pages;

import com.nopcommerce.utils.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PasswordRecoveryPage extends BasePage {

    /* Web elements */
    public By labelPassRecovery = By.cssSelector("h1");
    public By inputEmail = By.id("Email");
    public By btnRecovery = By.cssSelector("button[name='send-email']");
    public By msgEmailSent = By.id("bar-notification");

    /* Constructor */
    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    /* Methods */
    public void recoveryPassword(String email) {
        type(inputEmail, email);
        submit(btnRecovery);
    }

    public void verifyRecoveryPage(String passwordRecovery) {
        Assert.assertEquals(find(labelPassRecovery).getText(), passwordRecovery);
    }

    public void verifyEmailRecoverySent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Variables.EXPLICIT_WAIT_TIME));
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(msgEmailSent));
        String actualText = notification.getText();
        Assert.assertEquals(find(msgEmailSent).getText(), actualText);
    }
}
