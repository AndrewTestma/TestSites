<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/6 0006
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>报告详情</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div>
    <legend>操作日志<a onclick="history.go(-1);" style="float: right"><img src="${basePath}/resources/images/return.png"/></a></legend>
    <div id="log">
    </div>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var init;
    $(function () {
        init = self.setInterval("log()",1000);
    })
    function log () {
      $.ajax({
           type:'get',
           url: "/result/log",
           dataType:'json',
           success:function (data) {
                   var content=data.content;
                   var html = [];
                   var txt1="<pre>"+content+"</pre>";
                   html.push(txt1);
                   $('#log').html(html.join(''));
                   if(content.indexOf("END")>0){
                       clearInterval(init);
                   }
           }
       })
    }
</script>
</html>
