package HRM.qa.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class MyListener extends TestListenerAdapter {

    @Override
    public void onStart(ITestContext context) {
        Log.info("Test Suite Started: " + context.getName());
        ExtentReportManager.initReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.info("Test Started: " + testName);
        ExtentReportManager.createTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.info("Test Passed: " + testName);
        ExtentReportManager.logPass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Throwable error = result.getThrowable();

        Log.error("Test Failed: " + testName);
        Log.error("Exception: " + error.getMessage(), error);
        ExtentReportManager.logFail("Test Failed: " + error.getMessage());

        String path = TestUtil.captureScreenshot(testName);
        Log.info("Screenshot captured: " + path);
        ExtentReportManager.attachScreenshot(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        Log.warn("Test Skipped: " + testName);
        ExtentReportManager.logSkip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("Test Suite Finished: " + context.getName());
        ExtentReportManager.flushReports();
    }

}
