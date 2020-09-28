package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Test;

public class HeadLessTesting {
	WebDriver driver;
	
	@Test
	public void chromeHeadLess() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.setHeadless(true);
		//or we can use addArguments
//		option.addArguments("--headless");
		driver = new ChromeDriver(option);
		driver.get("http://www.google.com");	
		System.out.println("Title:: "+driver.getTitle());
		driver.quit();
	}
	
	@Test
	public void firefoxHeadLess() {
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		FirefoxOptions option = new FirefoxOptions();
		option.setHeadless(true);
		//or we can use addArguments
//		option.addArguments("--headless");
		driver = new FirefoxDriver(option);
		driver.get("http://www.amazon.com");	
		System.out.println("Title:: "+driver.getTitle());
		driver.quit();
	}

}
