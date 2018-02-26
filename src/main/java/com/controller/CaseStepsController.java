package com.controller;

import com.pojo.CaseSteps;
import com.service.CaseStepsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
}
