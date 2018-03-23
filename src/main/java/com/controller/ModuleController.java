package com.controller;

import com.pojo.Module;
import com.pojo.User;
import com.service.ModuleService;
import com.utils.ExtentReportMap;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2018/1/15 0015
 */
@Controller
@RequestMapping("/module")
@SessionAttributes("user")
public class ModuleController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "ModuleService")
    private ModuleService moduleService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/module/index";
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@ModelAttribute("user")User user){
        Integer productID= user.getTsproductid();
        List<Module> list=moduleService.selectList(productID);
        long total=list.size();
        Map<String ,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/selectlist",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray selectList(@ModelAttribute("user")User user){
        Integer productID=user.getTsproductid();
        List<Module> list=moduleService.selectList(productID);
        JSONArray jsonArray=JSONArray.fromObject(list);
        return jsonArray;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
                return "/manage/module/create";
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public int create(Module module,@ModelAttribute("user")User user){
        Integer productID=user.getTsproductid();
        logger.info(productID.toString());
        module.setTsproductid(productID);
        return moduleService.insert(module);
    }
    /**
     * @Description:将模块存入session
     * */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(String module){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session=request.getSession();
        session.setAttribute("module",module);
        return (String) session.getAttribute("module");
    }
}
