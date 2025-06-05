package BookStore;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import services.OmsStubService;
import services.WireMockManager;
import utils.ExtentManager;
//import services.WireMockManager;


import java.io.IOException;
import java.lang.reflect.Method;

public class BaseTest {
  public ExtentReports extent;
 public ExtentTest test;
    @BeforeSuite
    public void beforeSuite(){

       extent= ExtentManager.createInstance();
    }
    @AfterSuite
    public void tearDownExtentReport() {
        extent.flush();
    }
     @BeforeClass
     public void BeforeClass(ITestContext context) throws IOException {
         //WireMockManager.stopWiremockServer();
        WireMockManager.StartWiremock();
         OmsStubService.configureOms(context);
     }
   @AfterClass
    public void AfterClass(){
        WireMockManager.stopWiremockServer();
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
