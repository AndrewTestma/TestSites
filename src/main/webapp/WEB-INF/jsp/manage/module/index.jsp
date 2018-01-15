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
            <a class="waves-effect waves-button" href="javascript:;" onclick="redirectAction.close();">取消</a>
        </div>
    </form>
</div>
<table id="module"/>
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
                if (result== 1) {
                    redirectAction.close();
                    $module.bootstrapTable('refresh');
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
        $table.bootstrapTable({
            url: '/module/list',
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
            idField: 'tsmoduleid',
            maintainSelected: true,
            toolbar: '#toolbar',
            columns: [
                {field: 'tsmoduleid', title: '编号', sortable: true, align: 'center'},
                {field: 'tsname', title: '模块名称'},
                {field: 'action', title: '操作', align: 'center', formatter: function (value, row, index) {
                    var id=row.tsmoduleid;
                    var returnValue = '<a class="update" href="javascript:;"  onclick="updateAction('+id+')" data-toggle="tooltip"  title="Edit"><i class="glyphicon glyphicon-edit"></i></a>'
                        +'  <a class="delete" href="javascript:;" onclick="deleteAction('+id+')" data-toggle="tooltip" title="Remove"><i class="glyphicon glyphicon-remove"></i></a>';
                    return returnValue;
                }, events: 'actionEvents', clickToSelect: false}
            ]
        });
    });

</script>