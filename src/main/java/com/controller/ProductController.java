package com.controller;

import com.pojo.Product;
import com.pojo.User;
import com.service.ProductService;
import com.service.UserService;
import com.utils.ExtentReportMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order) {
        List<Product> rows = productService.selectList(offset,limit);
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
     * @Description:
     * @param:
     * @return:
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
}
