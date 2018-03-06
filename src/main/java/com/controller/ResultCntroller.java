package com.controller;

import com.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @program: TestSites
 * @description: 报告控制器
 * @author: Mr.Andrew
 * @create: 2018-03-06 10:01
 **/
@Controller("ResultCntroller")
@RequestMapping("/result")
public class ResultCntroller {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResultService resultService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    /**
    * @Description:返回报告首页
    * @return: java.lang.String
    * @Date: 10:04 2018年03月06日
     */
    public String index(){
        return "/manage/result/index";
    }
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Object info(String tsbusinessid){
        return resultService.selectBybusinessid(Integer.valueOf(tsbusinessid));
    }
}
