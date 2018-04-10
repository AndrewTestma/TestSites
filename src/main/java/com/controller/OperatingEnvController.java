package com.controller;

import com.pojo.OperatingEnv;
import com.pojo.User;
import com.service.OperatingEnvService;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:运行环境控制器
 * @Date 2018/2/28 0028
 */
@Controller("OperatingEnvController")
@RequestMapping("/env")
public class OperatingEnvController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OperatingEnvService operatingEnvService;
    @Autowired
    private UserService userService;
    /**
     * @Description:运行环境主视图
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/Operatingenv/index";
    }
    /**
     * @Description:显示数据列表
     * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order){
        List<OperatingEnv> rows= operatingEnvService.selectList(offset,limit);
        Map<String,Object> result=new HashMap<>();
        long total=rows.size();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    /**
     * @Description:新增视图
     * */
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
        return "/manage/Operatingenv/create";
    }
    /**
     * @Description:添加运行环境
     * */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(OperatingEnv operatingEnv){
        operatingEnv.setApply(1);
        return operatingEnvService.insert(operatingEnv);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    /**
    * @Description: 运行设置更新
    * @Param: [operatingEnv] 运行环境对象
    * @return: java.lang.String 是否成功
    * @Date: 2018/3/1 0001
    */
    public String update(OperatingEnv operatingEnv){
        int i=operatingEnvService.updateByPrimaryKeySelective(operatingEnv);
        if(i>0){
            return "success";
        }else{
            return  "error";
        }
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    /**
    * @Description:  删除操作
    * @Param: [tsoperatingenvid] 主键ID
    * @return: java.lang.Integer 是否成功
    * @Date: 10:46 2018年03月01日
     */
    public int delete(String tsoperatingenvid){
        return operatingEnvService.deleteByPrimaryKey(Integer.valueOf(tsoperatingenvid));
    }
    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    @ResponseBody
    /**
     * @Description:设置用户默认的运行方式
     * @param:[tsoperatingenvid, modelMap, username]:运行方式ID，获取用户
     * @return:int
     * @date:2018/3/21 10:18
     */
    public int apply(String tsoperatingenvid, ModelMap modelMap, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        user.setTsoperatingenvid(tsoperatingenvid);
        modelMap.addAttribute("tsoperatingenvid",tsoperatingenvid);
        return userService.updateByPrimaryKeySelective(user);
    }
}
