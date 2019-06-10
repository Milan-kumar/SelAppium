package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;


public class testAppium {

    public static void main(String[] args) throws MalformedURLException,Exception {
        DesiredCapabilities dc = new DesiredCapabilities();
//        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
//        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,7.0);//For real device platform version in ""
        dc.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");//"Android" for real device
//        dc.setCapability(MobileCapabilityType.APP,"C:\\Auto\\Appium\\IndiGo_v4.2.8_apkpure.com.apk");
        dc.setCapability(MobileCapabilityType.BROWSER_NAME,"");
        //To get appname and activity
        // adb shell "dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp
//        aapt dump badging AppApkName.apk OR ./ aapt dump badging AppApkName.apkMethod

        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"io.selendroid.testapp");
//        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"in.goindigo.android");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"io.selendroid.testapp.HomeScreenActivity");
//        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"in.goindigo.android.MainActivity");
        dc.setCapability(MobileCapabilityType.APP,"C:\\Auto\\Appium\\selendroid-test-app-0.17.0.apk");


        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(url,dc);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.findElement(By.xpath("//*[text()=\"Continue as Guest\"]")).click();
//        driver.findElementById("android:id/button1").click();
//        Alert alert =
//        driver.switchTo().alert().accept();
//        alert.accept();
//        driver.switchTo().defaultContent();
        driver.findElementById("io.selendroid.testapp:id/startUserRegistration").click();
//        Assert.assertEquals(driver.findElementByClassName("android.widget.TextView").getText(),"Welcome to register a new User");
        driver.findElementByClassName("android.widget.EditText").sendKeys("Test");
        driver.findElementByAccessibilityId("email of the customer").sendKeys("test@test.com");
        driver.hideKeyboard();
        driver.findElement(By.id("io.selendroid.testapp:id/inputPassword")).sendKeys("password");
        driver.hideKeyboard();
        driver.findElementById("io.selendroid.testapp:id/inputName").clear();
        driver.hideKeyboard();
        driver.findElementById("io.selendroid.testapp:id/inputName").sendKeys("Mr Test");
        driver.hideKeyboard();
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"I accept adds\")").click();
        Thread.sleep(5000);



        driver.quit();
    }
}
