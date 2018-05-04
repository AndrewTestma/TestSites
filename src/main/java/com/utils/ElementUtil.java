package com.utils;

import com.pojo.Autosteps;
import com.pojo.LogInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @program: TestSites
 * @description: 重写Selenium事件类
 * @author: Mr.Andrew
 * @create: 2018-03-05 11:21
 **/
public class ElementUtil {
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    public WebDriver driver;
    public LogInfo logInfo;
    public LogOperating logOperating;
    public ElementUtil(WebDriver driver, LogInfo logInfo, LogOperating logOperating){
        this.driver=driver;
        this.logInfo=logInfo;
        this.logOperating=logOperating;
    }
   /**
   * @Description: 查找单个元素
   * @Param:[autosteps:存储元素的对象]
   * @return:[WebElement:返回查找到的元素]
   * @Date: 11:33 2018年03月05日
    */
    public WebElement findElement(final Autosteps autosteps){
        WebElement webElement = null;
        try{
            if(autosteps.getTsframepath().equals("default")){
                driver.switchTo().defaultContent();
            }
            else if(!autosteps.getTsframepath().split("/")[0].equals("")){
                String[] array = autosteps.getTsframepath().split("/");
                int i = 0;
                driver.switchTo().defaultContent();
                while (array.length > i) {
                    driver.switchTo().frame(array[i]);
                    i++;
                }
                logger.info("【当前Frame】:"+array[i-1]);
                logOperating.writeTxtFile("【当前Frame】:"+array[i-1],logInfo);
            }
            webElement=(new WebDriverWait(driver,5).until(
                    new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver input) {
                            WebElement  element=getElement(autosteps);
                            while(element==null) {
                                    element=getElement(autosteps);
                                }
                            return element;
                            }
                        }
            ));

       /*     webElement=(new WebDriverWait(driver,5).until(
                    new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver input) {
                            WebElement  element=getElement(autosteps);
                            int k=0;
                            while(element==null) {
                                if (k == 1) {
                                    if (element == null && autosteps.getTsframepath().split("/").length >= 1 && autosteps.getTsframepath().split("/")[0] != "") {
                                        String[] array = autosteps.getTsframepath().split("/");
                                        int i = 0;
                                        driver.switchTo().defaultContent();
                                        while (array.length > i) {
                                            driver.switchTo().frame(array[i]);
                                            i++;
                                        }
                                        logger.info("【当前Frame】:"+array[i-1]);
                                    }
                                    element=getElement(autosteps);
                                }
                                k++;
                            }
                            return element;
                        }
                    }
            ));*/
            /*webElement=getElement(autosteps);*/
        }catch(NoSuchElementException e){
            logger.info("无法定位页面元素");
            logOperating.writeTxtFile("无法定位页面元素",logInfo);
        }
        return webElement;
    }
    /**
    * @Description:通过selenium.By方法查找元素
    * @Param:[autosteps]:存储元素的对象
    * @return:[WebElement]:返回查找到的元素
    * @Date: 11:34 2018年03月05日
     */
    public WebElement getElement(Autosteps autosteps){
        logger.info("查找元素："+autosteps.getTsremarks()+"查找方式："+"[By."+autosteps.getTsselecttype()+":"+autosteps.getTsselectcontent()+"]");
        logOperating.writeTxtFile("查找元素："+autosteps.getTsremarks()+"查找方式："+"[By."+autosteps.getTsselecttype()+":"+autosteps.getTsselectcontent()+"]",logInfo);
        WebElement webElement;
        switch (autosteps.getTsselecttype())
        {
            case "xpath" :
                webElement=driver.findElement(By.xpath(autosteps.getTsselectcontent()));
                break;
            case "id":
                webElement=driver.findElement(By.id(autosteps.getTsselectcontent()));
                break;
            case "cssSelector":
                webElement=driver.findElement(By.cssSelector(autosteps.getTsselectcontent()));
                break;
            case "name":
                webElement=driver.findElement(By.name(autosteps.getTsselectcontent()));
                break;
            case "className":
                webElement=driver.findElement(By.className(autosteps.getTsselectcontent()));
                break;
            case "linkText":
                webElement=driver.findElement(By.linkText(autosteps.getTsselectcontent()));
                break;
            case "partialLinkText":
                webElement=driver.findElement(By.partialLinkText(autosteps.getTsselectcontent()));
                break;
            case "tagName":
                webElement=driver.findElement(By.tagName(autosteps.getTsselectcontent()));
                break;
            default :
                webElement=driver.findElement(By.xpath(autosteps.getTsselectcontent()));
                break;
        }
        return webElement;
    }
    /**
    * @Description: 单击元素动作
    * @Param:[autosteps]:存储元素的对象
    * @Date: 11:40 2018年03月05日
     */
    public void click(Autosteps autosteps){
        WebElement webElement=null;
        try{
            webElement=findElement(autosteps);
            webElement.click();
            logger.error("点击："+autosteps.getTsremarks()+"-->点击成功");
        }catch (NoSuchElementException e){
            logger.error("找不到元素："+autosteps.getTsremarks()+"-->点击失败");
        }
    }
    /**
    * @Description:输入文本动作
    * @Param:[autosteps]:存储元素的对象,[value]:输入内容
    * @Date: 11:43 2018年03月05日
     */
    public void sendKey(Autosteps autosteps,String value){
        WebElement webElement;
        try{
            webElement=findElement(autosteps);
            webElement.click();
            webElement.sendKeys(value);
            logger.info(autosteps.getTsremarks()+"输入: "+value);
            logOperating.writeTxtFile(autosteps.getTsremarks()+"输入: "+value,logInfo);
        }catch(Exception e){
            logger.error("找不到元素："+autosteps.getTsremarks()+"-->输入失败");
            logOperating.writeTxtFile("找不到元素："+autosteps.getTsremarks()+"-->输入失败",logInfo);
        }
    }
    /**
     * @Description:控件等待时间
     * @param: [second]:毫秒
     * @return:null
     * @date:2018/5/4 14:12
     */
    public void sleepTime(long second){
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void sikuliUploadFile(String path){
        Screen screen=new Screen();
        screen.type("F:/TestSites/target/classes/images/filepath.jpg",path);
        try {
            screen.click("F:/TestSites/target/classes/images/confirm.jpg");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
    }
}
