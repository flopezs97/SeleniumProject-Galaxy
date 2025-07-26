package com.nopcommerce.tests;

import com.github.javafaker.Faker;
import com.nopcommerce.dataprovider.CustomDataProvider;
import com.nopcommerce.utils.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Locale;

@Epic("Non commerce Tests")
@Feature("Login Tests")
public class LoginTest extends BaseTest {

    Faker faker = new Faker(new Locale("en-US"));
    String emailNotRegister = faker.internet().emailAddress();
    String passwordNotRegister = faker.internet().password(1, 4);

    @Test(groups = {"Regression", "Integration"}, dataProviderClass = CustomDataProvider.class, dataProvider = "dp-email-already-register")
    @Description("TC-Login-01")
    public void doSuccessfulLogin(Method method, String email, String password) {
        homePage.goToLoginPage();
        loginPage.loginUser(email, password);
        loginPage.clickOnLogin();
        homePage.verifyLoginSuccessful(Variables.expected_my_account);
    }

    @Test(groups = {"Functional"}, dataProviderClass = CustomDataProvider.class, dataProvider = "dp-invalid-credentials")
    @Description("TC-Login-02")
    public void doLoginWithInvalidCredentials(Method method, String email, String password) {
        homePage.goToLoginPage();
        loginPage.loginUser(email, password);
        loginPage.clickOnLogin();
        loginPage.verifyValidCredentials(Variables.expected_login_error);
    }

    @Test(groups = {"Functional"})
    @Description("TC-Login-03")
    public void doLoginWithEmptyFields(Method method) {
        homePage.goToLoginPage();
        loginPage.clickOnLogin();
        loginPage.verifyEmptyLoginFields(Variables.expected_empty_login_fields);
    }

    @Test(groups = {"Regression"})
    public void doLoginWithNoRegisterUser(Method method) {
        homePage.goToLoginPage();
        loginPage.loginUser(emailNotRegister, passwordNotRegister);
        loginPage.clickOnLogin();
        loginPage.verifyNoRegisterUser(Variables.expected_no_register_user_error);
    }
}
