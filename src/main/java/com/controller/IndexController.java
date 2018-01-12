package com.controller;

import com.pojo.Product;
import com.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
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
}
