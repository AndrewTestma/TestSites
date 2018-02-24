<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/24 0024
  Time: 15:12
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
    <title>业务管理</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
    <div id="main">
        <div id="toolbar">
            <a class="btn btn-default" href="/bus/create"> <i class="glyphicon glyphicon-plus"> </i>添加业务</a>
        </div>
        <div>
            <table id="businessTable"></table>
        </div>
    </div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $businessTable=$('#businessTable');
    $(function () {
        $businessTable.bootstrapTable({
            url: '/bus/list',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: true,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'client',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idFile: 'tsbusinessid ',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsbusinessid ', title: '编号', sortable: true, align: 'center'},
                {
                    field:'tsname',
                    title:'业务名称',
                    editable:{
                        type:'text',
                        title:'业务名称',
                        validate:function (v) {
                            if(!v) return '业务名称不能为空'
                        }
                    }
                }
            ]
        })
    })
</script>
</html>
