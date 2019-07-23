package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class winHandle {
    WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setup(String browser){
        browser = (System.getProperty("BROWSER")!=null)?(System.getProperty("BROWSER")): browser;
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\Auto\\Appium\\appiumDemo\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        if(browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\Auto\\Appium\\appiumDemo\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test
    @Parameters("url")
    public void test(String url){
        driver.get(url);
        String parentHandle = driver.getWindowHandle();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"Welcome to HDFC Bank NetBanking");
//        System.out.println(driver.getTitle());
        driver.switchTo().frame("footer");
        driver.findElement(By.xpath("//a[text()=\"Terms and Conditions\"]")).click();
        WebDriverWait wait  = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.titleContains("HDFC"));
        Set<String> winHandles = driver.getWindowHandles();

        for(String windows:winHandles){
            System.out.println(windows);
            System.out.println(driver.getTitle());
            if(!windows.equalsIgnoreCase(parentHandle)){
                driver.switchTo().window(windows);
                System.out.println(driver.getTitle());
                Assert.assertTrue(driver.getTitle().contains("HDFC Bank"),"Terms Pop UP");
            }
        }
        driver.close();
        driver.switchTo().window(parentHandle);
        System.out.println(driver.getTitle());
        softAssert.assertAll();
//        Assert.assertEquals(driver.getTitle(),"Google");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}