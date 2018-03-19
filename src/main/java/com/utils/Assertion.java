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
    public static ExtentTest verification=null;
    public static ExtentTest  parameter=null;
    public static ExtentTest screenshot=null;
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
    /*
     * @Description：初始化子报表
     * @date 2018/3/19 13:08
     */
   /* public static void initialization(){
        if(verification!=null && parameter!=null){
            extentReports.endTest(verification);
            extentReports.endTest(parameter);
        }
    }*/
    /*
     * @Description:判断验证方式
     * @param:[autosteps]:实体对象
     * @date 2018/3/19 11:02
     */
    public static void verityType(Autosteps autosteps){
        if(!autosteps.getTsverificationtype().equals("")){
           if(verification==null&parameter==null){
               verification=extentReports.startTest("验证点");
               parameter=extentReports.startTest("参数");
           }
            switch(autosteps.getTsverificationtype()){
                case "数据库验证":
                    break;
                case  "url验证":
                    verityAssertLocation(autosteps,autosteps.getTsverificationcontent(),true);
                    break;
                case "文本验证":
                    verityAssertText(autosteps,autosteps.getTsverificationcontent(),true);
                    break;
                default :
                    logger.info("操作不需要验证");
                    break;
            }
        }
    }
    /** 
    * @Description:判断当前是在正确的页面
    * @Param:[url]:页面URL,[flag]:条件判断
    * @Date: 14:06 2018年03月05日
     */
    public static void verityAssertLocation(Autosteps autosteps,String url,Boolean flag){
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
            AssertPassLog(autosteps,url,flag);
        }catch(Error e){
            AssertFailedLog(autosteps,url,flag);
        }
    }
    /**
    * @Description:验证当前页面文字
    * @Param:[text]:验证的文本,[flag]:条件判断
    * @Date: 14:34 2018年03月05日
     */
    public static void verityAssertText(Autosteps autosteps,String text,Boolean flag){
        String verityStr="页面是否存在当前:"+text;
        logger.info(verityStr);
        StringBuffer stringBuffer=new StringBuffer();
        Boolean f;
        try{
            stringBuffer.append("//*[text()=\"");
            stringBuffer.append(text);
            stringBuffer.append("\"]");
            driver.findElements(By.xpath(stringBuffer.toString()));
            f=true;
        }catch(Exception e){
            f=false;
        }
        try{
            Assert.assertTrue(f);
            AssertPassLog(autosteps,text,flag);
        }catch(Error e){
            AssertFailedLog(autosteps,text,flag);
        }
    }
    /**
    * @Description: 验证成功
    * @Date: 14:29 2018年03月05日
     */
    public static void AssertPassLog(Autosteps autosteps,String verityStr,Boolean parameterStr){
        logger.info("验证成功");
        writeExtentReport(autosteps,verityStr,parameterStr,"PASS");
    }
    /**
    * @Description:验证失败
    * @Date: 14:30 2018年03月05日
     */
    public static void AssertFailedLog(Autosteps autosteps,String verityStr,Boolean parameterStr){
        logger.info("验证失败");
        writeExtentReport(autosteps,verityStr,parameterStr,"FAILED");
        snapshotInfo();
    }
    /**
     * @将验证结果及测试数据写入测试报告中
     * @param status 验证状态
     * */
    public static void writeExtentReport(Autosteps autosteps,String verityStr,Boolean parameterStr,String status){
       try{
           parameter.log(LogStatus.INFO,autosteps.getTsactioncontent());
           if(status.equals("PASS")){
               verification.log(LogStatus.PASS,autosteps.getTsverificationcontent(),"PASS");
           }else{
               verification.log(LogStatus.FAIL,autosteps.getTsverificationcontent(),"FAILED");
               screenshot=extentReports.startTest("截图");
               screenshot.log(LogStatus.FAIL,screenshot.addBase64ScreenShot(screenShotPath));
           }
           extentReports.flush();
          /* if(i==0){
               extentTest.appendChild(parameter);
               extentTest.appendChild(verification);
               if(screenshot!=null){
                   extentTest.appendChild(screenshot);
               }
               i++;
           }*/
       }catch (Error e){
           logger.info("验证结果写入测试报告出错");
       }
    }
    /*
     * @Description:写入子表测试报告
     * @date 2018/3/19 13:26
     */
    public static void writeReport(){
        try{
            extentTest.appendChild(parameter);
            extentTest.appendChild(verification);
            if(screenshot!=null){
                extentTest.appendChild(screenshot);
            }

        }catch(Exception e){
            logger.info("添加子表出错");
        }finally {
            extentReports.endTest(parameter);
            extentReports.endTest(verification);
            parameter=null;
            verification=null;
        }
    }
}
