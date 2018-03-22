package com.utils;

import com.pojo.Autosteps;
import com.relevantcodes.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Andrew
 * @Title: �洢ExtentReport����
 * @Description: ExtentReportMap
 * @date 2018/3/2113:30
 */
public class ExtentReportMap {

    public static Map<Integer,ExtentReports> map=new HashMap<>();//�洢�������
    public static Map<Integer,Map<String,List<Autosteps>>> autosteps=new HashMap<>();
    /**
     * @Description:��Map����ӻ�ȡ��ExtentReport����
     * @return:ExtentReport����
     * @date:2018/3/21 13:50
     */
    public static ExtentReports getMap(Integer key) {
        ExtentReports extentReports=null;
        if(map.get(key)!=null){
            extentReports=map.get(key);
        }
        return extentReports;
    }
}
