<%@ page contentType="text/html; charset=utf-8"%>
<%@ page isELIgnored="false" %>
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
    <div  id="toolbar" class="form-group">
        <div class="form-group">
            <div class="col-sm-8">
                <a class="btn btn-default" href="/ui/create"> <i class="glyphicon glyphicon-plus"> </i>添加用例</a>
            </div>
        </div>
        <div id="select_div" class="form-group">
            <div class="col-sm-4">
                <select id="module" class="btn btn-default moduleselect" data-url="/module/selectlist" data-first-title="请选择模块"
                        data-first-value="" data-json-name="tsame" data-json-value="tsame"></select>
            </div>
        </div>
    </div>
    <table id="tctable"></table>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    var $tctable = $('#tctable');
    var moduleSelect=$('#moduleSelect');
    $(function() {
        $('#select_div').cxSelect({
            selects: ['moduleselect'],  // 数组，请注意顺序
        });
        // bootstrap table初始化
        $('#tctable').bootstrapTable({
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
                {
                    field: 'tsname', title: '用例名称',
                    editable:{
                        type:'text',
                        validate:function (v) {
                            if(!v) return '用例名不能为空';
                        }
                    }
                },
                {
                    field: 'tsgrade',
                    title: '用例等级',
                    editable:{
                        type:'select',
                        title:'用例等级',
                        source:[
                            {value: 1,text: 1},
                            {value: 2,text: 2},
                            {value: 3,text: 3},
                            {value: 4,text: 4}
                        ]
                    }
                },
                {
                    field:'tscommon',title:'公共步骤',align:'center',formatter:function (value,row,index) {
                    var value=row.tscommon;
                    var returnValue;
                    if(value==1){
                        returnValue='<i class="glyphicon glyphicon-star"></i>'
                    }else {
                        returnValue='<i class="glyphicon glyphicon-star-empty"></i>'
                    }
                    return returnValue;
                },events: 'actionEvents', clickToSelect: false
                },
                {field: 'tscreatetime', title: '创建时间'},
                {field: 'tscreator', title: '创建人'},
                {
                    field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id = row.tsproductid;
                    var returnValue = '<a class="update" href="javascript:;"  onclick="" data-toggle="tooltip"  title="Edit"><i class="glyphicon glyphicon-pencil"></i></a>'
                        + '  <a class="delete" href="javascript:;" onclick="deleteAction(' + id + ')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ],
            onExpandRow:function (index,row,$detail) {
                initSubTable(index,row,$detail);
            },
            onEditableSave:function (field,row,oldValue,$el) {
                $.ajax({
                    type:'post',
                    url:'/ui/update',
                    data:row,
                    dataType:'JSON',
                    success:function (data) {
                        if(data=="success"){
                        }
                    }
                })
            }
        });
    });
    //父子表
    function initSubTable(index,row,$detail) {
        var id=row.tsuitestcaseid;
        var cur_table=$detail.html('<table></table>').find('table');
        $(cur_table).bootstrapTable({
            url: '/autosteps/tcstep',
            method:'get',
            queryParams:{uitestcaseID:id},
            idField: 'tsautostepsid',
            maintainSelected: true,
            columns: [
                {field: 'tsautostepsid', title: '编号', sortable: true, align: 'center'},
                {
                    field: 'tsautostepsname',
                    title: '步骤名称',
                    editable:{
                        type:'text',
                        title:'步骤名称',
                        validate:function (v) {
                            if(!v)return '步骤名称不能为空'
                        }
                    }
                },
                {
                    field: 'tsselecttype',
                    title: '查找方式',
                    editable:{
                        type:'select',
                        title:'查找方式',
                        source:[
                            {value:'id' ,text:'id'},
                            {value:'name',text:'name'},
                            {value:'xpath',text:'xpath'},
                            {value:'linkText',text:'linkText'},
                            {value:'className',text:'className'},
                            {value:'cssSelector',text:'cssSelector'},
                            {value:'partialLinkText',text:'partialLinkText'},
                            {value:'tagName',text:'tagName'}
                        ]
                    }
                },
                {
                    field: 'tsselectcontent',
                    title: '查找内容',
                    editable:{
                        type:'text',
                        title:'查找内容',
                        validate:function (v) {
                            if(!v)return '查找内容不能为空'
                        }
                    }
                },
                {
                    field: 'tsactiontype',
                    title: '执行方式',
                    editable:{
                        type:'select',
                        title:'执行方式',
                        source:[
                            {value:'单击',text:'单击'},
                            {value:'输入',text:'输入'},
                            {value:'双击',text:'双击'},
                            {value:'悬浮',text:'悬浮'},
                            {value:'拖拽',text:'拖拽'}
                        ]
                    }
                },
                {
                    field: 'tsactioncontent',
                    title: '执行内容',
                    editable:{
                        type:'text',
                        title:'执行内容'
                    }
                },
                {
                    field: 'tsframepath',
                    title: 'FramePath',
                    editable:{
                        type:'text',
                        title:'FramePath'
                    }
                },
                {
                    field: 'tswait',
                    title: '等待时间',
                    editable:{
                        type:'text',
                        title:'等待时间'
                    }
                },
                {
                    field: 'tsverificationtype',
                    title: '验证方式',
                    editable:{
                        type:'select',
                        title:'验证方式',
                        source:[
                            {value:'文本验证',text:'文本验证'},
                            {value:'url验证',text:'url验证'},
                            {value:'数据库验证',text:'数据库验证'}
                        ]
                    }
                },
                {
                    field: 'tsverificationcontent',
                    title: '验证内容',
                    editable:{
                        type:'text',
                        title:'验证内容'
                    }
                },
                {
                    field:'tscommon',title:'公共步骤',align:'center',formatter:function (value,row,index) {
                    var value=row.tscommon;
                    var returnValue;
                    if(value==1){
                        returnValue='<i class="glyphicon glyphicon-star"></i>'
                    }else {
                        returnValue='<i class="glyphicon glyphicon-star-empty"></i>'
                    }
                    return returnValue;
                },events: 'actionEvents', clickToSelect: false
                },
                {
                    field: 'tsremarks',
                    title: '备注',
                    editable:{
                        type:'text',
                        title:'备注'
                    }
                },
                {field: 'tscreator', title: '创建人'}
            ],
            onEditableSave:function (field,row,oldValue,$el) {
                $.ajax({
                    type:'post',
                    url:'/autosteps/update',
                    data:row,
                    dataType:'JSON',
                    success:function (data) {
                        if(data=="success"){
                            alert(data);
                        }
                    }
                })
            }
        });
    }
    //删除测试用例
    function deleteAction(id) {
        $.ajax({
            type:'get',
            url:'/ui/delete',
            data:{"tsuitestcaseid":id},
            success:function (data) {
                if(data>0){
                    alert("删除成功");
                }else{
                    alert("删除失败");
                }
                var opt={
                    url: '/ui/list',
                };
                $tctable.bootstrapTable('refresh',opt);
            },
            error:function () {
                alert("操作异常");
            }
        })
    }
</script>
</body>
</html>
