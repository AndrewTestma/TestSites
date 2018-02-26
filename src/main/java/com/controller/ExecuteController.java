package com.controller;

import com.pojo.Autosteps;
import com.service.AutostepsService;
import com.service.BusinessCaseService;
import com.service.CaseStepsService;
import com.service.UITestCaseService;
import com.utils.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.TestNG;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:测试用例执行控制器
 * @Date 2018/2/26 0026
 */
@Controller("ExecuteController")
@RequestMapping("/exec")
public class ExecuteController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    public static  Map<String,List<Autosteps>> listMap=null;
    @Resource(name = "BusinessCaseService")
    private BusinessCaseService businessCaseService;
    @Resource(name = "UITestCaseService")
    private UITestCaseService uiTestCaseService;
    @Resource(name = "AutostepsService")
    private AutostepsService autostepsService;
    @Resource(name = "CaseStepsService")
    private CaseStepsService caseStepsService;
    /**
     * @Description:执行的业务线
     * */
    @RequestMapping(value = "/business",method = RequestMethod.GET)
    @ResponseBody
    public String execBusiness(String tsbusinessid){
        listMap=new HashMap<>();
        List<Integer> uiIdList=businessCaseService.selectBytsbusinessid(Integer.valueOf(tsbusinessid));
        if(uiIdList.size()>0){
            execAutoSteps(uiIdList);
            TestNG testNG=new TestNG();
            testNG.setTestClasses(new Class[]{new BaseTest().getClass()});
            testNG.run();
            return "success";
        }else {
            return "error";
        }
    }
    /**
     * @Description:执行的操作步骤
     * */
    public void execAutoSteps(List<Integer> uiIdList){
        for(int i:uiIdList){
            List<Autosteps> autosteps=new ArrayList<>();
            String tsname=uiTestCaseService.selectByPrimaryKey(i).getTsname();
            logger.info("测试用例ID-----"+tsname);
            for(int n: caseStepsService.selectBytsuitestcaseid(i)){
                autosteps.add(autostepsService.selectByPrimaryKey(n));
            }
            listMap.put(tsname,autosteps);
        }
    }
}
