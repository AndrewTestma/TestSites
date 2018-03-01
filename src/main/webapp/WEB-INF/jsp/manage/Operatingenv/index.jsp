<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/28 0028
  Time: 11:13
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
    <title>运行设置</title>
    <jsp:include page="/resources/inc/head.jsp" flush="true"/>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <a class="btn btn-default" onclick="addOperatingenv()"> <i class="glyphicon glyphicon-plus"> </i>添加运行环境</a>
    </div>
    <div>
        <table id="envTable"></table>
    </div>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp"/>
<script>
    var $envTable=$('#envTable');
    $(function () {
        $envTable.bootstrapTable({
            url: '/env/list',
            height: getHeight(),
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
            idFile: 'tsoperatingenvid',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns:[
                {field: 'tsoperatingenvid', title: '编号', sortable: true, align: 'center'},
                {
                    field: 'tsname', title: '名称',
                    editable:{
                        type:'text',
                        title:'名称',
                        validate:function (v) {
                            if(!v) return '用例名不能为空';
                        }
                    }
                },
                {
                    field: 'tsurl',
                    title: '运行IP',
                    editable:{
                        type:'text',
                        title:'运行IP',
                        validate:function (v) {
                            if(!v) return '运行IP不能为空';
                        }
                    }
                },
                {
                    field: 'tsdriver',
                    title: '启动浏览器',
                    editable:{
                        type:'select',
                        title:'启动浏览器',
                        source:[
                            {value:'IE',text:'IE'},
                            {value:'Chrome',text:'Chrome'},
                            {value:'Firefox',text:'Firefox'}
                        ]
                    }
                },
                {
                    field: 'tsdirverpath',
                    title: '驱动地址',
                    editable:{
                        type:'text',
                        title: '驱动地址'
                    }
                },
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id=row.tsoperatingenvid;
                    var returnValue = '<a class="update" href="javascript:;"  onclick="delAction('+id+')" data-toggle="tooltip"  title="remove"><i class="glyphicon glyphicon-remove"></i></a>'+
                        '<a class="update" href="javascript:;"  onclick="appAction('+id+')" data-toggle="tooltip"  title="apply"><i class="glyphicon glyphicon-cog"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ],
            onEditableSave:function (field,row,oldValue,$el) {
                $.ajax({
                    type:'post',
                    url:'/env/update',
                    data:row,
                    dataType:'JSON',
                    success:function (data) {
                        alert(data);
                    }
                })
            }
        })
    })
    //新增动作
    var OperatingenvDialog;
    function addOperatingenv() {
        OperatingenvDialog=$.dialog({
            animationSpeed: 300,
            title: '添加运行环境',
            content: 'url:/env/create',
            onContentReady: function () {
                initMaterialInput();
            }
        });
    }
    //删除动作
    function delAction(id) {
        $.ajax({
            type:'post',
            url:'/env/delete',
            data:{"tsoperatingenvid":id},
            success:function () {
                $envTable.bootstrapTable('refresh');
            }
        })
    }
    //设置默认运行环境
    function appAction(id) {
        $.ajax({
            type:'post',
            url:'/env/apply',
            data:{"tsoperatingenvid":id},
            success:function (data) {
                if(data>0){
                    alert("设置成功");
                }
            }
        })
    }
</script>
</html>
