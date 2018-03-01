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
    <title>操作步骤</title>
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
        <fieldset>
            <legend>操作步骤</legend>
            <div id="toolbar" class="btn-group">
               <%-- <a class="waves-effect waves-button" href="javascript:;" onclick="adduitestcase(),addAutoSteps()">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                </a>--%>
                   <button id="addat" onclick="addAutoSteps()" class="btn btn-default" title="添加">
                       <i class="glyphicon glyphicon-plus"></i> 添加步骤
                   </button>
            </div>
            <div>
                <form class="form-horizontal" style="display: none" method="post" id="autostepsForm">
                <fieldset>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tsautostepsname">步骤名称</label>
                            <input id="tsautostepsname" type="text" class="form-control" name="tsautostepsname" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <select id="tsselecttype" name="tsselecttype" class="form-control selectpicker" title="查找方式">
                                <option>id</option>
                                <option>name</option>
                                <option>xpath</option>
                                <option>linkText</option>
                                <option>className</option>
                                <option>cssSelector</option>
                                <option>partialLinkText</option>
                                <option>tagName</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tsselectcontent">查找内容</label>
                            <input id="tsselectcontent" type="text" class="form-control" name="tsselectcontent" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <select id="tsactiontype" name="tsactiontype" class="form-control selectpicker" title="执行方式">
                                <option>单击</option>
                                <option>输入</option>
                                <option>双击</option>
                                <option>悬浮</option>
                                <option>拖拽</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tsactioncontent">执行内容</label>
                            <input id="tsactioncontent" type="text" class="form-control" name="tsactioncontent" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tsframepath">FramePath</label>
                            <input id="tsframepath" type="text" class="form-control" name="tsframepath" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tswait">等待时间</label>
                            <input id="tswait" type="text" class="form-control" name="tswait" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label>验证：</label>
                            <div class="btn-group" data-toggle="buttons">
                                <label  class="btn btn-info" name="options" onclick="verification(1)">是</label>
                                <label  class="btn btn-info" name="options" onclick="verification(2)">否</label>
                            </div>
                        </div>
                    </div>
                    <div style="display: none" id="tsverification">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <select id="tsverificationtype" name="tsverificationtype" class="form-control selectpicker" title="验证方式">
                                <option>文本验证</option>
                                <option>url验证</option>
                                <option>数据库验证</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tsverificationcontent">验证内容</label>
                            <input id="tsverificationcontent" type="text" class="form-control" name="tsverificationcontent" maxlength="50">
                        </div>
                    </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tsremarks">备注</label>
                            <input id="tsremarks" type="text" class="form-control" name="tsremarks" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <label for="tscreator1">创建人</label>
                            <input id="tscreator1" type="text" class="form-control" name="tscreator" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                        <a class="waves-effect waves-button" href="javascript:;" onclick="createAutoSteps();">保存</a>
                        <a class="waves-effect waves-button" href="javascript:;" onclick="addAutoStepsDialog.close();">取消</a>
                    </div>
                </fieldset>
            </form>
            </div>
            <div>
                <table id="autostepstable" style="display: none"></table>
            </div>
        </fieldset>
    </div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var addAutoStepsDialog;
    var $autostepstable=$('#autostepstable');
    function addAutoSteps() {
    /*    addAutoStepsDialog = $.dialog({
            animationSpeed: 300,
            title: '新建步骤',
            content: 'url:/autosteps/create',
            onContentReady: function () {
                initMaterialInput();
            }
        });*/
        document.getElementById("autostepsForm").style.display="inline";
    }
    //动态加载模块
    $('#moduleSelect1').append("<option>选择模块</option>");
    $(function() {
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
                    $('#moduleSelect1').append("<option value=" + module.tsame + ">" + module.tsame + "</option>");
                })
                $('#moduleSelect1').selectpicker('refresh');
                $('#moduleSelect1').selectpicker('render');
            }
        });
    });
    //bootstrap select 选择值后触发事件
    $("#moduleSelect1").on('changed.bs.select',function (e) {
        $.ajax({
            type:"post",
            url:"/module/save",
            data:{"module":$("#moduleSelect1").selectpicker('val')},
            success:function (data) {
            },
            error:function (data) {
            }
        })
    })

    //点击添加按钮触发保存测试用例
    var uitestcaseID;
    function adduitestcase() {
        $.ajax({
            type: 'post',
            url: '/ui/add',
            data: $('#testcaseForm').serialize(),
            beforeSend: function() {
                if ($('#tsname').val() == '') {
                    $('#tsname').focus();
                    return false;
                }
            },
            success: function(data) {
                if(data>0){
                    uitestcaseID=data;
                    document.getElementById("addtc").style.display="none";
                    document.getElementById("updatetc").style.display="inline";
                }
            },
            error:function (data) {
            }
        });
    }
    //点击添加按钮触发保存操作步骤
    var autoStespID;
    function createAutoSteps() {
        $.ajax({
            type: 'post',
            url: '/autosteps/add',
            data: $('#autostepsForm').serialize(),
            beforeSend: function() {
            },
            success: function(data) {
                if(data>0){
                    //addAutoStepsDialog.close();
                    autoStespID=data;
                    document.getElementById("autostepsForm").style.display="none";
                    document.getElementById("autostepstable").style.display="inline";
                    autostepstable();
                    addCaseSteps();

                }
            },
            error:function (data) {
            }
        });
    }
    //添加测试用例与操作步骤中间表
    function addCaseSteps() {
        $.ajax({
            type:'post',
            url:'/casesteps/add',
            data:{'uitestcaseID':uitestcaseID,'autostepsID':autoStespID},
            success:function (data) {
                if(data==1){
                    alert("添加成功");
                }
            }
        })
    }
    function autostepstable() {
        // bootstrap table初始化
        $autostepstable.bootstrapTable({
            url: '/autosteps/tcstep',
            method:'get',
            queryParams:{uitestcaseID:uitestcaseID},
            idField: 'tsautostepsid',
            striped: true,
            search: true,
            showRefresh: true,
            showColumns: true,
            minimumCountColumns: 2,
            clickToSelect: true,
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
                    var id = row.tsproductid;
                    var returnValue = '<a class="update" href="javascript:;"  onclick="updateAction(' + id + ')" data-toggle="tooltip"  title="Edit"><i class="glyphicon glyphicon-edit"></i></a>'
                        + '  <a class="delete" href="javascript:;" onclick="deleteAction(' + id + ')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false
                }
            ]
        });
    }
    //是否需要验证
    function verification(e) {
        if(e==1){
            document.getElementById('tsverification').style.display='inline';
        }else{
            document.getElementById('tsverification').style.display='none';
        }
    }
</script>
</html>
