package com.utils;

import com.pojo.Autosteps;
import com.relevantcodes.extentreports.ExtentReports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Andrew
 * @Title: 存储ExtentReport对象
 * @Description: ExtentReportMap
 * @date 2018/3/2113:30
 */
public class ExtentReportMap {

    public static Map<Integer,ExtentReports> map=new HashMap<>();//存储报告对象
    public static Map<Integer,Map<String,List<Autosteps>>> autosteps=new HashMap<>();//存储业务线对应的操作步骤
    public static Map<Integer,Integer>productSession=new HashMap<>();//存储产品ID
}
