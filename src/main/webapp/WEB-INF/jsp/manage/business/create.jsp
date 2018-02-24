<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/24 0024
  Time: 16:06
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
    <title>新建业务</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div class="container">
    <form class="form-horizontal" method="post" id="testcaseForm">
        <fieldset form="testcaseForm">
            <legend>测试用例</legend>
            <div class="form-group">
                <div class="col-sm-4">
                    <label for="tsname">用例名称</label>
                    <input id="tsname" type="text" class="form-control" name="tsname" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <select id="tsgrade" name="tsgrade" class="form-control selectpicker" title="测试等级">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select>

                    <select id="moduleSelect1" class="form-control selectpicker"></select>

                    <select id="tscommon" name="tsgrade" class="form-control selectpicker" title="公共用例">
                        <option>是</option>
                        <option>否</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <label for="tscreator" >创建人</label>
                    <input id="tscreator" type="text" class="form-control" name="tscreator" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <button id="addtc" onclick="adduitestcase()" type="button" class="btn btn-primary" title="添加"><i class="glyphicon glyphicon-plus"></i> 新增用例</button>
                    <button id="updatetc" style="display: none" onclick="updatetc()" type="button" class="btn btn-primary" title="添加"><i class="glyphicon glyphicon-plus"></i> 修改用例</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
</html>
