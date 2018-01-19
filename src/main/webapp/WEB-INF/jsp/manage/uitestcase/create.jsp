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
    <jsp:include page="/resources/inc/footer.jsp" flush="true"/>
</head>
<body>
    <div class="container">
        <form class="form-horizontal" method="post" id="testcaseForm">
        <div id="wizard"  class="swMain">
            <ul>
                <li><a href="#step-1">
                    <label class="stepNumber">1</label>
                    <span class="stepDesc">
             测试用例<br />
          </span>
                </a></li>
                <li><a href="#step-2">
                    <label class="stepNumber">2</label>
                    <span class="stepDesc">
             操作步骤<br />
          </span>
                </a></li>
            </ul>
            <div id="step-1">
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
                            <select id="tsgrade" name="tsgrade" class="form-control selectpicker" title="测试等级">
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
        </div>
            <div id="step-2">

            </div>
        </div>
</form>
    </div>
</body>
<script>
    //初始化步骤
    $(document).ready(function() {
        // Initialize Smart Wizard
        $('#wizard').smartWizard({
            onFinish:function () {
                addObejct();
            }
        });
    });
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
    //点击finish按钮触发
    function addObejct() {
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
            success: function() {
               window.location.href="/ui/index";
            },
            error:function () {
            }
        });
    }
</script>
</html>
