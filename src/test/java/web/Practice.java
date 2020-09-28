package web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import utils.JavaUtil;

public class Practice extends BaseClass {

	public static void main(String s[]) {
		System.out.println(JavaUtil.encryptString("Password"));
		System.out.println(JavaUtil.decryptString(JavaUtil.encryptString("Password")));
		System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		System.out.println("Title::"+driver.getTitle());
		driver.findElement(By.id("")).isDisplayed();
		driver.findElement(By.id("")).isEnabled();
		Select select = new Select(driver.findElement(By.id("")));
		select.getFirstSelectedOption();
		List<WebElement> ops = select.getAllSelectedOptions();
		List<String> str = new ArrayList();
		for(WebElement op:ops) {
			str.add(op.getText());
		}
//		ops.contains(o);
		Collections.sort(str);
		driver.quit();
	}
}
