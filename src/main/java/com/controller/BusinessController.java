package com.controller;

import com.pojo.Business;
import com.service.BusinessCaseService;
import com.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Andrew
 * @Description:业务管理控制器
 * @Date 2018/2/24 0024
 */
@Controller("BusinessController")
@RequestMapping("/bus")
public class BusinessController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BusinessService businessService;
    @Autowired
    private BusinessCaseService businessCaseService;
    /**
     * @Description:业务管理视图
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/business/index";
    }
    /**
     * @Description:业务创建视图
     * */
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
        return "/manage/business/create";
    }
    /**
     * @Description：业务列表
     * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order){
        List<Business> rows=businessService.selectList(offset,limit);
        long total=rows.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    /**
    * @Description: 添加业务线
    * @Param: [business, request]:业务实体对象,获取session中的产品ID
    * @return: int
    * @Date: 15:47 2018年03月14日
     */
    public int add(Business business, HttpServletRequest request){
        logger.debug("/bus/add:添加业务线");
        HttpSession session=request.getSession();
        business.setTsproductid(Integer.valueOf((String)session.getAttribute("product")));
        businessService.insert(business);
        return business.getTsbusinessid();
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    /**
    * @Description: 传递实体对象，进行编辑修改
    * @Param: [tsbusinessid, model]:业务ID，实体对象
    * @return: java.lang.String
    * @Date: 19:18 2018年03月14日
     */
    public String edit(@RequestParam("tsbusinessid")String tsbusinessid, Model model){
        logger.debug("/bus/edit:传递实体对象，进行编辑修改");
        Business business=businessService.selectByPrimaryKey(Integer.valueOf(tsbusinessid));
        model.addAttribute("business",business);
        return "/manage/business/create";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    /**
    * @Description: 修改业务
    * @Param: [business]:实体对象
    * @return: int
    * @Date: 19:23 2018年03月14日
     */
    public int update(String  tsbusinessid,String tsname){
        logger.debug("/bus/update:修改业务");
        Business business=new Business();
        business.setTsbusinessid(Integer.valueOf(tsbusinessid));
        business.setTsname(tsname);
        return businessService.updateByPrimaryKeySelective(business);
    }
    /**
     * @Description:删除业务
     * @param:tsbusinessid:主键
     * @return:删除个数
     * @date: 2018/3/19 18:41
     */
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    @ResponseBody
    public int del(String tsbusinessid){
        businessCaseService.deleteBytsbusinessid(Integer.valueOf(tsbusinessid));
        return businessService.deleteByPrimaryKey(Integer.valueOf(tsbusinessid));
    }
}
