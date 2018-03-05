package com.controller;

import com.pojo.Autosteps;
import com.pojo.OperatingEnv;
import com.pojo.Product;
import com.service.*;
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
    public static Product pro=null;
    public static OperatingEnv env=null;
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
    @RequestMapping(value = "/business",method = RequestMethod.GET)
    @ResponseBody
    /**
    * @Description: 执行业务线，将值传递到TestBaseCase
    * @Param: [tsbusinessid:业务线ID, tsproductid:产品ID]
    * @return: java.lang.String
    * @Date: 10:52 2018年03月05日
     */
    public String execBusiness(String tsbusinessid,String tsproductid){
        listMap=new HashMap<>();
        pro=productService.selectByPrimaryKey(Integer.valueOf(tsproductid));
        env=operatingEnvService.selectByPrimaryKey(0);
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
