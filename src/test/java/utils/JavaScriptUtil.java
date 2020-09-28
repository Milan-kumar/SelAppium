package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	public static void highlightElement(WebDriver driver, WebElement element) throws InterruptedException {		
		for(int i=0;i<5;i++) {
			((JavascriptExecutor)driver).executeScript("arguments[0].style.backgroundColor='Yellow'", element);
			Thread.sleep(20);
			((JavascriptExecutor)driver).executeScript("arguments[0].style.backgroundColor='transparent'", element);
		}		
	}
	
	public static void drawBorder(WebDriver driver, WebElement element) {
//		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', 'background: green; border: 2px solid red;');", element);
	}

}
