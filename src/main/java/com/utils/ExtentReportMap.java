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
    public static Map<Integer,Map<String,List<Autosteps>>> autosteps=new HashMap<>();//�洢ҵ���߶�Ӧ�Ĳ�������
    //public static Map<Integer,Integer>productSession=new HashMap<>();//�洢��ƷID
}
