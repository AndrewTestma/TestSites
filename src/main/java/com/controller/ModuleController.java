package com.controller;

import com.pojo.Module;
import com.service.ModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
public class ModuleController {
    @Resource(name = "ModuleService")
    private ModuleService moduleService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/module/index";
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false,defaultValue = "0",value = "offset")int offset,
            @RequestParam(required = false,defaultValue = "10",value = "limit")int limit,
            @RequestParam(required = false,defaultValue = "",value = "search")String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order){
        List<Module> list=moduleService.selectList(offset,limit);
        long total=list.size();
        Map<String ,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
                return "/manage/module/create";
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public int create(Module module){
        return moduleService.insert(module);
    }
}
