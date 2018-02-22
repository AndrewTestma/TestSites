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
<div class="container">
    <form class="form-horizontal" method="post" id="autostepsForm">
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
            <div class="form-group">
                <div class="col-sm-4">
                    <label for="tsremarks">备注</label>
                    <input id="tsremarks" type="text" class="form-control" name="tsremarks" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-4">
                    <label for="tscreator">创建人</label>
                    <input id="tscreator" type="text" class="form-control" name="tscreator" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <a class="waves-effect waves-button" href="javascript:;" onclick="createAutoSteps();">保存</a>
                <a class="waves-effect waves-button" href="javascript:;" onclick="addAutoStepsDialog.close();">取消</a>
            </div>
        </fieldset>
    </form>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    //点击保存操作步骤
    function createAutoSteps() {
        $.ajax({
            type: 'post',
            url: '/autosteps/add',
            data: $('#autostepsForm').serialize(),
            beforeSend: function() {
            },
            success: function(data) {
                if(data==1){
                    addAutoStepsDialog.close();
                    autostepstable();
                }
            },
            error:function (data) {
            }
        });
    }
    function autostepstable() {
        // bootstrap table初始化
        $autostepstable.bootstrapTable({
            url: '/autosteps/list',
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
</script>
</html>
