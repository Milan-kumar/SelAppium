import org.testng.TestNG;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FailedTestCases {

    @Test
    public void failedCases(){
        TestNG runner = new TestNG();
        List<String> failedList = new ArrayList<String>();
        failedList.add("C:\\Auto\\Appium\\appiumDemo\\target\\surefire-reports\\testng-failed.xml");
        runner.setTestSuites(failedList);
        runner.run();
    }
}
