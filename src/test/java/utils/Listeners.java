package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

//Either extend TestListenerAdapter class or implement ITestListener interface 
public class Listeners extends TestListenerAdapter {
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Started");
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		System.out.println("Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		System.out.println("Test Failed");
	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		System.out.println("Test Skipped");
	}

}
