package com.controller;

import com.pojo.CaseSteps;
import com.pojo.UITestCase;
import com.pojo.User;
import com.service.BusinessCaseService;
import com.service.CaseStepsService;
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
    @Autowired
    private CaseStepsService caseStepsService;
    /**
     * @Description:ui测试用例主页
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/uitestcase/index";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false)String module,HttpServletRequest request){
        List<UITestCase> rows;
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        if(module==""){
            rows=uiTestCaseService.selectList(user.getTsproductid());
        }else{
            rows=uiTestCaseService.selectListByModuel(module,user.getTsproductid());
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
    /**
     * @Description:添加测试用例
     * @param:[uiTestCase, request, user]:用例实体，获取模块，用户实体
     * @return:int:是否成功
     * @date:2018/3/23 10:10
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public int add(UITestCase uiTestCase,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user=(User)session.getAttribute("user");
        uiTestCase.setTsproductid(user.getTsproductid());
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
    /**
     * @Description: 删除测试用例,同时删除中间表
     * @Param: [tsuitestcaseid]
     * @return: int
     * @Date: 16:23 2018年03月13日
     */
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public int delete(String tsuitestcaseid){
        int i=0;
        if(businessCaseService.selectBytsuitestcaseid(Integer.valueOf(tsuitestcaseid)).size()==0){
            if(uiTestCaseService.deleteByPrimaryKey(Integer.valueOf(tsuitestcaseid))>0){
                i++;
            }
            if(caseStepsService.deleteBytsuitestcaseid(Integer.valueOf(tsuitestcaseid))>0){
                i++;
            }
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
