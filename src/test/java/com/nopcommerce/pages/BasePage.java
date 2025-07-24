package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    WebDriverWait wait;
    public int timeoutSec = 10;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
    }

    public void setTimeoutSec(int timeoutSec) {
        this.timeoutSec = timeoutSec;
    }

    public WebElement find(By element) {
        return driver.findElement(element);
    }

    public void click(By element) {
        find(element).click();
    }

    public void submit(By element) {
        find(element).submit();
    }

    public void type(By element, String text) {
        find(element).sendKeys(text);
    }

    public void typeAndEnter(By element, String text) {
        find(element).sendKeys(text, Keys.ENTER);
    }

    public String text(By element) {
        return find(element).getText();
    }

    public void selectByValue(By element, String text) {
        Select dropDown = new Select(find(element));
        dropDown.selectByValue(text);
    }

    public boolean isDisplayed(By element) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void changeIframe(By element) {
        driver.switchTo().frame(find(element));
    }

    public void goOutOfIframe() {
        driver.switchTo().defaultContent();
    }
}
