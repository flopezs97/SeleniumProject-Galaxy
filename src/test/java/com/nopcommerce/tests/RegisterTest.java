package com.nopcommerce.tests;

import com.github.javafaker.Faker;
import com.nopcommerce.utils.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Locale;

@Epic("Non commerce Tests")
@Feature("Register Tests")
public class RegisterTest extends BaseTest {
    Faker faker = new Faker(new Locale("en-US"));
    int randomGender = faker.number().numberBetween(0, 1);
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String company = faker.company().name();
    String password = faker.internet().password(8, 10, true, true,
            true);
    String invalidEmail = faker.name().username();
    String weakPassword = faker.internet().password(1, 4);

    @Test(groups = {"Functional"})
    @Description("TC-Register-01")
    public void doSuccessfulRegister(Method method) throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, email, company, password, password);
        registerPage.clickOnRegister();
        registerPage.verifyValidRegister(Variables.expected_title);
    }

    @Test(groups = {"Functional", "Integration"})
    @Description("TC-Register-02")
    public void doRegisterUserWithNoRequiredFields(Method method) throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.clickOnRegister();
        registerPage.verifyEmptyRequiredFields(Variables.expected_firstname, Variables.expected_lastname,
                Variables.expected_email, Variables.expected_password);
    }

    @Test(groups = {"Functional", "Regression"})
    @Description("TC-Register-03")
    public void doRegisterUserWithInvalidEmail(Method method) throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, invalidEmail, company, password, password);
        registerPage.clickOnRegister();
        registerPage.verifyValidEmail(Variables.expected_invalid_email);
    }

    @Test(groups = {"Regression"})
    @Description("TC-Register-04")
    public void doRegisterWithUnsecurePassword(Method method) throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, email, company, weakPassword, weakPassword);
        registerPage.clickOnRegister();
        registerPage.verifySecurePassword(Variables.expected_unsecure_password);
    }

    @Test(groups = {"Functional", "Integration"})
    @Description("TC-Register-05")
    public void doRegisterWithNoMatchPasswords(Method method) throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, email, company, password, weakPassword);
        registerPage.clickOnRegister();
        registerPage.verifyConfirmPassword(Variables.expected_confirm_password);
    }

    @Test(groups = {"Integration"}, dataProvider = "dp-email-already-register")
    @Description("TC-Register-06")
    public void doRegisterWithRepeatedEmail(Method method, String repeatedEmail, String repeatedPass)
            throws InterruptedException {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, repeatedEmail, company, repeatedPass, repeatedPass);
        registerPage.clickOnRegister();
        registerPage.verifyRepeatedEmail(Variables.expected_email_repeated);
    }

    @DataProvider(name = "dp-email-already-register")
    public Object[][] dataEmailRegister() {
        return new Object[][]{
                {"leonardo.metz@yahoo.com", "sRjt*J%#"},
                {"benedict.schuppe@hotmail.com", "#tb5v7*o7"}
        };
    }
}
