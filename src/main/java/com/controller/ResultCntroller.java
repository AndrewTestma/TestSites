package com.controller;

import com.pojo.LogInfo;
import com.pojo.Result;
import com.pojo.User;
import com.service.ResultService;
import com.utils.ExtentReportMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: TestSites
 * @description: 报告控制器
 * @author: Mr.Andrew
 * @create: 2018-03-06 10:01
 **/
@Controller
@RequestMapping("/result")
public class ResultCntroller {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ResultService resultService;

    /**
     * @Description:返回报告首页
     * @return: java.lang.String
     * @Date: 10:04 2018年03月06日
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(@RequestParam("tsbusinessid") String tsbusinessid, HttpServletRequest request){
        request.setAttribute("tsbusinessid",tsbusinessid);
        return "/manage/result/index";
    }

    @RequestMapping(value = "/log",method = RequestMethod.GET)
    @ResponseBody
    public LogInfo log( HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        LogInfo logInfo=ExtentReportMap.log.get(user.getTsuserid());
        return logInfo;
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public Object info(@RequestParam("tsbusinessid") String tsbusinessid){
        List<Result> rows=new ArrayList<>();
        rows.add(resultService.selectBybusinessid(Integer.valueOf(tsbusinessid)));
        long total=rows.size();
        Map<String,Object> result=new HashMap<>();
        result.put("data",rows);
        result.put("total",total);
        return result;
    }
}
