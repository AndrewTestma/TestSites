package com.controller;

import com.pojo.Product;
import com.service.ProductService;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/5 0005
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name="ProductService")
    private ProductService productService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String index(Model model){
        List<Product> list=productService.selectList(0,10);
        model.addAttribute("list",list);
        return "manage/index";
    }
    @RequestMapping(value = "/session",method = RequestMethod.POST)
    public String session(HttpServletRequest request,HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }
        return "";
    }
}
