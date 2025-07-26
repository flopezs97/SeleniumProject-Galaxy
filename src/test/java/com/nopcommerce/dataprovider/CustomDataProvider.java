package com.nopcommerce.dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {

    @DataProvider(name = "dp-email-already-register")
    public static Object[][] dataEmailRegister() {
        return new Object[][]{
                {"leonardo.metz@yahoo.com", "sRjt*J%#"},
                {"benedict.schuppe@hotmail.com", "#tb5v7*o7"}
        };
    }

    @DataProvider(name = "dp-invalid-credentials")
    public static Object[][] dataWithInvalidCredentials() {
        return new Object[][]{
                {"leonardo.metz@yahoo.com", "1234"},
                {"benedict.schuppe@hotmail.com", "1234"}
        };
    }
}
