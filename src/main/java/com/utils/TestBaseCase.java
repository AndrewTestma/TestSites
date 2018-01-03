package com.utils;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/26 0026
 */
public class TestBaseCase {
    public static WebDriver driver;
    public Logger logger= LoggerFactory.getLogger(this.getClass());

    @BeforeTest
    public void setup(){
        logger.info("开始测试");
        driver=DriverManager.setDriver("ChromeDriver");
        driver.navigate().to("http://www.baidu.com");
    }
    @Test
    public void test(){

    }
    @AfterClass
    public void endTest(){
        driver.close();
        driver.quit();
        logger.info("关闭退出浏览器");
    }
}
