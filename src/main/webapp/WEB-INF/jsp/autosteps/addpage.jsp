<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/28 0028
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath = request.getContextPath();%>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加操作步骤</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="/static/js/jquery-1.10.2.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix" style="margin-top:100px">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" id="addstep" method="post" href="/autosteps/add" accept-charset="UTF-8">
                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="input01">步骤名称</label>
                    <div class="col-xs-6">
                        <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <!-- Select Basic -->
                    <label class="control-label col-xs-3">查找方式 </label>
                    <div class="controls col-xs-6">
                        <select class="form-control">
                            <option>by.xpath</option>
                            <option>by.id</option>
                            <option>by.name</option>
                        </select>
                    </div>

                </div>
                <div class="form-group">
                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="input01">查找内容</label>
                    <div class="col-xs-6">
                        <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-xs-3">执行方式</label>
                    <div class="controls col-xs-6">
                        <select class="form-control">
                            <option>单击</option>
                            <option>输入</option>
                            <option>双击</option>
                            <option>悬浮</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="input01">iframe嵌套</label>
                    <div class="col-xs-6">
                        <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="input01">等待时间</label>
                    <div class="col-xs-6">
                        <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="input01">备注</label>
                    <div class="col-xs-6">
                        <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="input01">创建者</label>
                    <div class="col-xs-6">
                        <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <div class="controls col-xs-6">
                        <button class="btn btn-success" id="substep" type="submit">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    function list() {
        window.location.href="<%=appPath%>/ui/addpage"
    }
    $('#warning').css('display', 'none');
    var frm=$('#addstep');
    frm.submit(function (ev) {
        $.ajax({
            type: frm.attr('method'),
            url:frm.attr('action'),
            data:frm.serialize(),
            success:function () {
                list();
            },
            error:function () {
                alert("添加失败")
            }
        });
        ev.preventDefault();
    });
</script>
</body>
</html>
