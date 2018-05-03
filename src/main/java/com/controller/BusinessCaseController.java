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
     /**
     * @Description: 增加业务线与测试用例中间表数据
     * @Param: [tsbusinessid, tsuitestcaseid]
     * @return: int
     * @Date: 15:28 2018年03月14日
      */
     public int add(String  tsbusinessid ,String tsuitestcaseid ){
        logger.debug("增加业务线与测试用例中间表数据");
        BusinessCase businessCase=new BusinessCase();
        businessCase.setTsbusinessid(Integer.valueOf(tsbusinessid));
        businessCase.setTsuitestcaseid(Integer.valueOf(tsuitestcaseid));
        int i=0;
        if(businessCaseService.selectBytsorder(Integer.valueOf(tsbusinessid))!=null){
             i=businessCaseService.selectBytsorder(Integer.valueOf(tsbusinessid));
        }
        businessCase.setTsorder(i+1);
        return businessCaseService.insert(businessCase);
     }
     @RequestMapping(value = "/del",method = RequestMethod.POST)
     @ResponseBody
     /** 
     * @Description: 删除业务线与测试用例中间表数据 
     * @Param: [tsbusinessid, tsuitestcaseid]
     * @return: int
     * @Date: 15:28 2018年03月14日
      */ 
     public int del(String  tsbusinessid ,String tsuitestcaseid,String tsorder){
         logger.debug("删除业务线与测试用例中间表数据");
         return  businessCaseService.deleteBytsorder(Integer.valueOf(tsbusinessid),Integer.valueOf(tsuitestcaseid),Integer.valueOf(tsorder));
     }
}
