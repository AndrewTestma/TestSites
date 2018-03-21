<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%--<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>--%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>操作步骤</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="span6">
            <ul class="breadcrumb">
                <li>
                    <a href="/ui/index">UI用例</a> <span class="divider">></span>
                </li>
                <li class="active">新建用例</li>
            </ul>
        </div>
    </div>
    <div class="myform">
        <form class="form-horizontal" method="post" id="testcaseForm">
            <fieldset form="testcaseForm">
                <legend>测试用例</legend>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tsname">用例名称:</label>
                        <input id="tsuitestcaseid"  type="hidden" class="form-control" name="tsuitestcaseid" value="${uiTestCase.tsuitestcaseid}" maxlength="50">
                        <input id="tsname" type="text" class="form-control" name="tsname" value="${uiTestCase.tsname}"
                               maxlength="50">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tsgrade">测试等级:</label>
                        <select id="tsgrade" name="tsgrade" class="form-control">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tsmodulename">选择模块:</label>
                        <select id="tsmodulename" name="tsmodulename" class="form-control selectpicker"></select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tscommon">公共用例:</label>
                        <select id="tscommon" name="tscommon" class="form-control">
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <a id="addtc" onclick="adduitestcase()"  class="btn btn-primary" title="添加"><i
                                class="glyphicon glyphicon-plus"></i> 新增用例
                        </a>
                        <a id="updatetc" style="display: none"  onclick="updatetcAction()" class="btn btn-primary" title="修改">
                            <i class="glyphicon glyphicon-plus"></i> 修改用例
                        </a>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
    <fieldset>
        <legend>操作步骤</legend>
        <div id="toolbar" class="btn-group">
            <button id="addat" onclick="addAutoSteps(0)" class="btn btn-default" title="添加">
                <i class="glyphicon glyphicon-plus"></i> 新建步骤
            </button>
            <button id="reuse" onclick="Reuse(1)" class="btn btn-default" title="添加">
                <i class="glyphicon glyphicon-plus"></i> 复用步骤
            </button>
        </div>
        <div id="toolbars">
        </div>
        <table id="autostepstable" style="display: none"></table>
    </fieldset>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var uitestcaseID;//测试用例ID
    var addAutoStepsDialog;//新建步骤dialog
    var $autostepstable = $('#autostepstable');//步骤列表
    //打开新建步骤dialog
    function addAutoSteps() {
        addAutoStepsDialog = $.dialog({
            title: '新建步骤',
            content: 'url:/autosteps/create',
            onContentReady: function () {
                initMaterialInput();
            }
        });
    }
    //动态加载模块
    $('#tsmodulename').append("<option>选择模块</option>");
    $(function () {
        $('.selectpicker').selectpicker({
            size: 40
        });
        $.ajax({
            type: "get",
            url: "/module/selectlist",
            dataType: "json",
            success: function (resMsg) {
                var resultJson = eval(resMsg);
                $.each(resultJson, function (i, module) {
                    var optionStr = "<option value='" + module.tsame + "' ";
                    if ('${uiTestCase.tsmodulename}' == module.tsame) {
                        optionStr += " selected "
                    }
                    optionStr += ">" + module.tsame + "</option>";
                    $('#tsmodulename').append(optionStr);
                })
                $('#tsmodulename').selectpicker('refresh');
                $('#tsmodulename').selectpicker('render');
            }
        });
        if ('${uiTestCase.tsuitestcaseid}'!=""){
             uitestcaseID='${uiTestCase.tsuitestcaseid}';
            document.getElementById("updatetc").style.display = "inline";
            document.getElementById("addtc").style.display = "none";
            $('#tsgrade').val('${uiTestCase.tsgrade}');
            $('#tscommon').val('${uiTestCase.tscommon}');
            autostepstable(0);
        }
    });
    //bootstrap select 选择值后触发事件
    $("#tsmodulename").on('changed.bs.select', function (e) {
        $.ajax({
            type: "post",
            url: "/module/save",
            data: {"module": $("#tsmodulename").selectpicker('val')},
            success: function (data) {
            },
            error: function (data) {
            }
        })
    })

    //点击添加按钮触发保存测试用例
    function adduitestcase() {
        $.ajax({
            type: 'post',
            url: '/ui/add',
            data: $('#testcaseForm').serialize(),
            beforeSend: function () {
                if ($('#tsname').val() == '') {
                    $('#tsname').focus();
                    return false;
                }
            },
            success: function (data) {
                if (data > 0) {
                    uitestcaseID = data;
                    document.getElementById("addtc").style.display = "none";
                    document.getElementById("updatetc").style.display = "inline";
                }
            },
            error: function (data) {
            }
        });
    }
    //修改测试用例
    function updatetcAction() {
        $.ajax({
            type:'post',
            url:'/ui/update',
            data:$('#testcaseForm').serialize(),
            success:function (data) {
                if(data=="success"){
                    alert("更新成功");
                }else{
                    alert("修改失败");
                }
            }
        })
    }
    //点击复用按钮
    function Reuse() {
        autostepstable(1);
    }
    //table数据加载
    function autostepstable(stepType) {
        document.getElementById("autostepstable").style.display = "table";
        $autostepstable.bootstrapTable('destroy');
        // bootstrap table初始化
        var url;
        var queryParams;
        if (stepType == 0) {
            url = '/autosteps/tcstep';
            queryParams = {uitestcaseID: uitestcaseID};
        } else {
            url = '/autosteps/listByModule';
            queryParams = {moduleName: $('#tsmodulename').selectpicker('val')};
        }
        $autostepstable.bootstrapTable({
            url: url,
            height: getHeight(),
            method: 'get',
            queryParams: queryParams,
            idField: 'tsautostepsid',
            striped: true,
            showRefresh: true,
            showColumns: true,
            pagination: true,
            paginationLoop: false,
            sidePagination: 'client',
            smartDisplay: false,
            escape: true,
            searchOnEnterKey: true,
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsautostepsid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsautostepsname', title: '步骤名称'},
                {field: 'tsselecttype', title: '查找方式'},
                {field: 'tsselectcontent', title: '查找内容'},
                {field: 'tsactiontype', title: '执行方式'},
                {field: 'tsactioncontent', title: '执行内容'},
                {field: 'tsframepath', title: 'FramePath'},
                {field: 'tswait', title: '等待时间'},
                {field: 'tsverificationtype', title: '验证方式'},
                {field: 'tsverificationcontent', title: '验证内容'},
                {field: 'tsremarks', title: '备注'},
                {field: 'tscreator', title: '创建人'},
                {
                    field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id = row.tsautostepsid;
                    var addStr='inline';
                    var delStr='none';
                    if(stepType==0){
                        addStr = 'none';
                        delStr = "inline";
                    }
                    var returnValue =' <a id="' + id + '" class="btn btn-default" style="display: '+addStr+'"  href="javascript:;"  onclick="InsertCaseSteps(' + id + ')" data-toggle="tooltip"  title="添加"><i class="glyphicon glyphicon-plus"></i></a>'
                    + '  <a  id="' + id + '1" class="btn btn-default" style="display: '+delStr+'"   href="javascript:;" onclick="DelCaseSteps(' + id + ')" data-toggle="tooltip" title="取消"><i class="glyphicon glyphicon-minus"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false
                }
            ]
        });
    }
    //添加中间表关联
    function InsertCaseSteps(id) {
        var addid = id;
        var delid = id + "1";
        $.ajax({
            type: 'post',
            url: '/casesteps/add',
            data: {'uitestcaseID': uitestcaseID, 'autostepsID': id},
            success: function (data) {
                if (data > 0) {
                    document.getElementById(delid).style.display = "inline";
                    document.getElementById(addid).style.display = "none";
                }
            }
        })
    }
    //删除中间表关联
    function DelCaseSteps(id) {
        var addid = id;
        var delid = id + "1";
        $.ajax({
            type: 'post',
            url: '/casesteps/del',
            data: {'uitestcaseID': uitestcaseID, 'autostepsID': id},
            success: function (data) {
                if (data > 0) {
                    document.getElementById(addid).style.display = "inline";
                    document.getElementById(delid).style.display = "none";
                }
            }
        })
    }
</script>
</html>
