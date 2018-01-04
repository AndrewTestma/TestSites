<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>测试用例</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="/static/js/jquery-1.10.2.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix" style="margin-top:50px">
        <div class="col-md-12 column">
            <div class="btn-group">
                <a class="btn btn-default" href="<%=appPath%>/ui/addpage" >添加</a>
            </div>
            <table class="table" style="margin-top:20px">
                <thead>
                <tr>
                    <th>用例名称</th>
                    <th>用例等级</th>
                    <th>前置用例</th>
                    <th>预期结果</th>
                    <th>验证方式</th>
                    <th>验证内容</th>
                    <th>调试成功</th>
                    <th>回归测试</th>
                    <th>冒烟测试</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="uitestcase" items="${requestScope.get('list')}" varStatus="status">
                    <tr>
                            <td>${uitestcase.tsnum}</td>
                            <td>${uitestcase.tsgrade}</td>
                            <td>${uitestcase.tsfrontcase}</td>
                            <td>${uitestcase.tsexpected}</td>
                            <td>${uitestcase.tsverificationid}</td>
                            <td>${uitestcase.tsverificationcontent}</td>
                            <td>${uitestcase.tsdebug}</td>
                            <td>${uitestcase.tsregress}</td>
                            <td>${uitestcase.tssmoke}</td>
                            <td><a href="#"  class="btn" type="button" onclick="debugging(${uitestcase.tsuitestcaseid})">调试</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%--<div class="modal fade" id="modal-container" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加用例
                    </h4>
                </div>
                <div class="modal-body">
                    <!-- 表单-->
                    <form class="form-horizontal" role="form">
                        <div class="form-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="input01">用例名称</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="input01">前置用例</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="input01">预期结果</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                            </div>
                        </div>

                        <div class="form-group">

                            <!-- Select Basic -->
                            <label class="control-label col-xs-3">验证方式 </label>
                            <div class="controls col-xs-6">
                                <select class="form-control">
                                    <option>URL验证</option>
                                    <option>字符串验证</option>
                                    <option>数据库验证</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="input01">验证内容</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" class="form-control input-xlarge" type="text">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3">Inline Checkboxes</label>

                            <!-- Inline Checkboxes -->
                            <div class="controls col-xs-9">
                                <label class="checkbox-inline">
                                    <input value="1" type="checkbox"> 1
                                </label>
                                <label class="checkbox-inline">
                                    <input value="2" type="checkbox"> 2
                                </label>
                                <label class="checkbox-inline">
                                    <input value="3" type="checkbox"> 3
                                </label>
                            </div>

                        </div>

                        <div class="form-group">

                            <!-- Select Basic -->
                            <label class="control-label col-xs-3">Select </label>
                            <div class="controls col-xs-6">
                                <select class="form-control">
                                    <option>Enter</option>
                                    <option>Your</option>
                                    <option>Options</option>
                                    <option>Here!</option>
                                </select>
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3">Button</label>

                            <!-- Button -->
                            <div class="controls col-xs-6">
                                <button class="btn btn-success">Button</button>
                            </div>
                        </div>

                    </form>
                    <!-- 表单-->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="button" class="btn btn-primary">保存</button>
                </div>
            </div>
        </div>
    </div>--%>
</div>
<script>
    function debugging(btnid) {
        $.ajax({
            type:"post",
            url:"/ui/debugging",
            data:"btnid="+btnid,
            dataType:"text",
            success:function () {
                alert("调试成功");
            },
            error:function () {
                alert("调试失败");
            }
        })
    }
</script>
</body>
</html>
