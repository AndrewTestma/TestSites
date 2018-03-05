package com.utils;


import com.pojo.Autosteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/26 0026
 */
public class BaseTest extends TestBaseCase {
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    public ElementAction elementAction=new ElementAction();
    @Test
    public void test(){
        logger.info("执行测试步骤");
        for(Map.Entry<String,List<Autosteps>> entry:autosteps.entrySet()){
                for(Autosteps autosteps1:entry.getValue()){
                    if(autosteps1.getTsactiontype().equals("单击")){
                        elementAction.click(autosteps1);
                    }else if(autosteps1.getTsactiontype().equals("输入")){
                       elementAction.sendKey(autosteps1,autosteps1.getTsactioncontent());
                    }
                    Assertion.verityType(autosteps1);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
