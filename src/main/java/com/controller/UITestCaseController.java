package com.controller;

import com.pojo.UITestCase;
import com.service.UITestCaseService;
import com.utils.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.testng.TestNG;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/21 0021
 */
@Controller("UITestCaseController")
@RequestMapping("/ui")
public class UITestCaseController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource(name = "UITestCaseService")
    private UITestCaseService uiTestCaseService;

    /**
     * @Description:ui测试用例主页
     * */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "/manage/uitestcase/index";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Object list(
            @RequestParam(required = false, defaultValue = "0", value = "offset") int offset,
            @RequestParam(required = false, defaultValue = "10", value = "limit") int limit,
            @RequestParam(required = false, defaultValue = "", value = "search") String search,
            @RequestParam(required = false, value = "sort") String sort,
            @RequestParam(required = false, value = "order") String order){
        List<UITestCase> rows=uiTestCaseService.selectList(offset,limit);
        long total=rows.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public int create(UITestCase uiTestCase){
        return uiTestCaseService.insert(uiTestCase);
    }
   /* *//**
     * @Description:获取测试用例列表
     * *//*
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    private String list(Model model){
        List<UITestCase> list=uiTestCaseService.selectByPrimaryKeyAll(0,10);
        model.addAttribute("list",list);
        return "uitestcase/list";
    }
    *//**
     * @Description:用例添加页面
     * *//*
    @RequestMapping(value = "/addpage",method = RequestMethod.GET)
    public String addpage(){
        return "uitestcase/addpage";
    }
    *//**
     * @Description:添加用例方法
     * *//*
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ModelAndView add(UITestCase uiTestCase){
        logger.debug(uiTestCase.toString());
        if(uiTestCase !=null){
            logger.info("添加 测试用例"+uiTestCase.getTsnum());
            uiTestCaseService.insert(uiTestCase);
        }
        return new ModelAndView("redirect:/ui/list");
    }
    *//**
     * @Description:调试用例
     * *//*
    @RequestMapping(value = "/debugging",method = RequestMethod.POST)
    public ModelAndView debugging(@RequestParam("btnid") String tsuitestcaseid){
        logger.info("测试用例ID-----"+tsuitestcaseid);
        frontCase(tsuitestcaseid);
        TestNG testNG=new TestNG();
        testNG.setTestClasses(new Class[]{new BaseTest().getClass()});
        testNG.run();
        return new ModelAndView("redirect:/ui/list");
    }
    *//**
     * @Description：获取测试用例的操作步骤
     * @param tsuitestcaseid:测试用例ID
     * @return
     * *//*
    public void frontCase(String  tsuitestcaseid){
        Integer id=Integer.valueOf(tsuitestcaseid);
        uiTestCase=uiTestCaseService.selectByPrimaryKey(id);
        List<Autosteps> autosteps=autoStep(uiTestCase);
        list.put(uiTestCase.getTsnum(), autosteps);
        if(!uiTestCase.getTsfrontcase().equals("")) {
            String id1=String.valueOf(uiTestCase.getTsuitestcaseid());
            frontCase(id1);
        }
    }
    *//**
     * @Description:获取操作步骤
     * @param uiTestCase:测试用例对象
     * *//*
    public List<Autosteps> autoStep(UITestCase uiTestCase){
        List<Autosteps> autosteps=new ArrayList<>();
        String[] strings=uiTestCase.getTsautostepsname().split(",");
        Autosteps autosteps1;
        for(int i=0;i<strings.length;i++){
            autosteps1=autostepsService.selectByName(strings[i]);
            autosteps.add(autosteps1);
        }
        return autosteps;
    }*/
}
