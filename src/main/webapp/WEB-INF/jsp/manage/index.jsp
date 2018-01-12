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
    <title>自动化测试平台</title>

    <link href="${basePath}/resources/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
    <link href="${basePath}/resources/css/admin.css" rel="stylesheet"/>
    <style>
         #header {background: #29A176;}
        .content_tab{background: #29A176;}
    </style>
</head>
<body>
<header id="header">
    <ul id="menu">
        <li id="guide" class="line-trigger">
            <div class="line-wrap">
                <div class="line top"></div>
                <div class="line center"></div>
                <div class="line bottom"></div>
            </div>
        </li>
        <li id="logo" class="hidden-xs">
            <a href="${basePath}/manage/index">
                <img src="${basePath}/resources/images/logo.png"/>
            </a>
            <span id="system_title">自动化测试平台</span>
        </li>
        <li class="pull-right">
            <ul class="hi-menu">
                <!-- 搜索 -->
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-search"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <form id="search-form" class="form-inline">
                            <div class="input-group">
                                <input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索"/>
                                <div class="input-group-btn">
                                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                                </div>
                            </div>
                        </form>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-dropbox"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="skin-switch hidden-xs">
                            请选择产品切换
                        </li>
                        <li class="divider hidden-xs"></li>
                        <c:forEach var="product" items="${requestScope.get('list')}" varStatus="status">
                            <li>
                                <a class="waves-effect switch-systems" onclick="addsession()" href="javascript:;" systemid="1"  systemname="${product.tsname}" systemtitle="${product.tsname}"><i class="${upmsSystem.icon}"></i> ${product.tsname}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
                        <i class="him-icon zmdi zmdi-more-vert"></i>
                    </a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li class="hidden-xs">
                            <a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
                        </li>
                        <li>
                            <a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
                        </li>
                        <li>
                            <a class="waves-effect" href="${basePath}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</header>
<section id="main">
    <!-- 左侧导航区 -->
    <aside id="sidebar">
        <!-- 个人资料区 -->

        <!-- /个人资料区 -->
        <!-- 菜单区 -->
        <ul class="main-menu">
            <li>
                <a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
            </li>
            <li class="sub-menu system_menus system_1 3">
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts"></i> 用例管理</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('UI测试用例', '');">UI测试用例</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('接口测试用例', '');">接口测试用例</a></li>
                </ul>
            </li>
            <li class="sub-menu system_menus system_1 3" >
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-lock-outline"></i> 场景管理</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('业务管理', '');">业务管理</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('回归测试', '');">回归测试</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('冒烟测试', '');">冒烟测试</a></li>
                </ul>
            </li>
            <li class="sub-menu system_menus system_1 3">
                <a class="waves-effect" href="javascript:Tab.addTab('用户管理', 'home');"><i class="zmdi zmdi-home"></i> 用户管理</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('权限设置', '');">权限设置</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('用户信息', '');">用户信息</a></li>
                </ul>
            </li>
            <li class="sub-menu system_menus system_1 3" >
                <a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts-list"></i> 设置</a>
                <ul>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('产品信息', '${basePath}/product/index');">产品设置</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('模块信息', '${basePath}/product/index');">产品设置</a></li>
                    <li><a class="waves-effect" href="javascript:Tab.addTab('运行设置', '');">运行设置</a></li>
                </ul>
            </li>
            <li>
                <div class="upms-version">&copy; </div>
            </li>
        </ul>
        <!-- /菜单区 -->
    </aside>
    <!-- /左侧导航区 -->
    <section id="content">
        <div class="content_tab">
            <div class="tab_left">
                <a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
            </div>
            <div class="tab_right">
                <a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
            </div>
            <ul id="tabs" class="tabs">
                <li id="tab_home" data-index="home" data-closeable="false" class="cur">
                    <a class="waves-effect waves-light" href="javascript:;">首页</a>
                </li>
            </ul>
        </div>
        <div class="content_main">
            <div id="iframe_home" class="iframe cur">

            </div>
        </div>
    </section>
</section>
<footer id="footer"></footer>
<script>
    var BASE_PATH = '${basePath}';
    function addsession(){
        //添加session 方法
        $.ajax({});
    }
</script>
<script src="${basePath}/resources/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="${basePath}/resources/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${basePath}/resources/plugins/BootstrapMenu.min.js"></script>
<script src="${basePath}/resources/plugins/device.min.js"></script>
<script src="${basePath}/resources/plugins/jquery.cookie.js"></script>
<script src="${basePath}/resources/js/admin.js"></script>
<script src="${basePath}/resources/plugins/fullpage/jquery.fullPage.min.js"></script>
<script src="${basePath}/resources/plugins/fullpage/jquery.jdirk.min.js"></script>
</body>
</html>