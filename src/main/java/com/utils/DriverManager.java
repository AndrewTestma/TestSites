package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author:Andrew
 * @Description:driver管理
 * @Date 2017/12/26 0026
 */
public class DriverManager {

    public static WebDriver driver=null;
    /**
     * @Description:本地调用浏览器方法
     * @param:[string,driverPath]:浏览器类型,本地地址
     * @return:dirver
     * @date:2018/3/20 11:39
     */
    public static WebDriver setLocalDriver(String string,String driverPath)
    {
        switch (string)
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
                driver=new FirefoxDriver(firefoxProfile);
                break;
            case "ChromeDriver":
                System.setProperty("webdriver.chrome.driver", driverPath);
				/*ChromeOptions options = new ChromeOptions();
				options.setBinary(driverPath);*/
                driver=new ChromeDriver();
                break;
            case "InternetExplorerDriver":
               /* System.setProperty("webdriver.ie.driver",operatingEnv.getTsdirverpath());
				DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				dc.setCapability("ignoreProtectedModeSettings", true);
                driver=new InternetExplorerDriver();*/
                break;
            case "HtmlUnitDriver":
                driver=new HtmlUnitDriver();
                break;
            default:
                driver=new FirefoxDriver();
                break;
        }
        return driver;
    }
    /**
     * @Description:远程调用浏览器方式
     * @param:[string,driverPath]:浏览器类型,远程地址
     * @return:driver
     * @date:2018/3/20 11:38
     */
    public static WebDriver setRemoteDriver(String string,String driverPath)
    {
        if (string.equals("ChromeDriver")) {
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            try {
                driver =new EventFiringWebDriver(new RemoteWebDriver(new URL(driverPath), dc));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (string.equals("ie")) {
            DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
            try {
                driver =new EventFiringWebDriver(new RemoteWebDriver(new URL(driverPath), dc));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }
}
