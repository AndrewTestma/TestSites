<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="redirectAction" class="crudDialog">
    <form id="moduleForm" method="post">
        <div class="form-group">
            <label for="tsame">模块名称</label>
            <input id="tsame" type="text" class="form-control" name="tsame" maxlength="50">
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:;" onclick="redirectDialog.close();">取消</a>
        </div>
    </form>
</div>
<table id="module"></table>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '/module/create',
            data: $('#moduleForm').serialize(),
            beforeSend: function() {
                if ($('#tsame').val() == '') {
                    $('#tsame').focus();
                    return false;
                }
            },
            success: function(result) {
                if (result == 1) {
                    redirectDialog.close();
                    $('#module').bootstrapTable('refresh');
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                $.confirm({
                    theme: 'dark',
                    animation: 'rotateX',
                    closeAnimation: 'rotateX',
                    title: false,
                    content: textStatus,
                    buttons: {
                        confirm: {
                            text: '确认',
                            btnClass: 'waves-effect waves-button waves-light'
                        }
                    }
                });
            }
        });
    }
    var $module=$('#module');
    $(function () {
        $module.bootstrapTable({
            url: '/module/list',
            height: getHeight(),
            striped: true,
            search: false,
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
            idField: 'tsmoduleid',
            maintainSelected: true,
            columns: [
                {field: 'tsmoduleid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsame', title: '模块名称'},
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id=row.tsmoduleid;
                    var returnValue ='<a class="delete" href="javascript:;" onclick="deleteAction('+id+')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ]
        });
    });
    function deleteAction(id) {
        $.ajax({
            type:'post',
            url:'/module/del',
            data:{"tsmoduleid":id},
            success:function (data) {
                if(data==0){
                    alert("此模块被测试用例使用，请先删除测试用例");
                }else {
                    var opt={
                        url: '/module/list',
                    };
                    $module.bootstrapTable('refresh',opt);
                }
            }
        })
    }
</script>