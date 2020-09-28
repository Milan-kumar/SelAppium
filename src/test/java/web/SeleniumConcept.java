package web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.appium.java_client.functions.ExpectedCondition;
import utils.JavaScriptUtil;
import utils.JavaUtil;
import utils.Xls_Reader;

public class SeleniumConcept<E> extends BaseClass {

	@Test(enabled = false)
	public void winHandle() throws InterruptedException {
		driver.get("http://demo.automationtesting.in/Windows.html");
		String BaseWindow = driver.getWindowHandle();
		driver.findElement(By.cssSelector("a>.btn.btn-info")).click();
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Number of windows" + windows.size());
		Iterator<String> iterator = windows.iterator();
		while (iterator.hasNext()) {
			String window = iterator.next();
			System.out.println("Window:: " + window);
			if (!BaseWindow.equals(window)) {
				driver.switchTo().window(window);
				System.out.println("Switching to Child Window:: " + driver.getTitle());
				driver.close();
			}
		}

		driver.switchTo().window(BaseWindow);
		System.out.println("Switching to Base Window:: " + driver.getTitle());
		System.out.println("*************Multi Windows**********************");

		driver.findElement(By.linkText("Open Seperate Multiple Windows")).click();
		driver.findElement(By.cssSelector("div>.btn.btn-info")).click();
		Thread.sleep(5000);
		Set<String> childWindows = driver.getWindowHandles();
		for (String window : childWindows) {
			System.out.println("Child Window:: " + window);
			if (!BaseWindow.equals(window)) {
				driver.switchTo().window(window);
				System.out.println("Switching to Child Window:: " + driver.getTitle());
				driver.close();
			}
		}
		driver.switchTo().window(BaseWindow);
		System.out.println("Switching to Base Window:: " + driver.getTitle());

	}

//	@Test
	public void authenticationPopUp() {
		// http://username:password@url
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		System.out.println("Title:: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "The Internet");
	}

//	@Test
	public void frame() {
		driver.get("http://demo.automationtesting.in/Frames.html");
		driver.switchTo().frame("SingleFrame");
		driver.findElement(By.xpath("//div[@class=\"container\"]//input")).sendKeys("Check Frame");
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Iframe with in an Iframe")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src=\"MultipleFrames.html\"]")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src=\"SingleFrame.html\"]")));
		driver.findElement(By.xpath("//div[@class=\"container\"]//input")).sendKeys("Check Frame");
//		driver.findElements(By.xpath("//div[@class=\"container\"]//input")).get(0).sendKeys("Check Frame");
	}

//	@Test
	public void alerts() {
		driver.get("http://demo.automationtesting.in/Alerts.html");
		Alert alert;
		driver.findElement(By.linkText("Alert with OK")).click();
		driver.findElement(By.className("btn-danger")).click();
		alert = driver.switchTo().alert();
		System.out.println("Alert with OK:: " + alert.getText());
		alert.accept();
		driver.findElement(By.linkText("Alert with OK & Cancel")).click();
		driver.findElement(By.className("btn-primary")).click();
		alert = driver.switchTo().alert();
		System.out.println("Alert with OK & Cancel:: " + alert.getText());
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "You Pressed Cancel");
		driver.findElement(By.className("btn-primary")).click();
		alert = driver.switchTo().alert();
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), "You pressed Ok");
		driver.findElement(By.linkText("Alert with Textbox")).click();
		driver.findElement(By.className("btn-info")).click();
		alert = driver.switchTo().alert();
		alert.sendKeys("Testing Alert textbox");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("demo1")).getText(),
				"Hello Testing Alert textbox How are you today");
	}

//	@Test
	public void actions() throws InterruptedException {
		driver.get("http://demo.automationtesting.in/Static.html");
		Actions action = new Actions(driver);
//		Thread.sleep(5000);
//		action.moveToElement(driver.findElement(By.linkText("Interactions")))
//				.moveToElement(driver.findElement(By.xpath("//a[text()='Drag and Drop ']")))
//				.moveToElement(driver.findElement(By.xpath("//a[text()='Dynamic ']")))
//				.click()
//				.build().perform();
//		Thread.sleep(5000);
		WebElement source = driver.findElement(By.id("mongo"));
		WebElement target = driver.findElement(By.id("droparea"));
//		action.clickAndHold(source).moveToElement(target).release().build().perform();
		action.dragAndDrop(source, target).build().perform();
		action.contextClick(source).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build()
				.perform();
		Thread.sleep(5000);

	}

