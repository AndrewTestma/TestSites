<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/28 0028
  Time: 13:23
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
    <title>UI测试用例</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
    <div id="OperatingenvDialog" class="crudDialog">
        <form id="operatingenvForm" method="post">
            <div class="form-group">
                <label for="tsname">运行名称</label>
                <input id="tsname" type="text" class="form-control" name="tsname" maxlength="50">
            </div>
            <div class="form-group">
                <label for="tsurl">运行地址</label>
                <input id="tsurl" type="text" class="form-control" name="tsurl" maxlength="50">
            </div>
            <div class="form-group">
                <label for="tsdriver">浏览器</label>
                <select id="tsdriver" name="tsdriver" class="form-control" title="浏览器">
                    <option>InternetExplorerDriver</option>
                    <option>ChromeDriver</option>
                    <option>FirefoxDriver</option>
                </select>
            </div>
            <div class="form-group">
                <label for="tsdirverpath">驱动地址</label>
                <input id="tsdirverpath" type="text" class="form-control" name="tsdirverpath" maxlength="50">
            </div>
            <div class="form-group text-right dialog-buttons">
                <a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
                <a class="waves-effect waves-button" href="javascript:;" onclick="OperatingenvDialog.close();">取消</a>
            </div>
        </form>
    </div>
</body>
    <script>
        function createSubmit() {
            $.ajax({
                type: 'post',
                url: '/env/add',
                data: $('#operatingenvForm').serialize(),
                beforeSend: function() {
                    if ($('#tsname').val() == '') {
                        $('#tsname').focus();
                        return false;
                    }
                    if ($('#tsurl').val() == '') {
                        $('#tsurl').focus();
                        return false;
                    }
                },
                success: function() {
                    OperatingenvDialog.close();
                    $envTable.bootstrapTable('refresh');
                }
            });
        }
    </script>
</html>
