package com.controller;

import com.pojo.BusinessCase;
import com.service.BusinessCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author:Andrew
 * @Description:业务与测试用例的中间表
 * @Date 2018/2/26 0026
 */
@Controller("BusinessCaseController")
@RequestMapping("/buscase")
public class BusinessCaseController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "BusinessCaseService")
    private BusinessCaseService businessCaseService;
    /**
     * @Description:添加中间表
     * */
     @RequestMapping(value = "/add",method = RequestMethod.POST)
     @ResponseBody
    public int add(String  tsbusinessid ,String tsuitestcaseid ){
        BusinessCase businessCase=new BusinessCase();
        businessCase.setTsbusinessid(Integer.valueOf(tsbusinessid));
        businessCase.setTsuitestcaseid(Integer.valueOf(tsuitestcaseid));
        return businessCaseService.insert(businessCase);
     }
}
