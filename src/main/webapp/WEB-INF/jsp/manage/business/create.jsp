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
<!DOCTYPE HTML>
<html lang="zh-cn">
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
                    <input id="tsname" type="text" class="form-control" name="tsname" value="${business.tsname}" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <a id="addbus" onclick="addbusiness()" class="btn btn-primary" title="添加"><i class="glyphicon glyphicon-plus"></i> 新增业务</a>
                    <a id="updatebus" style="display: none" onclick="updatebusiness()" class="btn btn-primary" title="添加"><i class="glyphicon glyphicon-plus"></i> 修改业务</a>
                </div>
            </div>
        </fieldset>
    </form>
    <fieldset>
        <legend>选择测试用例</legend>
        <div id="toolbar" class="form-group">
            <div id="select_div" class="form-group">
                <div class="col-sm-4">
                    <select id="module" class="btn btn-default moduleselect" data-url="/module/selectlist" data-first-title="请选择模块"
                            data-first-value="" data-json-name="tsame" data-json-value="tsame" onchange="onChangeModule(this.options[this.options.selectedIndex].value)"></select>
                </div>
            </div>
            <div id="insert_div" class="form-group" style="display: none">
                <div class="col-sm-4">
                    <a class="btn btn-default" href="#" onclick=""> <i class="glyphicon glyphicon-plus"> </i>添加用例</a>
                </div>
            </div>
        </div>
        <table id="tctable"></table>
    </fieldset>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var ModuleStr="";//更改模块
    var busID;
    var $tctable = $('#tctable');//测试用例表
    var url;
    var queryParams;
    var type;
   //页面初始化
    $(function () {
        queryParams={module:ModuleStr};
        //cxselect 数据加载
        $('#select_div').cxSelect({
            selects: ['moduleselect'],  // 数组，请注意顺序
        });
        if('${business}'!=""){
            url='/ui/buscase';
            queryParams={tsbusinessID:'${business.tsbusinessid}'};
            type=1;
            document.getElementById("select_div").style.display="none";
            document.getElementById("insert_div").style.display="inline";
            document.getElementById("addbus").style.display="none";
            document.getElementById("updatebus").style.display="inline";
        }
        tableData(url,queryParams,type);
    })
    //点击新增业务
    function addbusiness() {
        $.ajax({
            type:'post',
            url:'/bus/add',
            data:$('#busForm').serialize(),
            success:function (data) {
                if(data>0){
                    busID=data;
                    document.getElementById("addbus").style.display="none";
                    document.getElementById("updatebus").style.display="inline";
                }
            },
            error:function () {
                alert("操作出错")
            }
        })
    }
    //修改业务
    function updatebusiness() {
        
    }
    //table 加载数据
    function tableData(url,queryParams,type) {
        $tctable.bootstrapTable('destroy');//销毁table，重新加载
        $tctable.bootstrapTable({
            url: url,
            height: getHeight(),
            queryParams:queryParams,
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
                {field:'tscommon',title:'公共步骤',align:'center',formatter:function (value,row,index) {
                    var value=row.tscommon;
                    var returnValue;
                    if(value==1){
                        returnValue='<i class="glyphicon glyphicon-star"></i>'
                    }else {
                        returnValue='<i class="glyphicon glyphicon-star-empty"></i>'
                    }
                    return returnValue;
                    },events: 'actionEvents', clickToSelect: false},
                {field: 'tscreatetime', title: '创建时间'},
                {field: 'tscreator', title: '创建人'},
                {
                    field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id = row.tsuitestcaseid;
                    var addStr='inline';
                    var delStr='none';
                    if(type!=0){
                        addStr = 'none';
                        delStr = "inline";
                    }
                    var returnValue =' <a id="' + id + '" style="display: '+addStr+'" class="btn btn-default"  href="javascript:;"  onclick="InsertBus(' + id + ')" data-toggle="tooltip"  title="添加"><i class="glyphicon glyphicon-plus"></i></a>'
                        + '  <a  id="' + id + '1" style="display: '+delStr+'"  class="btn btn-default"  href="javascript:;" onclick="DelBus(' + id + ')" data-toggle="tooltip" title="取消"><i class="glyphicon glyphicon-minus"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ]
        });
    }
    //增加业务线与测试用例中间表数据
    function InsertBus(id) {
        var addid = id;
        var delid = id + "1";
        $.ajax({
            type:'post',
            url:'/buscase/add',
            data:{'tsbusinessid':busID,'tsuitestcaseid':id},
            success:function (data) {
                if(data>0){
                    document.getElementById(delid).style.display = "inline";
                    document.getElementById(addid).style.display = "none";
                }
            }
        })
    }
    //删除业务线与测试用例中间表数据
    function DelBus(id) {
        var addid = id;
        var delid = id + "1";
        $.ajax({
            type:'post',
            url:'/buscase/del',
            data:{'tsbusinessid':'${business.tsbusinessid}','tsuitestcaseid':id},
            success:function (data) {
                if(data>0){
                    document.getElementById(delid).style.display = "none";
                    document.getElementById(addid).style.display = "inline";
                }
            }
        })
    }
    //更改模块触发事件
    function onChangeModule(module) {
        ModuleStr=module;
        url='/ui/list';
        queryParams={module:ModuleStr};
        type=0;
        tableData(url,queryParams,type);
    }
</script>
</html>
