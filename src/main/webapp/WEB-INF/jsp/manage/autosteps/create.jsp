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
                    <input id="tsautostepsname" type="text" class="form-control" name="tsautostepsname"  maxlength="50" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsselecttype" style="color: red">查找方式：</label>
                    <select id="tsselecttype" name="tsselecttype" class="form-control " title="查找方式">
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
                    <input id="tsselectcontent" type="text" class="form-control" name="tsselectcontent" maxlength="50" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsactiontype" style="color: red">执行方式：</label>
                    <select id="tsactiontype" name="tsactiontype" class="form-control " title="执行方式" >
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
                    <input id="tsactioncontent"  type="text" class="form-control" name="tsactioncontent" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsframepath">FramePath：</label>
                    <input id="tsframepath" type="text" class="form-control" name="tsframepath" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tswait">等待时间：</label>
                    <input id="tswait" type="text" class="form-control" name="tswait" maxlength="50">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <label for="tsremarks">备注：</label>
                    <input id="tsremarks" type="text" class="form-control" name="tsremarks" maxlength="50">
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
                            <option>文本验证</option>
                            <option>url验证</option>
                            <option>数据库验证</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <label for="tsverficationframe">verFrame：</label>
                        <input id="tsverficationframe" type="text" class="form-control" name="tsverficationframe" maxlength="50">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <label for="tsverificationcontent">验证内容：</label>
                        <input id="tsverificationcontent" type="text" class="form-control" name="tsverificationcontent" maxlength="50">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-12">
                    <a class="btn  btn-success" onclick="createAutoSteps();">保存</a>
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