//	@Test
	public void brokenlinks() throws IOException, InterruptedException {
		driver.get("http://www.newtours.demoaut.com");
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
//		HashSet<WebElement> _links = new HashSet<WebElement>(links);
//		System.out.println(_links.size());
		for (WebElement link : links) {
			String elementUrl = link.getAttribute("href");
			URL url = new URL(elementUrl);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			Thread.sleep(2000);
			int responseCode = httpConnection.getResponseCode();
			if (responseCode >= 400)
				System.out.println(url + " : is broken link");
			else
				System.out.println(url + " : is valid link");

		}
	}

//	@Test
	public void download() throws InterruptedException, IOException {
		driver.get("http://demo.automationtesting.in/FileDownload.html");
//		System.out.println("File deleted:: " + Files.deleteIfExists(Paths.get(baseDir + "//data//info.pdf")));

//		driver.findElement(By.id("textbox")).sendKeys("Testing TXT Download");
//		driver.findElement(By.id("createTxt")).click();
//		driver.findElement(By.id("link-to-download")).click();
		driver.findElement(By.id("pdfbox")).sendKeys("Testing PDF Download");
		driver.findElement(By.id("createPdf")).click();
		driver.findElement(By.id("pdf-link-to-download")).click();
//		driver.findElement(By.xpath("//a[@type='button' and text()='Download']")).click();
		Thread.sleep(5000);
		Assert.assertEquals(new File(baseDir + "//data//info.pdf").exists(), true);
	}

//	@Test
	public void downloadRobot() throws InterruptedException, AWTException, IOException {
		driver.get("http://demo.automationtesting.in/FileDownload.html");
//		driver.findElement(By.id("textbox")).sendKeys("Testing TXT Download");
//		driver.findElement(By.id("createTxt")).click();
//		driver.findElement(By.id("link-to-download")).click();
		File file = new File(baseDir + "\\Data\\info.pdf");
		file.delete();
		driver.findElement(By.id("pdfbox")).sendKeys("Testing PDF Download");
		driver.findElement(By.id("createPdf")).click();
		driver.findElement(By.id("pdf-link-to-download")).click();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(5000);
//		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);

//		driver.findElement(By.xpath("//a[@type='button' and text()='Download']")).click();
		Thread.sleep(5000);
		Assert.assertTrue(file.delete());
	}

//	@Test
	public void upload() throws InterruptedException {
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.findElement(By.id("imagesrc")).sendKeys(baseDir + "//data//info.pdf");
		Thread.sleep(5000);
	}

//	@Test
	public void uploadSikuli() throws InterruptedException, FindFailed {
		driver.get("http://demo.automationtesting.in/FileUpload.html");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='btn btn-primary btn-file']")).click();

		Screen s = new Screen();
		Pattern filePathTxtBox = new Pattern(baseDir + "/Data/FilePath.JPG");
		Pattern openButton = new Pattern(baseDir + "/Data/OpenButton.JPG");

		s.type(filePathTxtBox, baseDir + "\\Data\\info.pdf");
		s.click(openButton);
		Thread.sleep(5000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class=\"file-footer-caption\"]")).getText().contains("info.pdf"),
				true);
	}

	// @Test
	public void uploadAutoIt() throws InterruptedException, IOException {
		driver.get("http://demo.automationtesting.in/FileUpload.html");
//		Thread.sleep(10000);
		driver.findElement(By.xpath("//div[@class='btn btn-primary btn-file']")).click();
//		WebElement uploadButton = driver.findElement(By.xpath("//*[@id='input-4']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();",uploadButton);
//		((JavascriptExecutor) driver).executeScript("arguments[0].click();",uploadButton);
		Thread.sleep(5000);
		// ControlId in AutoIt is class+instance under Basic control info e.g. Edit1
		// Hard coded file to upload
		// Runtime.getRuntime().exec("./data/FileUpload_Hard.exe");
		// Dynamic file path to be uploaded at runtime
		Runtime.getRuntime().exec("./data/FileUpload.exe" + " " + baseDir + "\\data\\info.pdf");
		Thread.sleep(3000);
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='file-footer-caption']")).getText().contains("info.pdf"),
				true);
	}

