package com.controller;

import com.pojo.UITestCase;
import com.pojo.User;
import com.service.BusinessCaseService;
import com.service.UITestCaseService;
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
import java.util.*;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/21 0021
 */
@Controller("UITestCaseController")
@RequestMapping("/ui")
@SessionAttributes("user")
public class UITestCaseController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "UITestCaseService")
    private UITestCaseService uiTestCaseService;
    @Autowired
    private BusinessCaseService businessCaseService;
    /**
     * @Description:ui测试用例主页
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/uitestcase/index";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false)String module,@ModelAttribute("user")User user){
        List<UITestCase> rows;
        Integer productID=ExtentReportMap.productSession.get(user.getTsuserid());
        if(module==""){
            rows=uiTestCaseService.selectList(productID);
        }else{
            rows=uiTestCaseService.selectListByModuel(module,productID);
        }
        long total=rows.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String create(){
        return "/manage/uitestcase/create";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(UITestCase uiTestCase,HttpServletRequest request,@ModelAttribute("user")User user){
        HttpSession session = request.getSession();
        uiTestCase.setTsproductid(Integer.valueOf((String)session.getAttribute("product")));
        uiTestCase.setTsmodulename((String)session.getAttribute("module"));
        uiTestCase.setTscommon(0);
        uiTestCase.setTscreator(user.getTsname());
        uiTestCaseService.insert(uiTestCase);
        return uiTestCase.getTsuitestcaseid();
    }
    /**
     * @Description:测试用例修改视图
     * */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(UITestCase uiTestCase){
        logger.info(uiTestCase.getTsname());
        int i=uiTestCaseService.updateByPrimaryKeySelective(uiTestCase);
       if(i==1){
           logger.info("更新成功");
           return "success";
       }else {
           logger.info("更新失败");
           return "error";
       }
    }
    /**
     * @Description:获取业务对应的测试用例
     * */
    @RequestMapping(value = "/buscase",method = RequestMethod.GET)
    @ResponseBody
    public Object buscase(String tsbusinessID){
        List<Integer> list=businessCaseService.selectBytsbusinessid(Integer.valueOf(tsbusinessID));
        List<UITestCase> rows=new ArrayList<>();
        for(int i:list){
            rows.add(uiTestCaseService.selectByPrimaryKey(i));
        }
        long total=rows.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    /** 
    * @Description: 删除测试用例
    * @Param: [tsuitestcaseid] 
    * @return: int
    * @Date: 16:23 2018年03月13日
     */ 
    public int delete(String tsuitestcaseid){
        int i=0;
        if(businessCaseService.selectBytsuitestcaseid(Integer.valueOf(tsuitestcaseid)).size()==0){
            i=uiTestCaseService.deleteByPrimaryKey(Integer.valueOf(tsuitestcaseid));
        }
        return i;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    /** 
    * @Description: 传递实体对象到create页面 
    * @Param: [tsuitestcaseid, model] 
    * @return: java.lang.String
    * @Date: 17:20 2018年03月13日
     */ 
    public String edit(@RequestParam("tsuitestcaseid")String tsuitestcaseid,Model model){
        UITestCase uiTestCase=uiTestCaseService.selectByPrimaryKey(Integer.valueOf(tsuitestcaseid));
        model.addAttribute("uiTestCase",uiTestCase);
        return "/manage/uitestcase/create";
    }

}
