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
    @Test
    public void test(){
        WebElement webElement=null;
        logger.info("执行测试步骤");
        for(Map.Entry<String,List<Autosteps>> entry:autosteps.entrySet()){
                for(Autosteps autosteps1:entry.getValue()){
                    if(autosteps1.getTsselecttype().equals("id")){
                        webElement=driver.findElement(By.id(autosteps1.getTsselectcontent()));
                    }
                    if(autosteps1.getTsactiontype().equals("单击")){
                        webElement.click();
                    }else if(autosteps1.getTsactiontype().equals("输入")){
                        webElement.sendKeys(autosteps1.getTsactioncontent());
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
