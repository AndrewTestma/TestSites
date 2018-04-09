package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author Mr.Andrew
 * @Title: 用户控制器
 * @Description: UserController
 * @date 2018/3/2017:02
 */
@Controller("UserController")
@RequestMapping("/user")
public class UserController {

    public Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    /**
     * @Description:验证用户是否存在
     * @param:[name, password]:用户名,密码
     * @return:java.lang.String
     * @date:2018/3/20 17:42
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public int login(String name, String password,HttpSession session){
        User user=userService.selectCount(name,password);
        if(user!=null){
            session.setAttribute("user",user);
            return 1;
        }
        return 0;
    }
    /**
     * @Description:退出登录
     * @param:页面session
     * @return:重定向视图
     * @date:2018/4/9 14:39
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session)throws Exception{
        session.invalidate();
        return "redirect:/index/login";
    }
}
