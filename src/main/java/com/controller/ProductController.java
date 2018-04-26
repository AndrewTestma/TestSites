package com.controller;

import com.pojo.Product;
import com.pojo.User;
import com.pojo.UserProc;
import com.service.ProductService;
import com.service.UserProcService;
import com.service.UserService;
import com.utils.ExtentReportMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/11 0011
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "manage/product/index";
    }
    @RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpSession session) {
        List<Product> rows=(List<Product>)session.getAttribute("list");
        long total = rows.size();
        Map<String, Object> result = new HashMap<>();
        result.put("data", rows);
        result.put("total", total);
        return result;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
        return "/manage/product/create";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int create(Product product){
       return productService.insert(product);
    }
    /**
     * @Description:添加产品Session
     * @date:2018/3/22 15:00
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(String product, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        user.setTsproductid(Integer.valueOf(product));
        userService.updateByPrimaryKeySelective(user);
        return "";
    }
    /**
     * @Description:删除产品
     * @param:[tsproductid]:产品ID
     * @return:int:删除是否成功
     * @date:2018/4/10 13:40
     */
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public int del(Integer tsproductid){
        int i=0;
        try{
            i = productService.deleteByPrimaryKey(tsproductid);
        }catch (Exception e){
            logger.error("删除产品出错");
        }
        return i;
    }
    /**
     * @Description:编辑产品
     * @param:[tsproductid, model]:编辑的产品ID,编辑产品实体
     * @return:java.lang.String
     * @date:2018/4/10 13:54
     */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("tsproductid")Integer tsproductid, Model model){
        Product product=productService.selectByPrimaryKey(tsproductid);
        model.addAttribute("product",product);
        return "/manage/product/create";
    }
    /**
     * @Description:修改产品
     * @param:[product]:产品实体
     * @return:int
     * @date:2018/4/10 14:23
     */
    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public int update(Product product){
        return productService.updateByPrimaryKey(product);
    }
}
