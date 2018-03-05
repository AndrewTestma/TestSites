package com.utils;

import com.pojo.Autosteps;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * @program: TestSites
 * @description: 验证类
 * @author: Mr.Andrew
 * @create: 2018-03-05 14:03
 **/
public class Assertion  extends TestBaseCase {
    private static Logger logger= LoggerFactory.getLogger(Assertion.class);

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
            AssertPassLog();
        }catch(Error e){
            AssertFailedLog();
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
            AssertPassLog();
        }catch(Error e){
            AssertFailedLog();
        }
    }
    /**
    * @Description: 验证成功
    * @Date: 14:29 2018年03月05日
     */
    public static void AssertPassLog(){
        logger.info("验证成功");
    }
    /**
    * @Description:验证失败
    * @Date: 14:30 2018年03月05日
     */
    public static void AssertFailedLog(){
        logger.info("验证失败");
    }
}
