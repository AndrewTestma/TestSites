package com.controller;

import com.pojo.OperatingEnv;
import com.service.OperatingEnvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @Description:运行环境控制器
 * @Date 2018/2/28 0028
 */
@Controller("OperatingEnvController")
@RequestMapping("/env")
public class OperatingEnvController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource(name = "OperatingEnvService")
    private OperatingEnvService operatingEnvService;
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
}
