package com.utils;

import com.controller.UITestCaseController;
import com.pojo.Autosteps;
import com.pojo.UITestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/26 0026
 */
public class BaseTest {
    public static WebDriver driver=null;
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    public static Map<String,List<Autosteps>> autosteps=new HashMap<>();
    public static UITestCase uiTestCase=null;
    @BeforeTest
    public void setup(){
        logger.info("开始测试");
        autosteps= UITestCaseController.list;
        driver=setDriver("ChromeDriver");
        driver.navigate().to("http://www.baidu.com");
    }
    @Test
    public void test(){
        WebElement webElement=null;
        logger.info("执行测试步骤");
        for(Map.Entry<String,List<Autosteps>> entry:autosteps.entrySet()){
                for(Autosteps autosteps1:entry.getValue()){
                    if(autosteps1.getTssearchid().equals("by.id")){
                        webElement=driver.findElement(By.id(autosteps1.getTssearchcontent()));
                    }
                    if(autosteps1.getTsexecutionid().equals("单击")){
                        webElement.click();
                    }else if(autosteps1.getTsexecutionid().equals("输入")){
                        webElement.sendKeys(autosteps1.getTsexecutioncontent());
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
        logger.info("执行验证");
        if(uiTestCase.getTsverificationid().equals("")){

        }
    }
    @AfterClass
    public void endTest(){
        driver.close();
        driver.quit();
        logger.info("关闭退出浏览器");
    }

    private WebDriver setDriver(String browsername)
    {

        switch (browsername)
        {
            case "FirefoxDriver" :
                System.setProperty("webdriver.firefox.bin", "");
                FirefoxProfile firefoxProfile=new FirefoxProfile();
                //设置默认下载路径
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.dir", "D:\\自动化测试下载文件");
                //加载firebug插件
                firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0.13");
                firefoxProfile.setPreference("extensions.firebug.allPagesActivation", "none");
                //加载firepath插件
                firefoxProfile.setPreference("extensions.firepath.currentVersion", "0.9.7.1.1");
                firefoxProfile.setPreference("extensions.firepath.allPagesActivation", "on");
                this.driver=new FirefoxDriver(firefoxProfile);
                break;
            case "ChromeDriver":
                System.setProperty("webdriver.chrome.driver", "driver/Chromedriver.exe");
				/*ChromeOptions options = new ChromeOptions();
				options.setBinary(this.driver_path); */
                this.driver=new ChromeDriver();
                break;
            case "InternetExplorerDriver":
                System.setProperty("webdriver.ie.driver","" );
				/*DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setCapability("ignoreProtectedModeSettings", true);*/
                this.driver=new InternetExplorerDriver();
                break;
            case "HtmlUnitDriver":
                this.driver=new HtmlUnitDriver();
                break;
            default:
                this.driver=new FirefoxDriver();
                break;
        }
        return driver;
    }

}
