package com.nopcommerce.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReport() {
        ExtentSparkReporter report = new ExtentSparkReporter("./extent-report/spark-report.html");
        report.config().setReportName("Report of NopCommerce");
        report.config().setDocumentTitle("Report of Nopcommerce tests");
        report.config().setTheme(Theme.DARK);
        extentReports.attachReporter(report);
        extentReports.setSystemInfo("NopCommerce", "QE Team");
        extentReports.setSystemInfo("Author", "Fabian Lopez Sarango");

        return extentReports;
    }
}
