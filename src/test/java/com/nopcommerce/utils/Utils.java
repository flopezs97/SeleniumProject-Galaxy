package com.nopcommerce.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class Utils {


    public static void takeScreenOrder(WebDriver driver, String idOrder) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(source, new File("./screenshots/" + idOrder + ".jpg"));
    }
}
