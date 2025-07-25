package com.nopcommerce.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    private static final String reportName = new SimpleDateFormat("YYYYMMddhhmmss").format(new Date());

    public synchronized static ExtentReports createExtentReport() {
        ExtentSparkReporter report = new ExtentSparkReporter(System.getProperty("user.dir") + "/extent-report/" + reportName
                + ".html");
        report.config().setReportName("Report of NopCommerce");
        report.config().setDocumentTitle("Report of Nopcommerce tests");
        report.config().setTheme(Theme.DARK);
        extentReports.attachReporter(report);
        extentReports.setSystemInfo("NopCommerce", "QE Team");
        extentReports.setSystemInfo("Author", "Fabian Lopez Sarango");

        return extentReports;
    }
}