//	@Test
	public void cookies() {
		driver.get("http://www.amazon.in");
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println("Cookie size: " + cookies.size());
		for (Cookie cookie : cookies) {
			System.out.println("Name: " + cookie.getName() + " value: " + cookie.getValue());
		}
		Cookie myCookie = new Cookie("TestCookie", "12345678");
		driver.manage().addCookie(myCookie);
		cookies = driver.manage().getCookies();
		System.out.println("*****************************************");
		System.out.println("New Cookie size: " + cookies.size());
		for (Cookie cookie : cookies) {
			System.out.println("Name: " + cookie.getName() + " value: " + cookie.getValue());
		}
		driver.manage().deleteCookie(myCookie);
		cookies = driver.manage().getCookies();
		System.out.println("*****************************************");
		System.out.println("Cookie size post deletion: " + cookies.size());
		for (Cookie cookie : cookies) {
			System.out.println("Name: " + cookie.getName() + " value: " + cookie.getValue());
		}

		driver.manage().deleteAllCookies();
		cookies = driver.manage().getCookies();
		System.out.println("*****************************************");
		System.out.println("Cookie size post after all cookie deletion: " + cookies.size());
		for (Cookie cookie : cookies) {
			System.out.println("Name: " + cookie.getName() + " value: " + cookie.getValue());
		}
	}

