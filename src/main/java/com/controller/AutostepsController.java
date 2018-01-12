package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.annotation.Resource;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/29 0029
 */
@Controller("AutostepsController")
@RequestMapping("/autosteps")
public class AutostepsController {
    /*private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "AutostepsService")
    private AutostepsService autostepsService;

    @RequestMapping(name = "/addpage",method = RequestMethod.GET)
    public String addpage(){
        return "autosteps/addpage";
    }
    @RequestMapping(name = "/add",method = RequestMethod.POST)
    public String add(Autosteps autosteps){
        int i=0;
        if(autosteps!=null){
            logger.info("添加操作步骤："+autosteps.getTsname());
            i=autostepsService.insert(autosteps);
        }
        logger.info("---------------"+i);
        return i>0?"success":"error";
    }*/
}
