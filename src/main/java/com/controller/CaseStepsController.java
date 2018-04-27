package com.controller;

import com.pojo.Autosteps;
import com.pojo.CaseSteps;
import com.service.CaseStepsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.serial.SerialJavaObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:测试用例与操作步骤中间表
 * @Date 2018/2/22 0022
 */
@Controller("CaseStepsController")
@RequestMapping("/casesteps")
public class CaseStepsController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "CaseStepsService")
    private CaseStepsService caseStepsService;
    //添加中间表
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(String uitestcaseID,String autostepsID){
        CaseSteps caseSteps=new CaseSteps();
        caseSteps.setTsuitestcaseid(Integer.valueOf(uitestcaseID));
        caseSteps.setTsautostepsid(Integer.valueOf(autostepsID));
        int i=caseStepsService.selectBytsorder(Integer.valueOf(uitestcaseID));
        caseSteps.setTsorder(i+1);
        return caseStepsService.insert(caseSteps);
    }
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    /**
    * @Description: 根据uiID和步骤ID删除中间表数据
    * @Param: [uitestcaseID, autostepsID]:uiID,操作步骤ID
    * @return: int
    * @Date: 16:19 2018年03月13日
     */
    public int del(String uitestcaseID,String autostepsID){
       CaseSteps caseSteps=new CaseSteps();
       caseSteps.setTsuitestcaseid(Integer.valueOf(uitestcaseID));
       caseSteps.setTsautostepsid(Integer.valueOf(autostepsID));
        return caseStepsService.deleteByMore(caseSteps);
    }
    /**
     * @Description:操作步骤排序
     * @param:[tsuitestcaseid]:测试用例ID,[jsondata]:操作步骤json字符串
     * @return:[int]：标识执行结果
     * @date:2018/4/27 14:19
     */
    @RequestMapping(value = "/sort",method = RequestMethod.POST)
    @ResponseBody
    public int sort(String tsuitestcaseid,String jsondata){
        JSONArray jsonArray = JSONArray.fromObject(jsondata);
        List<Autosteps> list = JSONArray.toList(jsonArray, Autosteps.class);
        caseStepsService.deleteBytsuitestcaseid(Integer.valueOf(tsuitestcaseid));
        List<CaseSteps> caseStepsList= new ArrayList<>();
        for(int i=0;i<list.size();i++){
            CaseSteps caseSteps=new CaseSteps();
            caseSteps.setTsautostepsid(list.get(i).getTsautostepsid());
            caseSteps.setTsuitestcaseid(Integer.valueOf(tsuitestcaseid));
            caseSteps.setTsorder(i+1);
            caseStepsList.add(caseSteps);
        }
        return  caseStepsService.insertBatch(caseStepsList);
    }
}
