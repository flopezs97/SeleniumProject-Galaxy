package com.nopcommerce.utils;

public class Variables {
    public static final String BASE_URL = "https://demo.nopcommerce.com/";
    public static String expected_title = "Your registration completed";
    public static String expected_firstname = "First name is required.";
    public static String expected_lastname = "Last name is required.";
    public static String expected_email = "Email is required.";
    public static String expected_password = "Password is required.";
    public static String expected_invalid_email = "Please enter a valid email address.";
    public static String expected_unsecure_password = "Password must meet the following rules: must have at least 6 characters " +
            "and not greater than 64 characters";
    public static String expected_confirm_password = "The password and confirmation password do not match.";
    public static String expected_email_repeated = "The specified email already exists";
    public static String expected_my_account = "My account";
    public static String expected_login_error = "Login was unsuccessful. Please correct the errors and try again.\n"
            + "The credentials provided are incorrect";
    public static String expected_empty_login_fields = "Please enter your email";
    public static String expected_no_register_user_error = "Login was unsuccessful. Please correct the errors and try again.\n"
            + "No customer account found";
}
