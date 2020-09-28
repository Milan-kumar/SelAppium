package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("deprecation")
public class Reports {
	
	public ExtentHtmlReporter htmlReport;
	protected ExtentReports extent;
	protected ExtentTest test;
	
//	@BeforeTest
	public void setExtent() {
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/ExtentReport.html");
		htmlReport.config().setDocumentTitle("Automation Report");
		htmlReport.config().setReportName("Functional Report");
		htmlReport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);
		
		extent.setSystemInfo("Hostname", "LocalHost");
		extent.setSystemInfo("OS", "WIN 10");
		extent.setSystemInfo("Tester Name", "Milan");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public static File getScreenshot(WebDriver driver, String screenshotName) {
		String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File(System.getProperty("user.dir")+"/data/"+screenshotName+dateName+".PNG");
		try {
			FileUtils.copyFile(srcfile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetFile;
	}

}
