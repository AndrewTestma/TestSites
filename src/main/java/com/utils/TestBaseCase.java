package com.utils;

import com.controller.ExecuteController;
import com.pojo.Autosteps;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static int tstotaltime;//执行时长
    public static int tstotalsteps=0;//总步数
    public static int tsrunsteps=0;//执行步数
    public long startTime;//开始时间
    public long endTime;//结束时间
    /**
     * @Description:测试执行前操作
     * */
    @BeforeTest
    public void startSetUp(){
        logger.info("---开始测试---");
        startTime=System.currentTimeMillis();
        String driverType=ExecuteController.env.getTsdriver();
        String driverPath=ExecuteController.env.getTsdirverpath();
        driver=DriverManager.setDriver(driverType,driverPath);
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
        endTime=System.currentTimeMillis();
        tstotaltime=(int)((endTime-startTime)/1000);
        logger.info("关闭退出浏览器");
        logger.info("---结束测试---");
    }
}
