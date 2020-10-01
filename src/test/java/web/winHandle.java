package web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class winHandle extends BaseClass {
//    WebDriver driver;


    @Test
    @Parameters("panchayat")
    public void test(String panchayat) throws InterruptedException, IOException
    {
        driver.get("http://krishijal.bih.nic.in/");
//        Properties prop = new Properties();
//        InputStream in = getClass().getResourceAsStream("framework.properties");
//        prop.load(in);
//        prop.setProperty("newkey", "newvalue");
//        prop.store(new FileOutputStream("xyz.properties"), null);
        Wait explWait = new WebDriverWait(driver, 600);
        driver.findElement(By.id("ctl00_MainContent_txtUserId")).sendKeys("BR101152");
        driver.findElement(By.id("ctl00_MainContent_txtPassword")).sendKeys("1234", Keys.ENTER);
//        driver.findElement(By.id("ctl00_MainContent_txtMobileCDPO")).sendKeys("8309964896");
//        driver.findElement(By.id("ctl00_MainContent_btnSendOTPCDPO")).click();
//        driver.switchTo().alert().accept();
//        driver.findElement(By.id("ctl00_MainContent_btnHome")).click();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[@class=\"ctl00_LoginView14_MenuMaster_1 MenuDefaultMenuItemStyle ctl00_LoginView14_MenuMaster_3\" and text()=\"Action\"]"))).perform();
        driver.findElement(By.cssSelector(".ctl00_LoginView14_MenuMaster_1.MenuDefaultMenuItemStyle.MenuDynamicMenuItemStyle.ctl00_LoginView14_MenuMaster_5")).click();

        Select mauja = new Select(driver.findElement(By.id("ctl00_MainContent_ddlMuja")));
        if(panchayat.equalsIgnoreCase("runi"))
            mauja.selectByValue("218");
        else if(panchayat.equalsIgnoreCase("basatpur"))
            mauja.selectByValue("219");
        else if(panchayat.equalsIgnoreCase("majholi"))
            mauja.selectByValue("220");

        int sinchit = 0;
        boolean execute = false;
        boolean sinch = false;
        boolean asinch = false;
//        driver.findElement(By.linkText("10")).click();
        Thread.sleep(5000);
        List<WebElement> listEl = driver.findElements(By.xpath("//a[contains(@id,\"_lnkSelect\")]"));
        int i = 11;
        String id="";
        String sNum = "";
        for (int j = 2; j <= listEl.size()+1; j++)
//        for(WebElement e: listEl)
//        while(driver.findElements(By.xpath("//a[contains(@id,\"_lnkSelect\")]")).size()>1)
        {
            if (j>=10) {
                id = driver.findElement(By.id("ctl00_MainContent_grdOwner_ctl"+j+"_lnkID")).getText();
                sNum = driver.findElement(By.xpath("//a[@id=\"ctl00_MainContent_grdOwner_ctl"+j+"_lnkSelect\"]/../following-sibling::td[1]")).getText();
                driver.findElement(By.id("ctl00_MainContent_grdOwner_ctl" + j + "_lnkSelect")).click();
            }
            else {
                id = driver.findElement(By.id("ctl00_MainContent_grdOwner_ctl0"+j+"_lnkID")).getText();
                sNum = driver.findElement(By.xpath("//a[@id=\"ctl00_MainContent_grdOwner_ctl0"+j+"_lnkSelect\"]/../following-sibling::td[1]")).getText();
                driver.findElement(By.id("ctl00_MainContent_grdOwner_ctl0" + j + "_lnkSelect")).click();
            }
            explWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_MainContent_rbtnotsurvey"))));
            System.out.println("ITR: " + j + " ID:: " + id+" sNum:: "+sNum);
            Thread.sleep(5000);
            String acre = "";
            String desimal = "";
//            Select plot = new Select(driver.findElement(By.id("ctl00_MainContent_ddlPlotNumber")));
//            int plotListSize = plot.getOptions().size();
//            for(int z=1;z<=plotListSize;z++) {
//                plot.selectByIndex(z);
//                Thread.sleep(5000);
//            for(int z=1;z<=plotListSize;z++) {
                if (driver.findElement(By.id("ctl00_MainContent_rbtnotsurvey")).isSelected()) {
//                acre = driver.findElement(By.id("ctl00_MainContent_txtSinAcure")).getAttribute("value");
//                desimal = driver.findElement(By.id("ctl00_MainContent_txtSinDesimal")).getAttribute("value");
                    driver.findElement(By.id("ctl00_MainContent_lnkBack")).click();
                    Thread.sleep(5000);
                    continue;
                }
                if (driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).isSelected()) {
                    driver.findElement(By.id("ctl00_MainContent_rbtAccp1No")).click();
                    driver.findElement(By.id("ctl00_MainContent_rbtOtherNeedYes")).click();
                    driver.findElement(By.id("ctl00_MainContent_btnSubmit")).click();
                    driver.switchTo().alert().accept();
                    if(j==21){
                        j=1;
                        i++;
                        driver.findElement(By.linkText(String.valueOf(i))).click();
                    }
                    continue;
                }
                acre = driver.findElement(By.id("ctl00_MainContent_txtSinAcure")).getAttribute("value");
                desimal = driver.findElement(By.id("ctl00_MainContent_txtSinDesimal")).getAttribute("value");
                double total = Double.valueOf(acre + desimal);
                double dist = total / 100;
//            String ownerName = driver.findElement(By.id("ctl00_MainContent_lblOwnerName"));
//            System.out.println("OwnerName:: "+ownerName+" SinchitCount:: "+sinchit+" Total:: "+total);

                //            if(driver.findElement(By.id("ctl00_MainContent_rbtnotsurvey")).isSelected() && !ownerName.equalsIgnoreCase("NA"))
//            else if(driver.findElement(By.id("ctl00_MainContent_rbtnotsurvey")).isSelected() && total <= 0){
//                driver.findElement(By.id("ctl00_MainContent_rbtnotsurvey")).click();
//                if(!driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).isSelected())
//                    driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).click();
//                execute = true;
//            }
                if (driver.findElement(By.id("ctl00_MainContent_rbtSinchit")).isSelected() && sinchit <= 292 && total >= 5) {
//                driver.findElement(By.id("ctl00_MainContent_rbtSinchit")).click();
                    sinchit++;
                    execute = true;
                    sinch = true;
                    Thread.sleep(2000);
                    driver.findElement(By.id("ctl00_MainContent_rbtAccp1Yes")).click();
                    driver.findElement(By.id("ctl00_MainContent_rbtOtherNo")).click();
                    driver.findElement(By.id("ctl00_MainContent_rbtAccp1No")).click();
                }
                else if (driver.findElement(By.id("ctl00_MainContent_rbtSinchit")).isSelected() && sinchit <= 292 && total < 5) {
                    driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).click();
                    Thread.sleep(5000);
                    if (!driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).isSelected()) {
                        driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).click();
                        explWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_MainContent_txtASinchAcure"))));
                    }
                    execute = true;
                    sinch = false;
                }
