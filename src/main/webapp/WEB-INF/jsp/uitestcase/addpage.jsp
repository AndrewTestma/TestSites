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
    <title>添加测试用例</title>
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
            <form class="form-horizontal" role="form" id="add" action="/ui/add" method="post" accept-charset="UTF-8">
                <div class="form-group">
                    <label class="control-label col-xs-3" for="tsnum">用例名称</label>
                    <div class="col-xs-6">
                        <input name="tsnum" id="tsnum" placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="tsfrontcase">前置用例</label>
                    <div class="col-xs-6">
                        <input name="tsfrontcase" id="tsfrontcase" placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="tsexpected">预期结果</label>
                    <div class="col-xs-6">
                        <input name="tsexpected" id="tsexpected" placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>

                <div class="form-group">

                    <!-- Select Basic -->
                    <label class="control-label col-xs-3">验证方式 </label>
                    <div class="controls col-xs-6">
                        <select class="form-control" name="tsverificationid" id="tsverificationid">
                            <option>URL验证</option>
                            <option>字符串验证</option>
                            <option>数据库验证</option>
                        </select>
                    </div>

                </div>

                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="tsverificationcontent">验证内容</label>
                    <div class="col-xs-6">
                        <input name="tsverificationcontent" id="tsverificationcontent" placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>

                <div class="form-group">

                    <!-- Text input-->
                    <label class="control-label col-xs-3" for="tsautostepsname">操作步骤</label>
                    <div class="col-xs-6">
                        <input name="tsautostepsname" id="tsautostepsname" placeholder="placeholder" class="form-control input-xlarge" type="text" >
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12 column">
                        <div class="btn-group">
                           <%-- <a class="btn btn-default" href="<%=appPath%>/ui/redirect" >添加操作步骤</a>--%>
                               <a class="btn btn-default" href="#modal-container" data-toggle="modal">添加操作步骤</a>
                        </div>
                    </div>
                </div>



                <div class="form-group">
                    <label class="control-label col-xs-3" for="tscreator">创建者</label>
                    <div class="col-xs-6">
                        <input name="tscreator" id="tscreator1" placeholder="placeholder" class="form-control input-xlarge" type="text">
                    </div>
                </div>
                <div class="form-group">
                    <div class="controls col-xs-6">
                        <button class="btn btn-success" id="sub" <%--onclick="sumitcase()"--%>>保存</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal fade" id="modal-container" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加操作步骤
                    </h4>
                </div>
                <div class="modal-body">
                    <!-- 表单-->
                    <form class="form-horizontal" role="form" id="addstep" method="post"  action="/autosteps/add" accept-charset="UTF-8">
                        <div class="form-group">

                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tsname">步骤名称</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder"  name="tsname" id="tsname" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Select Basic -->
                            <label class="control-label col-xs-3">查找方式 </label>
                            <div class="controls col-xs-6">
                                <select class="form-control"  name="tssearchid" id="tssearchid" >
                                    <option>by.xpath</option>
                                    <option>by.id</option>
                                    <option>by.name</option>
                                </select>
                            </div>

                        </div>
                        <div class="form-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tssearchcontent">查找内容</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" name="tssearchcontent" id="tssearchcontent" class="form-control input-xlarge" type="text">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-xs-3">执行方式</label>
                            <div class="controls col-xs-6">
                                <select class="form-control" name="tsexecutionid" id="tsexecutionid">
                                    <option>单击</option>
                                    <option>输入</option>
                                    <option>双击</option>
                                    <option>悬浮</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tsexecutioncontent">执行内容</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" name="tsexecutioncontent" id="tsexecutioncontent" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tsiframe">iframe嵌套</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" name="tsiframe" id="tsiframe" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tswaittime">等待时间</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder"  name="tswaittime" id="tswaittime" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tsremarks">备注</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" name="tsremarks" id="tsremarks" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <!-- Text input-->
                            <label class="control-label col-xs-3" for="tscreator">创建者</label>
                            <div class="col-xs-6">
                                <input placeholder="placeholder" name="tscreator" id="tscreator" class="form-control input-xlarge" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="button" onclick="submitstep()" class="btn btn-primary" >保存</button>
                            </div>
                        </div>
                    </form>
                    <!-- 表单-->
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
<script type="text/javascript">
    $('#warning').css('display', 'none');
    var frm=$('#add');
    function sumitcase() {
        $.ajax({
            type:"post",
            url:"/ui.do?add",
            data:frm.serialize(),
            success:function (data) {
               alert("保存成功");
            },
            error:function (data) {
                alert("添加失败")
            }
        });
    }
    $('#warning').css('display', 'none');
    var frm1=$('#addstep');
    function submitstep() {
            $.ajax({
                type: "post",
                url: "/autosteps.do?add",
                data:frm1.serialize(),
                success: function (data) {
                    var tsautostepsname=$("#tsautostepsname").val();
                    if(tsautostepsname!=""){
                        tsautostepsname=tsautostepsname+",";
                    }
                    tsautostepsname += $("#tsname").val();
                    $("#tsautostepsname").val(tsautostepsname);
                        $('#modal-container').modal('hide')
                },
                error: function (data) {
                    alert("提交失败");
                }
            });
    }
</script>
</body>
</html>
