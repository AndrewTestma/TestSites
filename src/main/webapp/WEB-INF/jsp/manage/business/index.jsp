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
        <a class=" btn btn-default" href="/bus/create"> <i class="glyphicon glyphicon-plus"> </i>添加业务</a>
    </div>

    <table id="businessTable"></table>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $businessTable=$('#businessTable');
    var tsbusinessid;
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
            idFile: 'tsbusinessid',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsbusinessid', title: '编号', sortable: true, align: 'center'},
                {field:'tsname', title:'业务名称', align: 'center'},
                {field: 'action', title: '报告', align: 'center', formatter: function (value, row, index) {
                        tsbusinessid = row.tsbusinessid;
                        var returnModule = ' <a href="/resources/result/'+tsbusinessid+'.html" target="_blank"> 显示详情</a>';
                        return returnModule;
                    }, events: 'actionEvents', clickToSelect: false},
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                        var id=row.tsbusinessid;
                        var pid=row.tsproductid;
                        var returnValue = '<a class="btn btn-default" href="javascript:;"  onclick="execBusiness('+id+','+pid+')" data-toggle="tooltip"  title="Start"><i class="glyphicon glyphicon-play-circle"></i></a>'
                            +'  <a class="btn btn-default" href="/bus/edit?tsbusinessid='+id+'"  data-toggle="tooltip"  title="Edit"><i class="glyphicon glyphicon-pencil"></i></a>'
                            +'  <a class="btn btn-default" href="javascript:;" onclick="deleteAction('+id+')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                        return returnValue;
                    }, events: 'actionEvents', clickToSelect: false}
            ],
            onExpandRow:function (index,row,$detail) {
                businitSubTable(index,row,$detail);
            }
        })
    })
    function businitSubTable(index,row,$detail) {
        var id=row.tsbusinessid;
        var cur_table=$detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/ui/buscase',
            method:'get',
            queryParams:{tsbusinessID:id},
            idField: 'tsuitestcaseid',
            columns: [
                {field: 'tsuitestcaseid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsname', title: '用例名称',},
                {field: 'tsgrade', title: '用例等级'},
                {field: 'tscommon', title: '公共用例'},
                {field: 'tscreatetime', title: '创建时间'},
                {field: 'tscreator', title: '创建人'}
            ]
        })
    }
    //执行测试动作
    function execBusiness(id,pid) {
        $.ajax({
            type:'get',
            url:'/exec/business',
            data:{"tsbusinessid":id,"tsproductid":pid},
            success:function (data) {
            }
        })
    }
    //删除业务
    function deleteAction(id) {
        $.ajax({
            type:'get',
            url:'/bus/del',
            data:{"tsbusinessid":id},
            success:function (data) {
                if(data>0){
                    var opt={
                        url: '/ui/buscase',
                    };
                    $tctable.bootstrapTable('refresh',opt);
                }
            }
        })
    }
</script>
</html>