//            else if (driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).isSelected()) {
////                driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).click();
////                explWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_MainContent_txtASinchAcure"))));
//                execute = true;
//                asinch = false;
//            }
                else if (!driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).isSelected()) {
                    driver.findElement(By.id("ctl00_MainContent_rbtASinchit")).click();
                    explWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ctl00_MainContent_txtASinchAcure"))));
                    execute = true;
                    sinch = false;
                }
                if (execute && !sinch) {
                    driver.findElement(By.id("ctl00_MainContent_txtASinchAcure")).sendKeys(acre, Keys.TAB);
                    Thread.sleep(5000);
                    driver.findElement(By.id("ctl00_MainContent_txtASinchitDesimal")).sendKeys(desimal, Keys.TAB);
                    Thread.sleep(5000);
                    Select surfaceWater = new Select(driver.findElement(By.id("ctl00_MainContent_ddlSurfaceIrigationForKisan")));
                    surfaceWater.selectByValue("14");
                    Select borwel = new Select(driver.findElement(By.id("ctl00_MainContent_ddlBorvelForKisan")));
                    borwel.selectByValue("3");
                    Select distFT = new Select(driver.findElement(By.id("ctl00_MainContent_ddlCommandarea2")));
                    if (total >= 100)
                        distFT.selectByValue("1");
                    else if (total >= 50 && total < 100)
                        distFT.selectByValue("2");
                    else if (total >= 20 && total < 50)
                        distFT.selectByValue("3");
                    else
                        distFT.selectByValue("4");
                    Select achadit = new Select(driver.findElement(By.id("ctl00_MainContent_ddlExesesStoping2")));
                    if (dist >= 0 && dist <= .5)
                        achadit.selectByValue("1");
                    else if (dist >= .5 && dist <= 1.0)
                        achadit.selectByValue("2");
                    else if (dist >= 1.0 && dist <= 2.0)
                        achadit.selectByValue("3");
                    else if (dist >= 2.0 && dist <= 3.0)
                        achadit.selectByValue("4");
                    else if (dist >= 3.0 && 4.0 <= dist)
                        achadit.selectByValue("5");
                    else if (dist >= 4.0)
                        achadit.selectByValue("6");

                    driver.findElement(By.id("ctl00_MainContent_rbtAccp1No")).click();
                    driver.findElement(By.id("ctl00_MainContent_rbtOtherNo")).click();
                    driver.findElement(By.id("ctl00_MainContent_rbtOtherNeedYes")).click();
                }
            System.out.println(" SinchitCount:: " + sinchit + " Total:: " + total);
            driver.findElement(By.id("ctl00_MainContent_btnSubmit")).click();
            driver.switchTo().alert().accept();
            Thread.sleep(5000);
            if(j==21){
                j=2;
                i++;
                driver.findElement(By.linkText(String.valueOf(i))).click();
            }

            }
            }

//	@Test
//    @Parameters("url")
//    public void test(String url){
//        driver.get(url);
//        String parentHandle = driver.getWindowHandle();
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(driver.getTitle(),"Welcome to HDFC Bank NetBanking");
////        System.out.println(driver.getTitle());
//        driver.switchTo().frame("footer");
//        driver.findElement(By.xpath("//a[text()=\"Terms and Conditions\"]")).click();
//        WebDriverWait wait  = new WebDriverWait(driver,20);
//        wait.until(ExpectedConditions.titleContains("HDFC"));
//        Set<String> winHandles = driver.getWindowHandles();
//
//        for(String windows:winHandles){
//            System.out.println(windows);
//            System.out.println(driver.getTitle());
//            if(!windows.equalsIgnoreCase(parentHandle)){
//                driver.switchTo().window(windows);
//                System.out.println(driver.getTitle());
//                Assert.assertTrue(driver.getTitle().contains("HDFC Bank"),"Terms Pop UP");
//            }
//        }
//        driver.close();
//        driver.switchTo().window(parentHandle);
//        System.out.println(driver.getTitle());
//        softAssert.assertAll();
////        Assert.assertEquals(driver.getTitle(),"Google");
//    }
}