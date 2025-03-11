package Base.TestHelpers;

import Base.Resources.ExtentReporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listeners extends BaseTest implements ITestListener {
    private ExtentTest test;
    private ExtentReports extent = ExtentReporterNG.getReportObject();
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = BaseTest.getDriver();

        if (driver != null) {
            String methodName = result.getMethod().getMethodName();
            String screenshotPath = captureScreenshot(methodName);
            test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");

            // Set the screenshotPath attribute in the ITestResult object
            result.setAttribute("screenshotPath", screenshotPath);
        } else {
            System.out.println("WebDriver is null. Cannot take a screenshot.");
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        // Implement this method if needed.
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Implement this method if needed.
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        cleanupExtentReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
