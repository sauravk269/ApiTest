package BookStore;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ExtentManager;

import java.lang.reflect.Method;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;
    @BeforeSuite
    public void beforeSuite(){

       extent= ExtentManager.createInstance();
    }
    @AfterSuite
    public void tearDownExtentReport() {
        extent.flush();
    }

    @BeforeMethod
    public void createTest(Method method) {
        test = extent.createTest(method.getName());
    }
    @AfterMethod
    public void captureResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }
    }
}
