package com.controller;

import com.pojo.UITestCase;
import com.service.UICaseStepService;
import com.service.UITestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Andrew
 * @Description:
 * @Date 2017/12/21 0021
 */
@Controller("UITestCaseController")
@RequestMapping("/ui")
public class UITestCaseController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource(name="UITestCaseService")
    private UITestCaseService uiTestCaseService;

    /**
     * @Description:获取测试用例列表
     * */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    private String list(Model model){
        List<UITestCase> list=uiTestCaseService.selectByPrimaryKeyAll(0,10);
        model.addAttribute("list",list);
        return "uitestcase/list";
    }
    /**
     * @Description:用例添加页面
     * */
    @RequestMapping(value = "/addpage",method = RequestMethod.GET)
    public String addpage(){
        return "uitestcase/addpage";
    }
    /**
     * @Description:添加用例方法
     * */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ModelAndView add(UITestCase uiTestCase){
        logger.debug(uiTestCase.toString());
        if(uiTestCase !=null){
            logger.info("添加 测试用例"+uiTestCase.getTsnum());
            uiTestCaseService.insert(uiTestCase);
        }
        return new ModelAndView("redirect:/ui/list");
    }
    /**
     * @Description:调试用例
     * */

    /**
     * @Description:递归获取前置条件
     * */
    public List<String> frontCase(String  tsuitestcaseid){
     /*   int id=Integer.valueOf(tsuitestcaseid);
        List<String> list=new ArrayList<String>();
        uiCaseStepService.selectByPrimaryKey(id);*/
        return null;
    }
}
