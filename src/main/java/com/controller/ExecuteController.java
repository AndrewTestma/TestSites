package com.controller;

import com.pojo.*;
import com.service.*;
import com.utils.BaseTest;
import com.utils.TestBaseCase;
import com.utils.TestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.testng.TestNG;

import javax.annotation.Resource;
import java.util.*;

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
    public static Product pro=null;
    public static OperatingEnv env=null;
    public static Business business=null;
    @Resource(name = "BusinessCaseService")
    private BusinessCaseService businessCaseService;
    @Resource(name = "UITestCaseService")
    private UITestCaseService uiTestCaseService;
    @Resource(name = "AutostepsService")
    private AutostepsService autostepsService;
    @Resource(name = "CaseStepsService")
    private CaseStepsService caseStepsService;
    @Resource(name = "ProductService")
    private ProductService productService;
    @Resource(name = "OperatingEnvService")
    private OperatingEnvService operatingEnvService;
    @Autowired
    private ResultService resultService;
    @Autowired
    private BusinessService businessService;
    @RequestMapping(value = "/business",method = RequestMethod.GET)
    @ResponseBody
    /**
    * @Description: 执行业务线，将值传递到TestBaseCase
    * @Param: [tsbusinessid:业务线ID, tsproductid:产品ID]
    * @return: java.lang.String
    * @Date: 10:52 2018年03月05日
     */
    public int execBusiness(String tsbusinessid, String tsproductid){
        int tsresultid=0;
        listMap=new LinkedHashMap<>();
        pro=productService.selectByPrimaryKey(Integer.valueOf(tsproductid));
        env=operatingEnvService.selectByPrimaryKey(0);
        business=businessService.selectByPrimaryKey(Integer.valueOf(tsbusinessid));
        List<Integer> uiIdList=businessCaseService.selectBytsbusinessid(Integer.valueOf(tsbusinessid));
        Result result=new Result();
        if(uiIdList.size()>0){
            execAutoSteps(uiIdList);
            TestNG testNG=new TestNG();
            TestListener testListener=new TestListener();
            testNG.addListener(testListener);
            testNG.setTestClasses(new Class[]{BaseTest.class});
            testNG.run();
            result.setTsbusinessid(Integer.valueOf(tsbusinessid));
            result.setTscount(1);
            result.setTstotalsteps(TestBaseCase.tstotalsteps);
            result.setTsrunsteps(TestBaseCase.tsrunsteps);
            result.setTstotaltime(TestBaseCase.tstotaltime);
            if(TestBaseCase.tstotalsteps!=0){
                result.setTsresult(TestBaseCase.tsrunsteps/TestBaseCase.tstotalsteps);
            }
            result.setTsproductid(Integer.valueOf(tsproductid));
            result.setTsbusinessid(Integer.valueOf(tsbusinessid));
            if(resultService.selectBybusinessid(Integer.valueOf(tsbusinessid))==null){
                tsresultid =resultService.insert(result);
            }else{
               result.setTsresultid(resultService.selectBybusinessid(Integer.valueOf(tsbusinessid)).getTsresultid());
               result.setTscount(resultService.selectBybusinessid(Integer.valueOf(tsbusinessid)).getTscount()+1);
               tsresultid =resultService.updateByPrimaryKey(result);
            }
        }
        TestBaseCase.tstotalsteps=0;
        TestBaseCase.tsrunsteps=0;
        TestBaseCase.tstotaltime=0;
        return tsresultid;
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
