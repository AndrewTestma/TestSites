package com.utils;

import com.pojo.User;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: TestSites
 * @description: 测试监听类
 * @author: Mr.Andrew
 * @create: 2018-03-15 13:29
 **/
public class TestListenerUtil extends TestListenerAdapter {
    public Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    public User user;
    public HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    ExtentReports extent;
    ExtentTest extentTest;

    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        logger.info("测试用例:"+tr.getName()+"---start"+this.hashCode());
      /*  user = (User) request.getSession().getAttribute("user");
        extent=ExtentReportMap.map.get(user);*/
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        logger.info("【" +tr.getMethod().getDescription() + " Failure】");
       /* extentTest.log(LogStatus.FAIL, tr.getThrowable());
        extent.endTest(extentTest);*/

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        logger.info("【" + tr.getMethod().getDescription() + " Skipped】");
       /* extentTest.log(LogStatus.SKIP, "SKIP");
        extent.endTest(extentTest);*/
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        logger.info("【" + tr.getMethod().getDescription() + " Success】");
     /*   //logger.info("参数:"+tr.getParameters()[0]);
        extent.endTest(extentTest);
        extent.flush();*/
    }

    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);
    }
}
