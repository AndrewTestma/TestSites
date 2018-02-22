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
<div id="main">
    <div id="toolbar">
        <a class="btn btn-default" href="/ui/create"> <i class="glyphicon glyphicon-plus"> </i>添加用例</a>
    </div>
    <div>
        <table id="tctable"></table>
        <select id="moduleSelect" class="selectpicker" data-live-search="true"/>
    </div>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $tctable = $('#tctable');
    var moduleSelect=$('#moduleSelect');
    // bootstrap select初始化
    $('#moduleSelect').append("<option>请选择</option>");
    $(function() {
        $.ajax({
            type:"get",
            url:"/module/selectlist",
            dataType: "json",
            success:function (resMsg) {
                var resultJson = eval(resMsg);
                $.each(resultJson,function (i,module) {
                    $('#moduleSelect').append("<option value=" + module.tsmoduleid + ">" + module.tsame + "</option>");
                })
                /*   $('#moduleSelect').selectpicker('refresh');
                 $('#moduleSelect').selectpicker('render');*/
            }
        });
        // bootstrap table初始化
        $tctable.bootstrapTable({
            url: '/ui/list',
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
            idFile: 'tsuitestcaseid',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsuitestcaseid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsname', title: '用例名称'},
                {field: 'tsgrade', title: '用例等级'},
                {field:'action',title:'测试结果',align:'center',formatter:function (value,row,index) {
                    var id=row.tsuitestcaseid;
                    var returnResult='<a class="update" href="javascript:;"  onclick="updateAction('+id+')" data-toggle="tooltip"  title="详情">详情</a>';
                    return returnResult;
                }, events:'actionEvents',clickToSelect:false},
                {field: 'tscommon', title: '公共用例'},
                {field: 'tscreatetime', title: '创建时间'},
                {field: 'tscreator', title: '创建人'},
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id=row.tsuitestcaseid;
                    var returnValue = '<a class="update" href="javascript:;"  onclick="updateAction('+id+')" data-toggle="tooltip"  title="Edit"><i class="glyphicon glyphicon-edit"></i></a>'
                        +'  <a class="delete" href="javascript:;" onclick="deleteAction('+id+')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ],
            onExpandRow:function (index,row,$detail) {
                initSubTable(index,row,$detail);
            }
        });
    });
    function initSubTable(index,row,$detail) {
        var id=row.tsuitestcaseid;
        var cur_table=$detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/autosteps/list',
            idField: 'tsautostepsid',
            maintainSelected: true,
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
</body>
</html>
