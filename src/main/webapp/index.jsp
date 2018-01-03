<%@ page import="java.util.Date" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath=request.getContextPath();%>
<html>
<head>
    <meta charset="utf-8">
</head>
<body>
<h2>TestSites</h2>
运行Demo
<br />
日期: <%new Date().toLocaleString();%>
<br /><br /><br /><br />
<%--产品列表：<a href="<%=appPath%>/tsproduct/productlist">点击前往</a>--%>
用例：<a href="<%=appPath%>/ui/list">点击前往</a>
</body>
</html>
