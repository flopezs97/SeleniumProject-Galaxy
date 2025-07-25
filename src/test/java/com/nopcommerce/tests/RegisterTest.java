package com.nopcommerce.tests;

import com.github.javafaker.Faker;
import com.nopcommerce.utils.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
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

    @Test(groups = {"Functional"})
    @Description("TC-Register-01")
    public void doRegisterUser(Method method) throws Exception {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, email, company, password, password);
        registerPage.clickOnRegister();
        registerPage.verifyValidRegister(Variables.expected_title);
        registerPage.showUserDetails(email, password);
    }

    @Test(groups = {"Functional", "Integration"})
    @Description("TC-Register-02")
    public void doRegisterUserWithNoRequiredFields(Method method) throws Exception {
        homePage.goToRegisterPage();
        registerPage.clickOnRegister();
        registerPage.verifyEmptyRequiredFields(Variables.expected_firstname, Variables.expected_lastname,
                Variables.expected_email, Variables.expected_password);
    }

    @Test(groups = {"Functional", "Regression"})
    @Description("TC-Register-03")
    public void doRegisterUserWithInvalidEmail(Method method) throws Exception {
        homePage.goToRegisterPage();
        registerPage.generateGender(randomGender);
        registerPage.registerUserDetails(firstName, lastName, invalidEmail, company, password, password);
        registerPage.clickOnRegister();
        registerPage.verifyValidEmail(Variables.expected_invalid_email);
    }

}
