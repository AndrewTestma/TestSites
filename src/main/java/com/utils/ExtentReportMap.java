package com.utils;

import com.pojo.Autosteps;
import com.pojo.LogInfo;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mr.Andrew
 * @Title:ExtentReport报告Map
 * @Description: ExtentReportMap
 * @date 2018/3/2113:30
 */
public class ExtentReportMap {

    public static Map<Integer,ExtentReports> map=new HashMap<>();//存储不同的报告对象
    public static Map<Integer,Map<String,List<Autosteps>>> autosteps=new HashMap<>();//存储不同的操作步骤列表
    public static Map<Integer,LogInfo> log=new HashMap<>();//存储不同的操作日志
}
