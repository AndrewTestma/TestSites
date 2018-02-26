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
    <form class="form-horizontal" method="post" id="busForm">
        <fieldset form="busForm">
            <legend>业务</legend>
            <div class="form-group">
                <div class="col-sm-4">
                    <label for="tsname">业务名称</label>
                    <input id="tsname" type="text" class="form-control" name="tsname" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <button id="addtc" onclick="addbusiness()" type="button" class="btn btn-primary" title="添加"><i class="glyphicon glyphicon-plus"></i> 新增业务</button>
                    <button id="updatetc" style="display: none" onclick="updatetc()" type="button" class="btn btn-primary" title="添加"><i class="glyphicon glyphicon-plus"></i> 修改业务</button>
                </div>
            </div>
        </fieldset>
    </form>
    <fieldset>
        <legend>选择测试用例</legend>
        <table id="tctable"></table>
    </fieldset>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>

    //点击新增业务
    var busID;
    function addbusiness() {
        $.ajax({
            type:'post',
            url:'/bus/add',
            data:$('#busForm').serialize(),
            success:function (data) {
                if(data>0){
                    busID=data;
                    alert(busID);
                }else{
                    alert("添加失败")
                }
            },
            error:function () {
                alert("操作出错")
            }
        })
    }
    //测试用例
    var $tctable = $('#tctable');
    $(function () {
        // bootstrap table初始化
        $tctable.bootstrapTable({
            url: '/ui/list',
            striped: true,
            search: true,
            showRefresh: false,
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
            idFile: 'tsuitestcaseid',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsuitestcaseid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsname', title: '用例名称'},
                {field: 'tsgrade', title: '用例等级'},
                {field: 'tsmodulename', title: '模块',sortable: true},
                {field: 'tscommon', title: '公共用例'},
                {field: 'tscreatetime', title: '创建时间'},
                {field: 'tscreator', title: '创建人'},
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id=row.tsuitestcaseid;
                    var returnValue = '<a  class="update" href="javascript:;"  onclick="InsertBus('+id+')" data-toggle="tooltip"  title="添加"><i class="glyphicon glyphicon-plus"></i></a>'
                        +'  <a  style="display: none" class="delete" href="javascript:;" onclick="" data-toggle="tooltip" title="取消"><i class="glyphicon glyphicon-minus"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ]
        });
    })
    function InsertBus(id) {
        $.ajax({
            type:'post',
            url:'/buscase/add',
            data:{'tsbusinessid':busID,'tsuitestcaseid':id},
            success:function (data) {
                if(data>0){
                    alert("添加成功");
                }
            }
        })
    }
</script>
</html>
