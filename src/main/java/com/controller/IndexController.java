package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/5 0005
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "UserService")
    private UserService userService;
    @RequestMapping(value = "/a",method = RequestMethod.GET)
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("tsloginname")String loginname, @RequestParam("tspassword")String password){
        User user=userService.selectByLoginName(loginname,password);
       return user!=null?new ModelAndView("redirect:/ui/list"):new ModelAndView("redirect:/index/a");
    }
}
