package com.utils;

import com.controller.ExecuteController;
import com.pojo.Autosteps;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

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
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public final  String  reportLocation=getLocalPath()+"/resources/result/ExtentReport.html";
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    public static Map<String,List<Autosteps>> autosteps=new HashMap<>();
    public static int tstotaltime;//执行时长
    public static int tstotalsteps=0;//总步数
    public static int tsrunsteps=0;//执行步数
    public long startTime;//开始时间
    public long endTime;//结束时间
    /** 
    * @Description: 获取当前路径，并截取 
    * @Param:  
    * @return: 
    * @Date: 19:20 2018年03月15日
     */ 
    public String getLocalPath(){
        String path =  this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0,path.indexOf("/WEB-INF"));
        return path;
    }

    @BeforeSuite
    public void initializationExtentReport(){
        extentReports=new ExtentReports(reportLocation,true, NetworkMode.OFFLINE);
        extentReports.addSystemInfo("Host Name", "Andrew");
    }

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
    @AfterMethod
    public void ExtentResult(ITestResult result){
        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL,extentTest.addBase64ScreenShot(Assertion.screenShotPath),result.getThrowable());
        }else if(result.getStatus()==ITestResult.SKIP){
            extentTest.log(LogStatus.SKIP,result.getThrowable());
        }else{
            extentTest.log(LogStatus.PASS,"成功");
        }
        extentReports.endTest(extentTest);
        extentReports.flush();
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
    @AfterSuite
    public void closeExtentReport(){
        extentReports.flush();
        extentReports.close();
    }
    public static ExtentReports getextent() {
        return extentReports;
    }


}
