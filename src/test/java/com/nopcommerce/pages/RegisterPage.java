package com.nopcommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterPage extends BasePage {

    /* Web elements */
    public By genderMale = By.id("gender-male");
    public By genderFemale = By.id("gender-female");
    public By inputFirstName = By.id("FirstName");
    public By inputLastName = By.id("LastName");
    public By inputEmail = By.id("Email");
    public By inputCompanyName = By.id("Company");
    public By inputPassword = By.id("Password");
    public By inputConfirmPassword = By.id("ConfirmPassword");
    public By btnRegister = By.id("register-button");
    public By validRegisterTitle = By.xpath("//div[text()='Your registration completed']");
    public By labelFirstNameError = By.id("FirstName-error");
    public By labelLastNameError = By.id("LastName-error");
    public By labelEmailError = By.id("Email-error");
    public By labelPasswordError = By.id("Password-error");
    public By labelConfirmPasswordError = By.id("ConfirmPassword-error");

    /* Constructor */
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    /* Methods */
    public void generateGender(int numberGender) {
        if (numberGender == 0) {
            find(genderMale).click();
        } else {
            find(genderFemale).click();
        }
    }

    public void registerUserDetails(String firstname, String lastname, String email, String companyName, String password,
                                    String confirmPassword) {
        type(inputFirstName, firstname);
        type(inputLastName, lastname);
        type(inputEmail, email);
        type(inputCompanyName, companyName);
        type(inputPassword, password);
        type(inputConfirmPassword, confirmPassword);
    }

    public void clickOnRegister() {
        click(btnRegister);
    }

    public void verifyValidRegister(String expected_title_register) {
        Assert.assertEquals(find(validRegisterTitle).getText(), expected_title_register);
    }

    public void verifyEmptyRequiredFields(String firstNameError, String lastNameError, String emailError, String passwordError) {
        Assert.assertEquals(find(labelFirstNameError).getText(), firstNameError);
        Assert.assertEquals(find(labelLastNameError).getText(), lastNameError);
        Assert.assertEquals(find(labelEmailError).getText(), emailError);
        Assert.assertEquals(find(labelConfirmPasswordError).getText(), passwordError);
    }

    public void verifyValidEmail(String invalidEmailError) {
        Assert.assertEquals(find(labelEmailError).getText(), invalidEmailError);
    }

    public void verifySecurePassword(String unsecurePass) {
        Assert.assertEquals(find(labelPasswordError).getText(), unsecurePass);
    }

    public void verifyConfirmPassword(String confirmPassword) {
        Assert.assertEquals(find(labelConfirmPasswordError).getText(), confirmPassword);
    }

}
