package com.utils;

import com.pojo.Autosteps;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: TestSites
 * @description: 验证类
 * @author: Mr.Andrew
 * @create: 2018-03-05 14:03
 **/
public class Assertion{
    private Logger logger= LoggerFactory.getLogger(Assertion.class);
    public WebDriver driver;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public ExtentTest verification=null;
    public ExtentTest  parameter=null;
    public ExtentTest screenshot=null;
    public Boolean t;
    public static String screenShotPath;//绝对路径，图片存放地址
    public static int i=0;//控制写入报告次数
    public Assertion(WebDriver driver, ExtentReports extentReports,ExtentTest extentTest){
        this.driver=driver;
        this.extentReports=extentReports;
        this.extentTest=extentTest;
    }
    public static String formatDate(Date date)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
        return formatter.format(date).toString();
    }
    public  void snapshotInfo(){
        ScreenShot screenShot=new ScreenShot(driver);
        Date nowDate=new Date();
        screenShot.setscreenName(Assertion.formatDate(nowDate));
        screenShotPath=screenShot.takeScreenshot();
    }
    /**
     * @Description:判断验证方式
     * @param:[autosteps]:实体对象
     * @date 2018/3/19 11:02
     */
    public  void verityType(Autosteps autosteps){
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
                    t=true;
                    break;
            }
        }
    }
    /** 
    * @Description:判断当前是在正确的页面
    * @Param:[url]:页面URL,[flag]:条件判断
    * @Date: 14:06 2018年03月05日
     */
    public  void verityAssertLocation(Autosteps autosteps,String url,Boolean flag){

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
    public  void verityAssertText(Autosteps autosteps,String text,Boolean flag){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String verityStr="页面是否存在当前:"+text;
        logger.info(verityStr);
        StringBuffer stringBuffer=new StringBuffer();
        Boolean f;
        stringBuffer.append("//*[text()='"+text+"']");
        if(autosteps.getTsverficationframe()!=null && !autosteps.getTsverficationframe().equals("")){
            String[] array = autosteps.getTsverficationframe().split("/");
            int i = 0;
            while (array.length > i) {
                driver.switchTo().frame(array[i]);
                i++;
            }
            logger.info("【当前Frame】:"+array[i-1]);
        }
        if(driver.findElements(By.xpath(stringBuffer.toString())).size()>0){
            f=true;
            driver.switchTo().defaultContent();
        }else {
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
    public  void AssertPassLog(Autosteps autosteps,String verityStr,Boolean parameterStr){
        logger.info("验证成功");
        t=true;
        writeExtentReport(autosteps,verityStr,parameterStr,"PASS");
    }
    /**
    * @Description:验证失败
    * @Date: 14:30 2018年03月05日
     */
    public  void AssertFailedLog(Autosteps autosteps,String verityStr,Boolean parameterStr){
        logger.info("验证失败");
        t=false;
        snapshotInfo();
        writeExtentReport(autosteps,verityStr,parameterStr,"FAILED");
    }
    /**
     * @将验证结果及测试数据写入测试报告中
     * @param status 验证状态
     * */
    public  void writeExtentReport(Autosteps autosteps,String verityStr,Boolean parameterStr,String status){
       try{
           parameter.log(LogStatus.INFO,autosteps.getTsactioncontent());
           if(status.equals("PASS")){
               verification.log(LogStatus.PASS,autosteps.getTsverificationcontent(),"PASS");
           }else{
               verification.log(LogStatus.FAIL,autosteps.getTsverificationcontent(),"FAILED");
               screenshot=extentReports.startTest("截图");
               screenshot.log(LogStatus.FAIL,screenshot.addBase64ScreenShot(ScreenShot.screenPath));
           }
           extentReports.flush();
       }catch (Error e){
           logger.info("验证结果写入测试报告出错");
       }
    }
    /**
     * @Description:写入子表测试报告
     * @date 2018/3/19 13:26
     */
    public  void writeReport(){
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
            screenshot=null;
        }
    }
}
