package com.controller;

import com.pojo.User;
import com.service.UserService;
import com.utils.ExtentReportMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author Mr.Andrew
 * @Title: �û�������
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
     * @Description:��֤�û��Ƿ����
     * @param:[name, password]:�û���,����
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
     * @Description:�˳���¼
     * @param:ҳ��session
     * @return:�ض�����ͼ
     * @date:2018/4/9 14:39
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session)throws Exception{
        session.invalidate();
        return "redirect:/index/login";
    }
}
