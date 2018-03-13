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
    <title>产品管理</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <a class="waves-effect waves-button" href="javascript:;" onclick="createAction()"><i class="zmdi zmdi-plus"></i> 添加产品</a>
    </div>
    <table id="table"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $table = $('#table');
    $(function() {
        // bootstrap table初始化
        $table.bootstrapTable({
            url: '/product/list',
            height: getHeight(),
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
            detailView: false,
            detailFormatter: 'detailFormatter',
            pagination: true,
            paginationLoop: false,
            sidePagination: 'client',
            silentSort: false,
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            idField: 'tsproductid',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsproductid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsname', title: '产品名称'},
                {field: 'action', title: '模块信息', align: 'center', formatter: function (value, row, index) {
                    var returnModule = ' <a class="waves-effect waves-button" href="javascript:;" onclick="redirectAction()"> 显示详情</a>';
                    return returnModule;
                }, events: 'actionEvents', clickToSelect: false},
                {field: 'tsurl', title: '测试地址'},
                {field: 'tsdburl', title: '数据库地址'},
                {field: 'tsdbname', title: '数据库名称'},
                {field: 'tsusername', title: '登录名'},
                {field: 'tspassword', title: '登录密码'},
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id=row.tsproductid;
                    var returnValue = '<a class="update" href="javascript:;"  onclick="updateAction('+id+')" data-toggle="tooltip"  title="Edit"><i class="glyphicon glyphicon-edit"></i></a>'
                        +'  <a class="delete" href="javascript:;" onclick="deleteAction('+id+')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    var createDialog;
    function createAction() {
        createDialog = $.dialog({
            animationSpeed: 300,
            title: '新增产品',
            content: 'url:/product/create',
            onContentReady: function () {
                initMaterialInput();
            }
        });
    }
    var redirectDialog;
    function redirectAction() {
        redirectDialog = $.dialog({
            animationSpeed: 600,
            width:900,
            title: '模块信息',
            content: 'url:/module/index',
            onContentReady: function () {
                initMaterialInput();
            }
        });
    }
</script>
</body>
</html>