//	@Test
	public void scrolling() throws InterruptedException, IOException {
		driver.get("http://www.amazon.in");

		// Scroll using pixel
		((JavascriptExecutor) driver).executeScript("window.scroll(0,1000)", "");
		// Scroll using Actions class
		WebElement conditions = driver.findElement(By.linkText("Conditions of Use & Sale"));
		Actions action = new Actions(driver);
		action.moveToElement(conditions).perform();
		Thread.sleep(2000);
		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile = new File("./data/screenshot.jpg");
		FileUtils.copyFile(srcfile, targetFile);
		Assert.assertEquals(targetFile.exists(), true);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("window.scroll(0,1000)", "");
		// Scroll page till element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", conditions);
		// Scroll to top
		((JavascriptExecutor) driver).executeScript("window.scroll(0,0)", "");
		Thread.sleep(2000);
		// Scroll page till bottom
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		Thread.sleep(5000);
	}



	@Test
	public void BRBN() throws InterruptedException {
		Xls_Reader xls = new Xls_Reader("C:\\MILAN\\Ved\\BRBN (A.C Panal).xlsx");
//		System.out.print("*******regNum*****"+xls.getCellData("Madhya",1,3));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		driver.get("http://dealer.go4online.co.in/KSpanel/index.aspx");
//		driver.findElement(By.id("txtuser")).sendKeys("vedmani811@gmail.com");
		driver.findElement(By.id("txtuser")).sendKeys("mishragovind001@gmail.com");
		Thread.sleep(5000);
//		driver.findElement(By.id("txtPass")).sendKeys("Vm@22M811", Keys.ENTER);
		driver.findElement(By.id("txtPass")).sendKeys("lalshakm", Keys.ENTER);
		Thread.sleep(15000);
//		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Generate Garma Demand (By Farmer)")));
//		driver.findElement(By.linkText("Generate Garma Demand (By Farmer)")).click();
//		driver.navigate().to("http://dealer.go4online.co.in/KSPanel/FarmerDemandApprovedByAC.aspx");
//		List<WebElement> edits = driver.findElements(
//				By.xpath("//td[text()='SANKAR DHAN']/following-sibling::td//input[@src='../images/editicon.jpg']"));
//		System.out.println("List size:: " + edits.size());
		driver.navigate().to("http://dealer.go4online.co.in/KSpanel/newDemand.aspx?Code=0&Mode=I");
		int i = 89;
//		Xls_Reader xls = new Xls_Reader("C:\\MILAN\\Ved\\barheta input 2020 (1).xlsx");
		
		while (i < 92) {
			String regNum = xls.getCellData("Mishra1",1,i).replace(".", "").substring(0,13);
//			String regNum = xls.getCellData("Mishra1",1,i).substring(0,13);
			String farmer = xls.getCellData("Mishra1",2,i);
			System.out.print("*******regNum*****"+regNum+" "+farmer);
			Thread.sleep(20000);
			driver.navigate().to("http://dealer.go4online.co.in/KSpanel/newDemand.aspx?Code=0&Mode=I");
			Thread.sleep(10000);
//			WebElement editBtn = driver.findElement(By.id("ContentPlaceHolder1_GridView1_ImageButton8_" + i));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", editBtn);
//			wait.until(ExpectedConditions.elementToBeClickable(editBtn));
//			String text = driver.findElement(By.xpath(
//					"//input[@id='ContentPlaceHolder1_GridView1_ImageButton8_" + i + "']/../preceding-sibling::td[7]"))
//					.getText();
//			String name = driver.findElement(By.xpath(
//					"//input[@id='ContentPlaceHolder1_GridView1_ImageButton8_" + i + "']/../preceding-sibling::td[13]"))
//					.getText();
//			System.out.print("Name:::: " + name + " Seed: " + text+" "+i+" ");
//			if (text.equalsIgnoreCase("SANKAR DHAN")) {
////				driver.findElement(By.id("ContentPlaceHolder1_GridView1_ImageButton8_" + i)).click();
//				editBtn.click();
			driver.findElement(By.id("ContentPlaceHolder1_txtRegNo")).clear();
			driver.findElement(By.id("ContentPlaceHolder1_txtRegNo")).sendKeys(regNum,Keys.ENTER);
			
			Thread.sleep(25000);
			System.out.println(" "+i+"**Farmer**"+driver.findElement(By.id("ContentPlaceHolder1_txtFarmerName")).getAttribute("Value")+" Panch "+driver.findElement(By.id("ContentPlaceHolder1_txtPanchyat")).getAttribute("value"));
			
			if(driver.findElement(By.id("ContentPlaceHolder1_txtPanchyat")).getAttribute("value").equalsIgnoreCase("OLIPUR SARHACHIYA"))
			{					
//				wait.until(
//						ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_GridView1_DDSchemeType_0")));
//				Select select = new Select(driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDSchemeType_0")));
				Select select = new Select(driver.findElement(By.name("ctl00$ContentPlaceHolder1$GridView1$ctl02$DDSchemeType")));				
//				select.selectByVisibleText("State Scheme");
				select.selectByValue("1");
				Thread.sleep(25000);
				wait.until(ExpectedConditions
						.elementToBeClickable(By.id("ContentPlaceHolder1_GridView1_DDSUBSchemeType_0")));
				Select select1 = new Select(
						driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDSUBSchemeType_0")));
//				select1.selectByVisibleText("Anudanit Dar par Shankar Dhan Beej Vitran");
				select1.selectByVisibleText("Minikit Yojana");				
				Thread.sleep(25000);
				Select select3 = new Select(driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDCrop_0")));
//				if(!(select3.getFirstSelectedOption().getText().equalsIgnoreCase("SANKAR DHAN"))) {
//				select3.selectByVisibleText("SANKAR DHAN");
				select3.selectByVisibleText("Paddy");
				Thread.sleep(25000);
//				}
				wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_GridView1_DDSeed_0")));
//				Select select2 = new Select(driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDSeed_0")));
				Select select2 = new Select(driver.findElement(By.name("ctl00$ContentPlaceHolder1$GridView1$ctl02$DDSeed")));
				
				select2.selectByVisibleText("CS");
				Thread.sleep(25000);
				driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
				driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("6", Keys.TAB);
//				int qty = Integer.parseInt(
//						driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).getAttribute("value"));
//				if (qty > 25) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("12", Keys.TAB);
//				} else if (qty > 15 && qty <= 25) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("9", Keys.TAB);
//				} else if (qty > 12 && qty <= 15) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("6", Keys.TAB);
//				} else if (qty > 0 && qty <= 12) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("3", Keys.TAB);
//				}
				Thread.sleep(25000);
				driver.findElement(By.id("ContentPlaceHolder1_Button1")).click();
			}
//				Thread.sleep(15000);
//				driver.findElement(By.linkText("Generate Garma Demand (By Farmer)")).click();

//			}
//			else if (text.equalsIgnoreCase("Paddy")) {
////				driver.findElement(By.id("ContentPlaceHolder1_GridView1_ImageButton8_" + i)).click();
//				editBtn.click();
//				Thread.sleep(25000);
//				wait.until(
//						ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_GridView1_DDSchemeType_0")));
//				Select select = new Select(driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDSchemeType_0")));
//				select.selectByVisibleText("NFSM");
//				Thread.sleep(15000);
//				wait.until(ExpectedConditions
//						.elementToBeClickable(By.id("ContentPlaceHolder1_GridView1_DDSUBSchemeType_0")));
//				Select select1 = new Select(
//						driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDSUBSchemeType_0")));
//				select1.selectByVisibleText("Less Then 10 Year");
//				Thread.sleep(15000);
//				Select select3 = new Select(driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDCrop_0")));
////				if(!(select3.getFirstSelectedOption().getText().equalsIgnoreCase("SANKAR DHAN"))) {
//				select3.selectByVisibleText("Paddy");
//				Thread.sleep(15000);
////				}
//				wait.until(ExpectedConditions.elementToBeClickable(By.id("ContentPlaceHolder1_GridView1_DDSeed_0")));
//				Select select2 = new Select(driver.findElement(By.id("ContentPlaceHolder1_GridView1_DDSeed_0")));
//				select2.selectByVisibleText("CS");
//				Thread.sleep(15000);
//				int qty = Integer.parseInt(
//						driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).getAttribute("value"));
//				if (qty > 60) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("40", Keys.TAB);
//				} else if (qty > 40 && qty <= 60) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("30", Keys.TAB);
//				} else if (qty > 25 && qty <= 40) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("25", Keys.TAB);
//				} else if (qty > 20 && qty <= 25) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("20", Keys.TAB);
//				}
//				else if (qty > 15 && qty <= 20) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("15", Keys.TAB);
//				}
//				else if (qty > 10 && qty <= 15) {
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).clear();
//					driver.findElement(By.id("ContentPlaceHolder1_GridView1_txtSRate_0")).sendKeys("10", Keys.TAB);
//				}
//				Thread.sleep(15000);
//				driver.findElement(By.id("ContentPlaceHolder1_Button1")).click();
//				
//
//			}
//			Thread.sleep(15000);
			if(driver.findElements(By.id("ContentPlaceHolder1_lblMsg")).size()>0)
				System.out.println(" "+driver.findElement(By.id("ContentPlaceHolder1_lblMsg")).getText());
//			driver.findElement(By.linkText("Generate Garma Demand (By Farmer)")).click();
//			driver.navigate().to("http://dealer.go4online.co.in/KSPanel/FarmerDemandApprovedByAC.aspx");
		i++;
		}
	}

//	@Test
	public void DBTA() throws InterruptedException {
		driver.get("https://dbtagriculture.bihar.gov.in/ACRabiApril.aspx");
//		Thread.sleep(35000);
//		List<WebElement> edits = driver.findElements(By.xpath("//input[contains(@id,\"MainContent_gdviewdiesel_imgbtndiesel_\")]"));
//		for(WebElement edit:edits) {
		List<WebElement> edits = driver.findElements(By.id("MainContent_gdviewdiesel_imgbtndiesel_0"));
		int i=0;
		WebElement edit;
//		while(i<edits.size()) {
		while(edits.size()>0) {
			driver.findElement(By.id("MainContent_gdviewdiesel")).click();
//			edit = driver.findElement(By.id("MainContent_gdviewdiesel_imgbtndiesel_"+i));
			edit = driver.findElement(By.id("MainContent_gdviewdiesel_imgbtndiesel_0"));
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", edit);
			edit.click();
			Thread.sleep(5000);
			Select select = new Select(driver.findElement(By.id("MainContent_ddlstatus")));
			select.selectByValue("2");
			Thread.sleep(5000);
			Select select1 = new Select(driver.findElement(By.id("MainContent_ddlrejectcause")));
			select1.selectByValue("4");
			Thread.sleep(5000);
			driver.findElement(By.id("MainContent_txtremarks")).clear();
			driver.findElement(By.id("MainContent_txtremarks")).sendKeys("Panchayat not under scheme.");
			driver.findElement(By.id("MainContent_btnsave")).click();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			Thread.sleep(5000);
			i++;
		}
	}
	
//	@Test
	public void testListener() throws SQLException {
		Assert.assertTrue(false);
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521/pdborcl","hr","hr");
			Statement select = con.createStatement();
			ResultSet rs = select.executeQuery("select *from table");
			while(rs.next()) {
				System.out.println(rs.toString());
				System.out.println(rs.getString("uname"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			con.close();
		}
	}

//	@Test
	public void highlight() throws InterruptedException {
		test = extent.createTest("Elements highlight");
		driver.get("http://www.amazon.com");
		test.pass("Navigated to Amazon");
		WebElement searchBox = driver.findElement(By.name("q"));
		JavaScriptUtil.highlightElement(driver,searchBox);
		JavaScriptUtil.drawBorder(driver,searchBox);
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', 'background: green; border: 2px solid red;');", searchBox);
		Thread.sleep(5000);
		driver.getTitle();
	}
	
//	@AfterMethod
	public void testResults(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Case Failed is "+ result.getName());
			test.log(Status.FAIL, "Test Case Failed is "+ result.getThrowable());
			
			String screenshotPath = getScreenshot(driver, result.getName()).toString();
			System.out.println("PATH:"+screenshotPath);
			test.fail("details", 
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			test.fail("details").addScreenCaptureFromPath(screenshotPath);
		}else if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test passed is " +result.getName());
		}else if (result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test skipped is " +result.getName());
		}
	}
}