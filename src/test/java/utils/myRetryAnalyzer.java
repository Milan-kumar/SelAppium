package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class myRetryAnalyzer implements IRetryAnalyzer {
    int counter = 0;
    int retryLimit = 2;
    public boolean retry(ITestResult iTestResult) {
        if(counter < retryLimit){
            counter++;
            return true;
        }
        else
            return false;
    }
}
