package com.utils;

import com.pojo.Autosteps;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: TestSites
 * @description: 验证类
 * @author: Mr.Andrew
 * @create: 2018-03-05 14:03
 **/
public class Assertion  extends TestBaseCase {
    private static Logger logger= LoggerFactory.getLogger(Assertion.class);
    public static ExtentTest verification=extentReports.startTest("验证点");
    public static ExtentTest  parameter=extentReports.startTest("参数");
    public static ExtentTest screenshot;
    public static String screenShotPath;
    public static int i=0;//控制写入报告次数
    public static ElementAction action=new ElementAction();
    public static String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date).toString();
    }
    public static void snapshotInfo(){
        ScreenShot screenShot=new ScreenShot(driver);
        Date nowDate=new Date();
        screenShot.setscreenName(Assertion.formatDate(nowDate));
        screenShotPath=screenShot.takeScreenshot();
    }
    public static void verityType(Autosteps autosteps){
        if(!autosteps.getTsverificationtype().equals("")){
            switch(autosteps.getTsverificationtype()){
                case "数据库验证":
                    break;
                case  "url验证":
                    verityAssertLocation(autosteps.getTsverificationcontent(),true);
                    break;
                default :
                    verityAssertText(autosteps.getTsverificationcontent(),true);
                    break;
            }
        }
    }
    /** 
    * @Description:判断当前是在正确的页面
    * @Param:[url]:页面URL,[flag]:条件判断
    * @Date: 14:06 2018年03月05日
     */
    public static void verityAssertLocation(String url,Boolean flag){
        String verityStr="页面是否跳转至:"+url+"地址";
        logger.info(verityStr);
        Boolean f;
        try{
            f=url.equals(driver.getCurrentUrl());
        }catch(Exception e){
            f=false;
        }
        try{
            Assert.assertTrue(f);
            AssertPassLog(url,flag);
        }catch(Error e){
            AssertFailedLog(url,flag);
        }
    }
    /**
    * @Description:验证当前页面文字
    * @Param:[text]:验证的文本,[flag]:条件判断
    * @Date: 14:34 2018年03月05日
     */
    public static void verityAssertText(String text,Boolean flag){
        String verityStr="页面是否存在当前:"+text;
        logger.info(verityStr);
        Boolean f;
        try{
            text="//*[text()=\""+text+"\"]";
            driver.findElements(By.xpath(text));
            f=true;
        }catch(Exception e){
            f=false;
        }
        try{
            Assert.assertTrue(f);
            AssertPassLog(text,flag);
        }catch(Error e){
            AssertFailedLog(text,flag);
        }
    }
    /**
    * @Description: 验证成功
    * @Date: 14:29 2018年03月05日
     */
    public static void AssertPassLog(String verityStr,Boolean parameterStr){
        logger.info("验证成功");
        writeExtentReport(verityStr,parameterStr,"PASS");
    }
    /**
    * @Description:验证失败
    * @Date: 14:30 2018年03月05日
     */
    public static void AssertFailedLog(String verityStr,Boolean parameterStr){
        logger.info("验证失败");
        writeExtentReport(verityStr,parameterStr,"FAILED");
        snapshotInfo();
    }
    /**
     * @将验证结果及测试数据写入测试报告中
     * @param status 验证状态
     * */
    public static void writeExtentReport(String verityStr,Boolean parameterStr,String status){
        parameter.log(LogStatus.INFO,"正常建站数据");
        if(status.equals("PASS")){
            verification.log(LogStatus.PASS,verityStr,"PASS");
        }else{
            verification.log(LogStatus.FAIL,verityStr,"FAILED");
            screenshot=extentReports.startTest("截图");
            screenshot.log(LogStatus.FAIL,screenshot.addBase64ScreenShot(screenShotPath));
        }
        if(i==0){
            extentTest.appendChild(parameter);
            extentTest.appendChild(verification);
            if(screenshot!=null){
                extentTest.appendChild(screenshot);
            }
            extentReports.flush();
            i++;
        }
    }
}
