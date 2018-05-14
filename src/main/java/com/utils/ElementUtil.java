package com.utils;

import com.pojo.Autosteps;
import com.pojo.LogInfo;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
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
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private WebDriver driver;

    private LogInfo logInfo;
    private LogOperatingUtil logOperatingUtil;

    private String host;
    private String session;

    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private ExtentTest  exception=null;
    public ExtentTest screenshot=null;

    public ElementUtil(WebDriver driver, LogInfo logInfo, LogOperatingUtil logOperatingUtil, String host, String session, ExtentReports extentReports, ExtentTest extentTest){
        this.driver=driver;
        this.logInfo=logInfo;
        this.logOperatingUtil = logOperatingUtil;
        this.host=host;
        this.session=session;
        this.extentReports=extentReports;
        this.extentTest=extentTest;
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
                logOperatingUtil.writeTxtFile("【当前Frame】:"+array[i-1],logInfo);
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
        }catch (TimeoutException e){
            logger.info("定位页面元素超时");
            logOperatingUtil.writeTxtFile("定位页面元素超时",logInfo);
            execptionExtentReports(autosteps);
        }catch(NoSuchElementException e){
            logger.info("无法定位页面元素");
            logOperatingUtil.writeTxtFile("无法定位页面元素",logInfo);
            execptionExtentReports(autosteps);
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
        logOperatingUtil.writeTxtFile("查找元素："+autosteps.getTsremarks()+"查找方式："+"[By."+autosteps.getTsselecttype()+":"+autosteps.getTsselectcontent()+"]",logInfo);
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
        WebElement webElement=findElement(autosteps);
        try{
            webElement.click();
            logger.error("点击："+autosteps.getTsremarks()+"-->点击成功");
        }catch (NullPointerException e){
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
            logOperatingUtil.writeTxtFile(autosteps.getTsremarks()+"输入: "+value,logInfo);
        }catch(Exception e){
            logger.error("找不到元素："+autosteps.getTsremarks()+"-->输入失败");
            logOperatingUtil.writeTxtFile("找不到元素："+autosteps.getTsremarks()+"-->输入失败",logInfo);
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

    public  void sikuliUploadFile(String path,String OSInfo){
        ClassLoader classLoader =Thread.currentThread().getContextClassLoader();
        StringBuffer fileimage=new StringBuffer();
        fileimage.append(classLoader.getResource("").getPath()+"serviceImg/");
        StringBuffer confirmImage=new StringBuffer();
        confirmImage.append(classLoader.getResource("").getPath()+"serviceImg/");
        Screen screen=new Screen();
        try {
            if(OSInfo.equals("win10")){
                fileimage.append("filepath.jpg");
                confirmImage.append("confirm.jpg");
            }
            screen.type(fileimage.toString(),path);
            screen.click(confirmImage.toString());
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        /*SikuliExtensionClient sikuli=new SikuliExtensionClient(host,5555,session);
        sikuli.uploadResourceBundle("serviceImg");

        TargetFactory targetFactory = sikuli.getTargetFactory();
        ImageTarget imageTarget = targetFactory.createImageTarget("filepath.jpg");

        DesktopScreenRegion desktop = sikuli.getDesktop();
        ScreenRegion screenRegion = desktop.find(imageTarget);

        Mouse mouse = sikuli.getMouse();
        mouse.click(screenRegion.getCenter());*/
    }
    /**
     * @Description:查找元素失败，添加进报告
     * @param:[autosteps]：操作步骤元素
     * @return:void
     * @date:2018/5/11 16:52
     */
    public void execptionExtentReports(Autosteps autosteps){
        try{
            if(exception==null ){
                exception=extentReports.startTest("元素异常");
            }
            exception.log(LogStatus.FAIL,autosteps.getTsautostepsname()+":定位失败","FAILED");
            extentReports.flush();
        }catch (Exception e){
            logger.info("ElementUtil:写入子表测试报告错误");
        }finally {
            extentTest.appendChild(exception);
            extentReports.endTest(exception);
            exception=null;
            screenshot=null;
        }
    }
}
