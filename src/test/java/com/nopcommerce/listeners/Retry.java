package com.nopcommerce.listeners;

import com.aventstack.extentreports.Status;
import com.nopcommerce.tests.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static com.nopcommerce.extentreports.ExtentTestManager.getTest;

public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 1;

    @Override
    public boolean retry(ITestResult ITestResult) {
        if (!ITestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                ITestResult.setStatus(org.testng.ITestResult.FAILURE);
                extendReportsFailOperations(ITestResult);
                return true;
            }
        } else {
            ITestResult.setStatus(org.testng.ITestResult.SUCCESS);
        }
        return false;
    }

    public void extendReportsFailOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/jpg;base64," + ((TakesScreenshot) webDriver)
                .getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test failed", getTest().addScreenCaptureFromBase64String(base64Screenshot)
                .getModel().getMedia().get(0));
    }

}
