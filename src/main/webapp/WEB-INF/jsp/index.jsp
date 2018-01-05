<%@page contentType="text/html;charset=UTF-8" language="java" %>
<% String appPath=request.getContextPath();%>
<html>
<head>
    <meta charset="utf-8">
    <title>测试平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="/static/js/jquery-1.10.2.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body>
<%--<h2>TestSites</h2>
运行Demo
<br />
<br /><br /><br /><br />
&lt;%&ndash;产品列表：<a href="<%=appPath%>/tsproduct/productlist">点击前往</a>&ndash;%&gt;
用例：<a href="<%=appPath%>/ui/list">点击前往</a>--%>
<div class="container">
    <div class="row clearfix" style="margin-top:300px">
        <div class="col-md-12 column">
            <form class="form-horizontal" role="form" action="/index/login" method="post">
                <div id="legend" class="">
                    <legend class="">登录</legend>
                </div>
                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="tsloginname">用户名</label>
                    <div class="col-xs-6">
                        <input type="text" id="tsloginname" name="tsloginname" placeholder="andrew" class="form-control input-xlarge">
                    </div>
                </div>

                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="tspassword">密码</label>
                    <div class="col-xs-6">
                        <input type="text" id="tspassword" name="tspassword" placeholder="123456" class="form-control input-xlarge">
                    </div>
                </div>

                <div class="form-group">
                    <!-- Button -->
                    <div class="controls col-xs-6">
                        <button class="btn btn-success" type="submit" <%--onclick="login($('#tsloginname').val(),$('#tspassword').val())"--%>>登录</button>
                        <button class="btn btn-success">注册</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
<script>
    function login(loginname,password) {
        $.ajax({
            type:"post",
            url:"/index/login",
            data:{loginname:loginname,password:password},
            dataType:"text",
            success:function () {
                alert("登录成功");
            },
            error:function () {
                alert("登录失败");
            }
        })
    }
</script>
</body>
</html>
