<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.edu.cup.system.SystemTitle" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!--设置布局模板-->
    <meta name="layout" content="welcome"/>
    %{--浏览器标签上的标题-kq--}%
    <title>${cn.edu.cup.system.SystemTitle.last()?.applicationTitle}</title>
    %{--浏览器标签上的图标-kq--}%
    <asset:link rel="icon" href="cn/edu/cup/${cn.edu.cup.system.SystemTitle.last()?.applicationLogo}"
                type="image/x-ico"/>
    <!-- 设置标题，如果没有特殊情况，标题叫这样了。 -->
    <g:set var="entityName" value="Lims2018"/>
    <title>${entityName}维护</title>
    <!--asset:javascript src="cn/edu/cup/os/${entityName}.js"/-->
</head>

<body>
<content tag="nav">
    <li><a href="#">当前用户：${session.systemUser}</a></li>
    <li>
        <g:if test="${session.systemUser}">
            <a href="${createLink(uri: '/home/logout')}">退出</a>
        </g:if>
        <g:else>
            <a href="${createLink(uri: '/home/loginUI')}">去登录</a>
        </g:else>
    </li>
</content>

<div class="carousel slide" id="carousel-welcome">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-slide-to="0" data-target="#carousel-welcome" class="active"></li>
        <li data-slide-to="1" data-target="#carousel-welcome"></li>
        <li data-slide-to="2" data-target="#carousel-welcome"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <g:each in="${cn.edu.cup.system.SystemTitle.last()?.systemCarousel}" var="item" status="i">
            <g:if test="${i == 0}">
                <div class="item active">

                    <asset:image alt="loading..." src="cn/edu/cup/${item.imageName}"/>

                    <div class="carousel-caption">
                        <h4>${item.name}</h4>

                        <p>
                            这里写点儿什么？
                        </p>
                    </div>
                </div>
            </g:if>
            <g:else>
                <div class="item">
                    <asset:image alt="loading..." src="cn/edu/cup/${item.imageName}"/>
                    <div class="carousel-caption">
                        <h4>${item.name}</h4>

                        <p>
                            这里写点儿什么？
                        </p>
                    </div>
                </div>
            </g:else>
        </g:each>

    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="left carousel-control" href="#carousel-welcome" data-slide="prev">
        <span class="glyphicon-chevron-left glyphicon"></span></a>
    <a class="right carousel-control" href="#carousel-welcome" data-slide="next">
        <span class="glyphicon-chevron-right glyphicon"></span></a>
</div>
<hr>

<div class="span12">
    <!-- 软件介绍 -->
    <div class="col-md-4 column">
        <h1>
            Easypipe
            <g:if test="${flash.message}">
                <div class="home-message" role="status">${flash.message}</div>
            </g:if>
        </h1>

        <p>
            Easypipe是一款具有管网模拟、优化、评价、控制等功能的在线应用程序。
        </p>

        <p>
            <a class="btn btn-primary btn-large" href="#">参看更多 ?</a>
        </p>
    </div>

    <!--登录信息统计 -->
    <div class="col-md-4 column">
        <h1 class="home-title">最近登陆的有：</h1>
        <div id="listSystemLogDiv"></div>
        <div id="paginationListSystemLogDiv" class="easyui-pagination" data-options="total: ${cn.edu.cup.system.SystemLog.count()}">

        </div>
    </div>

    <div class="col-md-4 column">
        <h1 class="home-title">最近的交流信息：</h1>
        <div id="listSystemChatDiv"></div>
        <div id="paginationListSystemChatDiv" class="easyui-pagination" data-options="total: ${cn.edu.cup.system.SystemChat.count()}">

        </div>
    </div>
</div>

</body>
</html>
