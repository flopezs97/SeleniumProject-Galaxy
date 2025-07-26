package com.nopcommerce.tests;

import com.github.javafaker.Faker;
import com.nopcommerce.logs.Log;
import com.nopcommerce.pages.HomePage;
import com.nopcommerce.pages.LoginPage;
import com.nopcommerce.pages.PasswordRecoveryPage;
import com.nopcommerce.pages.RegisterPage;
import com.nopcommerce.utils.Variables;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Locale;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected PasswordRecoveryPage passwordRecoveryPage;

    public Faker faker;
    public JavascriptExecutor js;
    public Actions actions;

    @BeforeTest
    public void loadSettings() {
        Log.info("Loading base URL for all testing");
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Variables.TIME_WAIT));
        driver.manage().window().maximize();
        driver.get(Variables.BASE_URL);

        faker = new Faker(new Locale("en-US"));
        actions = new Actions(driver);

        Log.info("Initializing Page Object Model");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        js = (JavascriptExecutor) driver;
    }

    @AfterMethod
    public void teardown() {
        Log.info("Closing the driver instance");
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
