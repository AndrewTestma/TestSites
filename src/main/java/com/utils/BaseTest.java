package com.utils;


import com.pojo.Autosteps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/26 0026
 */
public class BaseTest extends TestBaseCase {
    public Logger logger= LoggerFactory.getLogger(this.getClass());
   /* public ElementAction elementAction=null;
    public Assertion assertion=null;
    @Test()
    public void test(){
        logger.info("执行测试步骤");
        String ip=null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            logger.info(ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Map<String,List<Autosteps>> autosteps=null;
        if(ExtentReportMap.autosteps.get(ip)!=null){
            autosteps=ExtentReportMap.autosteps.get(ip);
        }
        for(Map.Entry<String,List<Autosteps>> entry:autosteps.entrySet()){
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
                   *//* tsrunsteps++;*//*
                }
            assertion.writeReport();
            extentReports.flush();
            extentReports.endTest(extentTest);
        }
    }*/
}
