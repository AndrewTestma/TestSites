package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:Andrew
 * @Description:业务管理控制器
 * @Date 2018/2/24 0024
 */
@Controller("BusinessController")
@RequestMapping("/bus")
public class BusinessController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * @Description:业务管理视图
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/business/index";
    }
    /**
     * @Description:业务创建视图
     * */
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
        return "/manage/business/create";
    }
}
