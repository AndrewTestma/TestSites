package com.utils;

import com.relevantcodes.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Andrew
 * @Title: �洢ExtentReport����
 * @Description: ExtentReportMap
 * @date 2018/3/2113:30
 */
public class ExtentReportMap {

    public static Map<String,ExtentReports> map=new HashMap<>();//�洢�������
    /**
     * @Description:��Map����ӻ�ȡ��ExtentReport����
     * @return:ExtentReport����
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
