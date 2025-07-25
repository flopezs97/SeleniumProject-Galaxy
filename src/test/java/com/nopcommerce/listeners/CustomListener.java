package com.nopcommerce.listeners;

import com.aventstack.extentreports.Status;
import com.nopcommerce.extentreports.ExtentManager;
import com.nopcommerce.logs.Log;
import com.nopcommerce.tests.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static com.nopcommerce.extentreports.ExtentTestManager.getTest;

public class CustomListener extends BaseTest implements ITestListener {
    WebDriver driver;

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is starting.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is succeed.");
        getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/jpg;base64," + ((TakesScreenshot) Objects.requireNonNull(driver))
                .getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test failed", getTest().addScreenCaptureFromBase64String(base64Screenshot)
                .getModel().getMedia().get(0));

        saveScreenshotPNG(driver);

        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        getTest().log(Status.SKIP, "Test skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but is in defined success ratio " + getTestMethodName(iTestResult));
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plan")
    public static String saveTextLog(String message) {
        return message;
    }

}
