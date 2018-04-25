package com.controller;

import com.pojo.Module;
import com.pojo.User;
import com.service.ModuleService;
import com.service.UITestCaseService;
import com.utils.ExtentReportMap;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ModuleController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ModuleService moduleService;
    @Autowired
    private UITestCaseService uiTestCaseService;
    /**
     * @Description: 获取模块视图
     * @date:2018/4/25 16:46
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/module/index";
    }
    /**
     * @Description:获取模块列表
     * @param:[request]:获取登录session中的信息
     * @date:2018/4/25 16:46
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        Integer productID= user.getTsproductid();
        List<Module> list=moduleService.selectList(productID);
        long total=list.size();
        Map<String ,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",total);
        return result;
    }
    /**
     * @Description: 获取产品下的模块
     * @param:[request]:获取登录session中的信息
     * @return:[JSONArray]:返回json数组
     * @date:2018/4/25 16:51
     */
    @RequestMapping(value = "/selectlist",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray selectList(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        Integer productID=user.getTsproductid();
        List<Module> list=moduleService.selectList(productID);
        JSONArray jsonArray=JSONArray.fromObject(list);
        return jsonArray;
    }
    /**
     * @Description:获取模块创建视图(暂被弃用)
     * @param:
     * @return:
     * @date:2018/4/25 16:52
     */
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
                return "/manage/module/create";
    }
    /**
     * @Description:添加模块
     * @param:[module]:module实体,[request]:获取登录session中的信息
     * @return:[int]:标识是否成功
     * @date:2018/4/25 16:53
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @ResponseBody
    public int create(Module module,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        Integer productID=user.getTsproductid();
        module.setTsproductid(productID);
        return moduleService.insert(module);
    }
    /**
     * @Description:将模块存入session,更换模块时，重新加载测试用例列表
     * @param:[module]:模块名称,
     * @return:[String]：返回模块名称
     * @date:2018/4/25 16:55
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(String module,HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("module",module);
        return (String) session.getAttribute("module");
    }
    /**
     * @Description:删除模块
     * @param:[tsmoduleid]
     * @return:int
     * @date:2018/4/25 16:44
     */
    @RequestMapping(value = "/del",method = RequestMethod.POST)
    @ResponseBody
    public int del(String tsmoduleid,HttpSession session){
        User user =(User) session.getAttribute("user");
        Module module=moduleService.selectByPrimaryKey(Integer.valueOf(tsmoduleid));
        if(uiTestCaseService.selectListByModuel(module.getTsame(),user.getTsproductid()).size()>0){
            return 0;
        }else{
            return moduleService.deleteByPrimaryKey(Integer.valueOf(tsmoduleid));
        }
    }
}
