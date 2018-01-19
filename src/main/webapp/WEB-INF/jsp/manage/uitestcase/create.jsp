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
        <form class="form-horizontal" role="form">
            <fieldset>
                <legend>测试用例</legend>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tsname">用例名称</label>
                        <input id="tsname" type="text" class="form-control" name="tsname" maxlength="50">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <select id="tsgrade" class="form-control selectpicker" title="测试等级">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10">
                        <select id="moduleSelect1" class="form-control selectpicker"></select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tscommon">公共用例</label>
                        <input id="tscommon" type="text" class="form-control" name="tscommon" maxlength="50">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4">
                        <label for="tscreator" >创建人</label>
                        <input id="tscreator" type="text" class="form-control" name="tscreator" maxlength="50">
                    </div>
                </div>
            </fieldset>
            <div class="form-group text-right dialog-buttons">
                <a class="waves-effect waves-button" href="javascript:;" onclick="">保存</a>
                <a class="waves-effect waves-button" href="javascript:;" onclick="">取消</a>
            </div>
        </form>
</div>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>

</body>
<script>
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
                    $('#moduleSelect1').append("<option value=" + module.tsmoduleid + ">" + module.tsame + "</option>");
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
</script>
</html>
