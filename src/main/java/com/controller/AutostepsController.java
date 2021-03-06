package com.controller;

import com.pojo.Autosteps;
import com.pojo.User;
import com.service.AutostepsService;
import com.service.CaseStepsService;
import com.utils.ExtentReportMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public Object list(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        List<Autosteps> rows=autostepsService.selectList(user.getTsproductid());
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
    public int add(Autosteps autosteps,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        autosteps.setTsproductid(user.getTsproductid());
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
    /**
     * @Description: 返回某个模块下的所有操作步骤
     * @Param: [moduleName]:模块名称
     * @return: java.util.Map<java.lang.String,java.lang.Object>：绑定在table的数据
     * @Date: 9:40 2018年03月13日
     */
    @RequestMapping(value = "/listByModule",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listByModule(@RequestParam("moduleName") String moduleName,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        Map<String,Object> result=new HashMap<>();
        List<Autosteps> rows=autostepsService.selectByModule(moduleName,user.getTsproductid());
        long total=rows.size();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    /**
     * @Description:返回实体对象到修改页面
     * @param:[tsautostepsid]:操作步骤ID,[Model]:返回到前端的model对象
     * @return:[String]:视图名称
     * @date:2018/4/26 16:09
     */
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(@RequestParam("tsautostepsid")String tsautostepsid,Model model){
        Autosteps autosteps = autostepsService.selectByPrimaryKey(Integer.valueOf(tsautostepsid));
        model.addAttribute("autosteps",autosteps);
        return "/manage/autosteps/create";
    }
}
