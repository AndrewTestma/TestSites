package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * @author Mr.Andrew
 * @Title: �û�������
 * @Description: UserController
 * @date 2018/3/2017:02
 */
@Controller("UserController")
@SessionAttributes({"username","tsoperatingenvid"})
@RequestMapping("/user")
public class UserController {

    public Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    /**
     * @Description:��֤�û��Ƿ����
     * @param:[name, password]:�û���,����
     * @return:java.lang.String
     * @date:2018/3/20 17:42
     */
    @ResponseBody
    public int login(String name,String password,ModelMap model){
        User user=userService.selectCount(name,password);
        int i=0;
        if(user!=null){
            model.addAttribute("username",user.getTsname());
            model.addAttribute("tsoperatingenvid",user.getTsoperatingenvid());
            i=1;
        }

        return i;
    }

}
