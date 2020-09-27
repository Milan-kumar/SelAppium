package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class myListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
     System.out.println("Test Started");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test Passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test Failed");
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Suite Started");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Suite Finished");
    }
}
