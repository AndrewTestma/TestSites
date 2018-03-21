<%--
  Created by IntelliJ IDEA.
  User: andrew
  Date: 2018/3/20
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9,IE=edge,Chrome=1" />
    <title>登录</title>
    <link href="${basePath}/resources/css/login.css" rel="stylesheet"/>
    <link href="${basePath}/resources/css/style.css" rel="stylesheet"/>
    <jsp:include page="/resources/inc/footer.jsp" flush="true"/>
    <script type="text/javascript" >
        var options = {
            feedbackIcons : {
                valid : 'glyphicon glyphicon-ok',
                invalid : 'glyphicon glyphicon-remove',
                validating : 'glyphicon glyphicon-refresh'
            },
            live : 'enabled',
            fields:{
                account: {
                    validators:{
                        notEmpty: {
                            message: '账号不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 20,
                            message: '账号长度必须在6-20个字符之间'
                        },
                        regexp:{
                            regexp:/^[a-zA-Z0-9]+$/,
                            message:'账号只能由数字和字母组成'
                        }
                    }
                },
                password:{
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 5,
                            max: 20,
                            message: '密码长度必须在6到20个字符之间'
                        },
                        regexp:{
                            regexp:/^[a-zA-Z0-9]+$/,
                            message:'密码只能由数字和字母组成'
                        }
                    }
                }

            }
        };
        /**发送登录请求**/
        function login(){
           /* if(validate("#loginform",options)){

            }*/
            $.ajax({
                type:'post',
                url:'/user/login',
                data:{'name':$("#account").val(),'password':$("#password").val()},
                success:function (data) {
                    if(data>0){
                        window.location.href="/index/list";
                    }
                }
            })
        }
    </script>
</head>
<body>
<div class="login">
    <div class="message">自动化测试平台</div>
    <div id="darkbannerwrap"></div>
    <form id="loginform"method="post">
        <div class="form-group">
            <input name="account" class="form-control" id="account"   type="text" placeholder="请输入账号">
            <hr class="hr15">
        </div>
        <div class="form-group">
            <input name="password" class="form-control" id="password"   type="password" placeholder="请输入密码">
            <hr class="hr15">
        </div>
        <input value="登录" style="width:100%;" type="button" onclick="login()">
        <hr class="hr20">
        <!-- 帮助 <a onClick="alert('请联系管理员')">忘记密码</a> -->
    </form>
</div>

<div class="copyright">©  2018-03-20</div>

</body>
</html>