package web;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackTest {
	
	public static final String USERNAME = "milanyadav1";
	  public static final String AUTOMATE_KEY = "rnNnkLxCKy4Rn7z6F6gy";
	  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	  public static void main(String[] args) throws Exception {

	    DesiredCapabilities caps = new DesiredCapabilities();
//	    caps.setCapability("browserName", "iPhone");
//	    caps.setCapability("device", "iPhone 8 Plus");
//	    caps.setCapability("realMobile", "true");
//	    caps.setCapability("os_version", "11");
//	    caps.setCapability("name", "Bstack-[Java] Sample Test");
	    
	    caps.setCapability("os_version", "11");
	    caps.setCapability("device", "iPhone 8 Plus");
	    caps.setCapability("real_mobile", "true");
	    caps.setCapability("project", "Browser Stack IOS");
	    caps.setCapability("build", "Test build 1.1");
	    caps.setCapability("name", "IOS Google Test");
	    caps.setCapability("browserstack.local", "false");

	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    driver.get("http://www.google.com/ncr");
	    WebElement element = driver.findElement(By.name("q"));

	    element.sendKeys("BrowserStack");
	    element.submit();

	    System.out.println(driver.getTitle());
	    driver.quit();

	  }
}
