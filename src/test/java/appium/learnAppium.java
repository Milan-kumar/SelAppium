package appium;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class learnAppium {

    public AndroidDriver driver;

    @BeforeClass(enabled = false)
    public void setupNative() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"3436b04d0204");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.0");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"");
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Auto\\Appium\\selendroid-test-app-0.17.0.apk");
        capabilities.setCapability("app-package","io.selendroid.testapp");
        capabilities.setCapability("app-activity",".HomeScreenActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @BeforeClass
    @Parameters("phone")
    public void setupBrowser(String phone) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"3436b04d0204");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.0");
        if(phone.equalsIgnoreCase("Redmi"))
            capabilities.setCapability("udid","3436b04d0204");
        else if(phone.equalsIgnoreCase("emulator")) {
            System.out.println("IN EMULATOR");
            capabilities.setCapability("udid", "emulator-5554");
        }
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability(MobileCapabilityType.APP,"C:\\Auto\\Appium\\selendroid-test-app-0.17.0.apk");
        capabilities.setCapability("app-package","io.selendroid.testapp");
        capabilities.setCapability("app-activity",".HomeScreenActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test(enabled = false)
    public void test() throws InterruptedException, IOException {
        List <WebElement> txtBoxList = driver.findElementsByClassName("android.widget.EditText");
        txtBoxList.get(0).sendKeys("Testing");
        Thread.sleep(10000);
        driver.hideKeyboard();
//        txtBoxList.get(1).sendKeys("EXP");
        driver.findElementById("startUserRegistration").click();
        driver.findElementById("inputUsername").sendKeys("Test");
        swipeScreen();
        Thread.sleep(5000);
        driver.findElementById("input_adds").click();
        File file = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("Screenshot.jpg"));
        ScreenOrientation currOrientation = driver.getOrientation();
        driver.rotate(currOrientation.LANDSCAPE);
        file = ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("OrientScreenshot.jpg"));


//        driver.findElementById("my_text_field").sendKeys("Testing");
//        driver.hideKeyboard();
//        driver.findElementById("exceptionTestField").sendKeys("EXCEPTION");
    }

    @Test(enabled =true)
    public void multiApp() throws InterruptedException {
        Thread.sleep(5000);
        driver.startActivity(new Activity("com.koushikdutta.vysor",".StartActivity"));
//        driver.findElementByClassName("android.widget.TextView").getText();
        System.out.println("Current Package Name: "+driver.getCurrentPackage()+driver.findElementByClassName("android.widget.TextView").getText());
        Thread.sleep(5000);
        driver.startActivity(new Activity("io.selendroid.testapp",".HomeScreenActivity"));
        System.out.println("Current Package Name: "+driver.getCurrentPackage());
//        driver.findElementById("my_text_field").sendKeys("Testing");
//        driver.hideKeyboard();
//        driver.findElementById("exceptionTestField").sendKeys("EXCEPTION");
    }

    @Test(enabled = false)
    public  void browserTest(){
        driver.get("http:www.google.com");
        Assert.assertEquals(driver.getTitle(),"Google");
    }

    private void swipeScreen() {
        Dimension dim = driver.manage().window().getSize();
        int height = dim.getHeight();
        int width = dim.getWidth();

        int startX = width/2;
        int endX = width/2;
        int startY = (int) (height*.40);
        int endY = (int) (height*.10);

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(startX,startY))
                .moveTo(PointOption.point(endX,endY))
                .release()
                .perform();
    }
}
