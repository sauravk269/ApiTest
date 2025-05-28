package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport_" + timestamp + ".html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Api Automation step by step");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        return  extent;
    }
}
