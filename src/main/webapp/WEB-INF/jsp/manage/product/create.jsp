<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="createDialog" class="crudDialog">
    <form id="createForm" method="post">
        <div class="form-group">
            <label for="tsname">产品名称</label>
            <input id="tsname" type="text" class="form-control" name="tsname" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tsurl">测试地址</label>
            <input id="tsurl" type="text" class="form-control" name="tsurl" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tsdburl">数据库地址</label>
            <input id="tsdburl" type="text" class="form-control" name="tsdburl" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tsdbname">数据库名称</label>
            <input id="tsdbname" type="text" class="form-control" name="tsdbname" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tsusername">登录用户</label>
            <input id="tsusername" type="text" class="form-control" name="tsusername" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tspassword">登录密码</label>
            <input id="tspassword" type="text" class="form-control" name="tspassword" maxlength="50">
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
        </div>
    </form>
</div>
<script>
    function createSubmit() {
        $.ajax({
            type: 'post',
            url: '/product/add',
            data: $('#createForm').serialize(),
            beforeSend: function() {
                if ($('#tsname').val() == '') {
                    $('#tsname').focus();
                    return false;
                }
                if ($('#tsurl').val() == '') {
                    $('#tsurl').focus();
                    return false;
                }
            },
            success: function() {
                createDialog.close();
                $table.bootstrapTable('refresh');
            }
        });
    }
</script>
