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
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>报告详情</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <table id="resultTable"></table>
    </div>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $resultTable=$('#resultTable');
   $(function () {
       var tsbusinessid = '${tsbusinessid}';
       $resultTable.bootstrapTable({
           url: '/result/info?tsbusinessid='+tsbusinessid,
           height: getHeight(),
           columns: [
               {field: 'tsresultid', title: '编号',  align: 'center'},
               {field: 'tstotaltime', title: '执行时长',align: 'center'},
               {field: 'tstotalsteps', title: '总步骤数',  align: 'center'},
               {field: 'tsrunsteps', title: '执行步骤数',  align: 'center'},
               {field: 'tsresult', title: '执行结果',  align: 'center'},
               {field: 'tscount', title: '执行次数',  align: 'center'},
               {field: 'tsexecutive', title: '执行人',  align: 'center'},
               {field: 'tsexecutiontime', title: '执行时间',  align: 'center'}
           ]
       })
   })
</script>
</html>
