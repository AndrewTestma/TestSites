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
<div class="myform">
    <form class="form-horizontal" method="post" id="autostepsForm">
        <fieldset>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsautostepsname" style="color: red">步骤名称：</label>
                    <input id="tsautostepsid"  type="hidden" class="form-control" name="tsautostepsid" value="${autosteps.tsautostepsid}">
                    <input id="tsautostepsname" type="text" class="form-control" name="tsautostepsname" value="${autosteps.tsautostepsname}" maxlength="50" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsselecttype" style="color: red">查找方式：</label>
                    <select id="tsselecttype" name="tsselecttype" class="form-control" title="查找方式">
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
                <div class="col-sm-12">
                    <label for="tsselectcontent" style="color: red">查找内容：</label>
                    <input id="tsselectcontent" type="text" class="form-control" name="tsselectcontent" value="${autosteps.tsselectcontent}" maxlength="50" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsactiontype" style="color: red">执行方式：</label>
                    <select id="tsactiontype" name="tsactiontype" class="form-control" title="执行方式">
                        <option>单击</option>
                        <option>输入</option>
                        <option>双击</option>
                        <option>悬浮</option>
                        <option>拖拽</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12"  id="tsactioncontentdiv" style="display: none">
                    <label for="tsactioncontent">执行内容：</label>
                    <input id="tsactioncontent"  type="text" class="form-control" name="tsactioncontent" value="${autosteps.tsactioncontent}" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsframepath">FramePath：</label>
                    <input id="tsframepath" type="text" class="form-control" name="tsframepath" value="${autosteps.tsframepath}" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tswait">等待时间：</label>
                    <input id="tswait" type="text" class="form-control" name="tswait" value="${autosteps.tswait}" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsremarks">备注：</label>
                    <input id="tsremarks" type="text" class="form-control" name="tsremarks" value="${autosteps.tsremarks}" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label>是否验证:</label>
                    <input type="checkbox" onchange="document.getElementById('verdiv').style.display='inline'">
                </div>
            </div>
            <div id="verdiv" style="display: none">
                <div class="form-group">
                    <div class="col-sm-12">
                        <label for="tsverificationtype">验证方式：</label>
                        <select id="tsverificationtype" name="tsverificationtype" class="form-control " title="验证方式">
                            <option>验证方式</option>
                            <option selected = selected">文本验证</option>
                            <option>url验证</option>
                            <option>数据库验证</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <label for="tsverficationframe">verFrame：</label>
                        <input id="tsverficationframe" type="text" class="form-control" name="tsverficationframe" value="${autosteps.tsverficationframe}" maxlength="50">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <label for="tsverificationcontent">验证内容：</label>
                        <input id="tsverificationcontent" type="text" class="form-control" name="tsverificationcontent" value="${autosteps.tsverificationcontent}" maxlength="50">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <a id="add" class="btn  btn-success" onclick="createAutoSteps();">保存</a>
                    <a id="edit" class="btn  btn-success" onclick="updatetcAction();" style="display: none">修改</a>
                    <a class="btn btn-info"  onclick="addAutoStepsDialog.close();">取消</a>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
<jsp:include page="/resources/inc/footer.jsp" flush="true"/>
<script>
    $(function () {
        $('#autostepsForm').validator();
        var numbers;
        var autostepsValue;
        if('${autosteps.tsautostepsname}'!=''){
            document.getElementById("edit").style.display="inline";
            document.getElementById("add").style.display="none";
            for(var n=0;n<3;n++){
                if(n==0){
                    numbers = $("#tsselecttype").find("option");
                    autostepsValue='${autosteps.tsselecttype}';
                }else if(n==1){
                    numbers = $("#tsactiontype").find("option");
                    autostepsValue='${autosteps.tsactiontype}';
                }else{
                    numbers = $("#tsverificationtype").find("option");
                    autostepsValue='${autosteps.tsverificationtype}';
                }
                for(var i=0;i<numbers.length;i++){
                    if($(numbers[i]).val()==autostepsValue){
                        $(numbers[i]).attr("selected", "selected");
                    }
                }
            }
        }
    })
    //点击保存操作步骤
    var autoStespID;
    function createAutoSteps() {
        $.ajax({
            type:'post',
            url:'/module/save',
            data:{"module":tsmodulename.value},
            success:function () {

            }
        })
        $.ajax({
            type: 'post',
            url: '/autosteps/add',
            data: $('#autostepsForm').serialize(),
            beforeSend: function() {
            },
            success: function(data) {
                if(data>0){
                    autoStespID=data;
                    addCaseSteps();
                    autostepstable(0);
                    addAutoStepsDialog.close();
                }
            },
            error:function (data) {
            }
        });
    }
    //点击修改操作步骤
    function updatetcAction() {
        $.ajax({
            type:'post',
            url:'/autosteps/update',
            data:$('#autostepsForm').serialize(),
            success:function (data) {
                if(data=="success"){
                    alert("更新成功");
                    autostepstable(0);
                    addAutoStepsDialog.close();
                }else{
                    alert("修改失败");
                }
            }
        })
    }
    //添加测试用例与操作步骤中间表
    function addCaseSteps() {
        $.ajax({
            type:'post',
            url:'/casesteps/add',
            data:{'uitestcaseID':uitestcaseID,'autostepsID':autoStespID},
            success:function (data) {
            }
        })
    }
    //选择输入展开执行内容
    $('#tsactiontype').change(function () {
        if(($(this).children('option:selected').val())=="输入"){
            document.getElementById("tsactioncontentdiv").style.display="inline";
        }else{
            document.getElementById("tsactioncontentdiv").style.display="none";
        }
    })
</script>
</html>
