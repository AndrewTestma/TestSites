<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<div id="testcaseDialog" class="crudDialog">
    <form id="createForm" method="post">
        <div class="form-group">
            <label for="tsname">用例名称</label>
            <input id="tsname" type="text" class="form-control" name="tsname" maxlength="50">
        </div>
        <div class="form-group">
            <select id="tsgrade" class="selectpicker" title="测试等级">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
        <div class="form-group">
            <select id="moduleSelect1" class="selectpicker" data-live-search="true"/>
        </div>
        <div class="form-group">
            <a class="waves-effect waves-button" href="javascript:;" onclick="autoStepAction()"><i class="zmdi zmdi-plus"></i> 操作步骤</a>
        </div>
        <div class="form-group">
            <label for="tsdbname">测试结果</label>
            <input id="tsdbname" type="text" class="form-control" name="tsdbname" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tsusername">公共用例</label>
            <input id="tsusername" type="text" class="form-control" name="tsusername" maxlength="50">
        </div>
        <div class="form-group">
            <label for="tspassword">创建人</label>
            <input id="tspassword" type="text" class="form-control" name="tspassword" maxlength="50">
        </div>
        <div class="form-group text-right dialog-buttons">
            <a class="waves-effect waves-button" href="javascript:;" onclick="createSubmit();">保存</a>
            <a class="waves-effect waves-button" href="javascript:;" onclick="createDialog.close();">取消</a>
        </div>
    </form>
</div>
<script>
    $('#moduleSelect1').append("<option>请选择</option>");
    $(function() {
        $('.selectpicker').selectpicker({
            size: 10
        });
        $.ajax({
            type: "get",
            url: "/module/selectlist",
            dataType: "json",
            success: function (resMsg) {
                var resultJson = eval(resMsg);
                $.each(resultJson, function (i, module) {
                    $('#moduleSelect1').append("<option value=" + module.tsmoduleid + ">" + module.tsame + "</option>");
                })
                   $('#moduleSelect1').selectpicker('refresh');
                 $('#moduleSelect1').selectpicker('render');
            }
        });
    });
</script>
