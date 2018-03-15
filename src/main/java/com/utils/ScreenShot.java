package com.utils;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @program: TestSites
 * @description: 截图工具类
 * @author: Mr.Andrew
 * @create: 2018-03-15 13:17
 **/
public class ScreenShot {
    public WebDriver driver;
    private String screenName;
    Logger log= LoggerFactory.getLogger(this.getClass().getName());
    public ScreenShot(WebDriver driver){
        this.driver=driver;
    }
    public void setscreenName(String screenName){
        this.screenName=screenName;
    }
    private void takeScreenshot(String screenPath){
        File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            Files.copy(scrFile,new File(screenPath));
            log.error("错误截图:"+screenPath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  String takeScreenshot(){
        String screenName =this.screenName+ ".jpg";
        File dir = new File("snapshot");
        if (!dir.exists()){
            dir.mkdirs();
        }
        //dir.getAbsolutePath() +
        String screenPath = "snapshot\\" + screenName;
        this.takeScreenshot(screenPath);
        return screenPath;
    }
}
