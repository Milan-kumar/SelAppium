package web;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.print.attribute.HashAttributeSet;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utils.Reports;

public class BaseClass extends Reports {

	WebDriver driver;
	String baseDir = System.getProperty("user.dir");

	/*
	 * @BeforeTest
	 * 
	 * @Parameters({"browser"}) public void setup(String browser){ browser =
	 * (System.getProperty("BROWSER")!=null)?(System.getProperty("BROWSER")):
	 * browser; if(browser.equalsIgnoreCase("chrome")){
	 * System.setProperty("webdriver.chrome.driver",
	 * "C:\\Auto\\Appium\\appiumDemo\\driver\\chromedriver.exe"); driver = new
	 * ChromeDriver(); } if(browser.equalsIgnoreCase("firefox")){
	 * System.setProperty("webdriver.gecko.driver",
	 * "C:\\Auto\\Appium\\appiumDemo\\driver\\geckodriver.exe"); driver = new
	 * FirefoxDriver(); } driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	 * 
	 * }
	 */

//	@BeforeTest
	public void setup() {

		/*
		 * System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		 * ChromeOptions option = new ChromeOptions(); //
		 * option.setPageLoadStrategy(PageLoadStrategy.NONE);
		 * option.setAcceptInsecureCerts(true); driver = new ChromeDriver(option);
		 */

		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
		FirefoxProfile fProfile = new FirefoxProfile();

		// Set profile to accept untrusted certificates
		fProfile.setAcceptUntrustedCertificates(true);

		// Set profile to not assumet certificate issuer is untrusted
		fProfile.setAssumeUntrustedCertificateIssuer(false);
		// Set download location and file types
		fProfile.setPreference("browser.download.dir", baseDir + "\\data");
		/*
		 * Default Value: 0 This can be set to either 0, 1, or 2. When set to 0, Firefox
		 * will save all files on the user’s desktop. 1 saves the files in the Downloads
		 * folder and 2 saves file at the location specified for the most recent
		 * download
		 */
		fProfile.setPreference("browser.download.folderList", 2);
		/*
		 * Default Value: true, It allows the user to specify whether or not the
		 * Download Manager window is displayed when a file download is initiated.
		 */
		fProfile.setPreference("browser.download.manager.showWhenStarting", false);
		/*
		 * Default Value: empty string A comma-separated list of MIME types to save to
		 * disk without asking what to use to open the file.
		 */
//		fProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
//				"text/plain,application/pdf,text/csv,application/octet-stream");

//        fProfile.setPreference("browser.helperApps.neverAsk.openFile", "text/plain,application/pdf,application/octet-stream");
		// Disable internal pdf reader, Set this to true to disable the pdf opening
//		fProfile.setPreference("pdfjs.disabled", true);

		FirefoxOptions option = new FirefoxOptions();
		option.setProfile(fProfile);
		driver = new FirefoxDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeTest
	public void initialize() {
		setExtent();
		Map<String, Object> chromePref = new Hashtable<String, Object>();
		chromePref.put("download.default_directory", baseDir +File.separator+"data");	
//		chromePref.put( "browser.startup.homepage", "http://my.home.page" );
//		chromePref.put( "browser.startup.page", START_WITH_HOME_PAGE );
		ChromeOptions option = new ChromeOptions(); //
//		option.setPageLoadStrategy(PageLoadStrategy.NONE);
//		option.setAcceptInsecureCerts(true);
//		option.setHeadless(true);
//		option.addArguments("--incognito");
//		option.addArguments("--homepage \"http:\\www.google.com\"");
//		option.addArguments("--disable-extensions"); // disabling extensions
//	    option.addArguments("--disable-gpu"); // applicable to windows os only
//	    option.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//	    option.addArguments("--no-sandbox");//the sandbox removes unnecessary privileges from the processes that don't need them in Chrome, for security purposes. Disabling the sandbox makes your PC more vulnerable to exploits via webpages, so Google don't recommend it
//	    option.addArguments("--remote-debugging-port=9225");
		option.setExperimentalOption("prefs", chromePref);
		
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	}
	
//	@BeforeTest
	public void launchEdge() {
		System.setProperty("webdriver.edge.driver", "./driver/MicrosoftWebDriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

//	@AfterTest
	public void tearDown() {
		driver.quit();
		extent.flush();
	}
}
