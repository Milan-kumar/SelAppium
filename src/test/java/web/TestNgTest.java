package web;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import utils.myRetryAnalyzer;

public class TestNgTest {

    @Test(invocationCount = 1,groups = "smoke")
    public void x(){
        System.out.println("method x");
    }

    @Test(priority = -1,groups = "Regression")
    public void b(){
        System.out.println("method b");
        Assert.assertEquals(true,true);
    }

    @Test(groups = "Regression",dataProvider = "getData",enabled = true)
    public void a(String input, String message){
        System.out.println("method a");
        System.out.println("Input: "+input+" Message: "+message);
        Assert.assertEquals("Success Message",message);
    }

    @DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[2][2];
        data[0][0] = "Input1";
        data[0][1] = "Error Message";
        data[1][0] = "Input2";
        data[1][1] = "Success Message";
        return data;
    }
}
