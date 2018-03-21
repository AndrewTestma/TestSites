package com.utils;

import com.controller.ExecuteController;
import com.pojo.Autosteps;
import com.pojo.User;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:测试父类
 * @Date 2018/2/28 0028
 */
public class TestBaseCase {
    public String reportLocation;
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    public WebDriver driver;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ElementAction elementAction=null;
    public Assertion assertion=null;
    /*public static int tstotaltime;//执行时长
    public static int tstotalsteps=0;//总步数
    public static int tsrunsteps=0;//执行步数
    public long startTime;//开始时间e
    public long endTime;//结束时间*/
    /** 
    * @Description: 获取当前路径，并截取
    * @Date: 19:20 2018年03月15日
     */ 
    public String getLocalPath(){
        String path =  this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0,path.indexOf("/WEB-INF"));
        return path;
    }
    @BeforeSuite
    public void initializationExtentReport(){
        reportLocation=getLocalPath()+"/resources/result/"+ExecuteController.business.getTsbusinessid()+".html";
        extentReports=new ExtentReports(reportLocation,true, NetworkMode.OFFLINE,Locale.SIMPLIFIED_CHINESE);
        extentReports.addSystemInfo("Host Name", "Andrew");
        ExtentReportMap.map.put(Thread.currentThread().getName(),extentReports);
    }
    /**
     * @Description:测试执行前操作
     * */
    @BeforeTest
    public void startSetUp(){
        logger.info(Thread.currentThread().getName());
        logger.info("---打开浏览器---");
        /*startTime=System.currentTimeMillis();*/
        String driverType=ExecuteController.env.getTsdriver();
        String driverPath=ExecuteController.env.getTsdirverpath();
        String driverName=ExecuteController.env.getTsname();
        if(driverName.equals("本地")){
            driver=DriverManager.setLocalDriver(driverType,driverPath);
        }else {
            driver=DriverManager.setRemoteDriver(driverType,driverPath);
        }
        /*autosteps= ExecuteController.listMap;*/

     /*   ExtentReportMap.autosteps.put(user.getTsuserid(),ExecuteController.listMap);*/
        String url=ExecuteController.pro.getTsurl();
        driver.navigate().to(url);
        driver.manage().window().maximize();
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
        extentReports.flush();
    }
    /**
     * @Description:测试完成后操作
     * */
    @AfterTest
    public void endSetUp(){
        driver.close();
        driver.quit();
        /*endTime=System.currentTimeMillis();
        tstotaltime=(int)((endTime-startTime)/1000);*/
        logger.info("关闭退出浏览器");
        logger.info("---结束测试---");
    }
    @AfterSuite
    public void closeExtentReport(){
        extentReports.endTest(extentTest);
        extentReports.close();
    }

    @Test()
    public void test(){
        logger.info("执行测试步骤");
        /*String ip=null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            logger.info(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Map<String,List<Autosteps>> autosteps=null;
        if(ExtentReportMap.autosteps.get(ip)!=null){
            autosteps=ExtentReportMap.autosteps.get(ip);
        }*/
        if(ExtentReportMap.autosteps.get(Thread.currentThread().getName())!=null){
            for(Map.Entry<String,List<Autosteps>> entry:ExtentReportMap.autosteps.get(Thread.currentThread().getName()).entrySet()){
                extentTest=extentReports.startTest(entry.getKey());
                elementAction=new ElementAction(driver);
                assertion=new Assertion(driver,extentReports,extentTest);
                for(Autosteps autosteps1:entry.getValue()){
                    if(autosteps1.getTsactiontype().equals("单击")){
                        elementAction.click(autosteps1);
                    }else if(autosteps1.getTsactiontype().equals("输入")){
                        elementAction.sendKey(autosteps1,autosteps1.getTsactioncontent());
                    }
                    assertion.verityType(autosteps1);
                }
                assertion.writeReport();
                extentReports.flush();
                extentReports.endTest(extentTest);
                if(!assertion.t){
                    ExtentReportMap.autosteps.remove(Thread.currentThread().getName());
                    break;
                }
            }
            ExtentReportMap.autosteps.remove(Thread.currentThread().getName());
        }
    }
}
