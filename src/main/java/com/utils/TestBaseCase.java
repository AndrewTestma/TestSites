package com.utils;

import com.controller.ExecuteController;
import com.pojo.Autosteps;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:测试父类
 * @Date 2018/2/28 0028
 */
public class TestBaseCase {
    public static WebDriver driver;
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    public static Map<String,List<Autosteps>> autosteps=new HashMap<>();
    /**
     * @Description:测试执行前操作
     * */
    @BeforeTest
    public void startSetUp(){
        logger.info("---开始测试---");
        String drivertype=ExecuteController.env.getTsdriver();
        driver=DriverManager.setDriver(drivertype);
        autosteps= ExecuteController.listMap;
        String url=ExecuteController.pro.getTsurl();
        driver.navigate().to(url);
    }
    /**
     * @Description:测试完成后操作
     * */
    @AfterTest
    public void endSetUp(){
        driver.close();
        driver.quit();
        logger.info("关闭退出浏览器");
        logger.info("---结束测试---");
    }
}
