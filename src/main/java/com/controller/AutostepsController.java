package com.controller;

import com.pojo.Autosteps;
import com.pojo.User;
import com.service.AutostepsService;
import com.service.CaseStepsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


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
 * @Date 2017/12/29 0029
 */
@Controller("AutostepsController")
@RequestMapping("/autosteps")
@SessionAttributes("user")
public class AutostepsController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "AutostepsService")
    private AutostepsService autostepsService;
    @Resource(name ="CaseStepsService")
    private CaseStepsService caseStepsService;
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return null;
    }
    /**
     * @see:查找所有的操作步骤
     * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order,
            @RequestParam(required = false)Integer uitestcaseid){
        List<Autosteps> rows=autostepsService.selectList(offset,limit);
        long total=rows.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    /**
     * @see:查找测试用例对应的操作步骤
     * */
    @RequestMapping(value = "/tcstep",method = RequestMethod.GET)
    @ResponseBody
    public Object tcstep(String uitestcaseID){
        System.out.println(uitestcaseID);
        List<Integer> list=caseStepsService.selectBytsuitestcaseid(Integer.valueOf(uitestcaseID));
        List<Autosteps> autosteps=new ArrayList<>();
        for(Integer i:list){
            autosteps.add(autostepsService.selectByPrimaryKey(i));
        }
        long total=autosteps.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",autosteps);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
                return "manage/autosteps/create";
    }
    /**
     * @Description:添加操作步骤
     * */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(Autosteps autosteps,@ModelAttribute("user")User user){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        autosteps.setTsproductid(Integer.valueOf((String)session.getAttribute("product")));
        autosteps.setTsmodulename((String)session.getAttribute("module"));
        autosteps.setTscommon(0);//每次新增默认操作步骤为执行失败
        autosteps.setTscreator(user.getTsname());
        autostepsService.insert(autosteps);
        return autosteps.getTsautostepsid();
    }
    /**
     * @Description:修改操作步骤
     * */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Autosteps autosteps){
        int i=autostepsService.updateByPrimaryKeySelective(autosteps);
        if(i==1){
            logger.info("操作步骤更新成功");
            return "success";
        }else{
            logger.info("操作步骤更新失败");
            return "error";
        }
    }
    @RequestMapping(value = "/listByModule",method = RequestMethod.GET)
    @ResponseBody
    /**
    * @Description: 返回某个模块下的所有操作步骤
    * @Param: [moduleName]:模块名称
    * @return: java.util.Map<java.lang.String,java.lang.Object>：绑定在table的数据
    * @Date: 9:40 2018年03月13日
     */
    public Map<String,Object> listByModule(String moduleName){
        Map<String,Object> result=new HashMap<>();
        List<Autosteps> rows=autostepsService.selectByModule(moduleName);
        long total=rows.size();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
}
