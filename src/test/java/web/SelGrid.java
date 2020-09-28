package web;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SelGrid {
	
	RemoteWebDriver driver;
	DesiredCapabilities cap;
	
	@Test()
	@Parameters({"url","browser","port"})
	public void gridTest(String url,String browser,String port) throws MalformedURLException, InterruptedException {

		if(browser.equalsIgnoreCase("chrome")) {
//			ChromeOptions option = new ChromeOptions();
			cap = DesiredCapabilities.chrome();
			System.setProperty("webdriver.chrome.driver", "C:\\Auto\\Selenium\\SelAppium\\driver\\chromedriver.exe");
			cap.setBrowserName(BrowserType.CHROME);
			cap.setPlatform(Platform.WINDOWS);
			Thread.sleep(5000);
		}			
		else if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName(BrowserType.FIREFOX);
			cap.setPlatform(Platform.WINDOWS);
		}
//		
		driver = new RemoteWebDriver(new URL("http://10.0.75.1:"+port+"/wd/hub"), cap);
//		driver = new RemoteWebDriver(new URL("http://10.0.75.1:4444/wd/hub"), cap);
		
		driver.get(url);
		System.out.println(driver.getTitle());
		driver.quit();
	}

}