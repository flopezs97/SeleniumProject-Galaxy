package com.nopcommerce.tests;

import com.nopcommerce.dataprovider.CustomDataProvider;
import com.nopcommerce.utils.Variables;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

@Epic("Non commerce Tests")
@Feature("Login Tests")
public class LoginTest extends BaseTest {

    @Test(groups = {"Regression", "Integration"},
            dataProviderClass = CustomDataProvider.class,
            dataProvider = "dp-email-already-register")
    @Description("TC-Login-01")
    public void doSuccessfulLogin(Method method, String email, String password) {
        homePage.goToLoginPage();
        loginPage.loginUser(email, password);
        homePage.verifyLoginSuccessful(Variables.expected_my_account);
    }

    @Test(groups = {"Functional"}, dataProviderClass = CustomDataProvider.class,
            dataProvider = "dp-invalid-credentials")
    @Description("TC-Login-02")
    public void doLoginWithInvalidCredentials(Method method, String email, String password) {
        homePage.goToLoginPage();
        loginPage.loginUser(email, password);
        loginPage.verifyValidCredentials(Variables.expected_login_error);
    }
}
