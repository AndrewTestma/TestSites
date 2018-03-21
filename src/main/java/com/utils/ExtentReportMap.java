package com.utils;

import com.relevantcodes.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Andrew
 * @Title: 存储ExtentReport对象
 * @Description: ExtentReportMap
 * @date 2018/3/2113:30
 */
public class ExtentReportMap {

    public static Map<String,ExtentReports> map=new HashMap<>();//存储报告对象
    /**
     * @Description:在Map中添加或取出ExtentReport对象
     * @return:ExtentReport对象
     * @date:2018/3/21 13:50
     */
    public static void setMap(String key,ExtentReports extentReports){
        if(map.get(key)!=null) {
           map.remove(key);
        }
        map.put(key, extentReports);
    }
    public static ExtentReports getMap(String key) {
        ExtentReports extentReports=null;
        if(map.get(key)!=null){
            extentReports=map.get(key);
        }
        return extentReports;
    }
}
