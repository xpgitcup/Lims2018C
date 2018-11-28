<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <g:set var="userName" value="${session.systemUser}"/>
    <!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!-- end 实现可定制的布局 -->
<!-- 设置当前域类的名字，以后的所有命名规则都基于这个名字 -->
    <g:set var="entityName" value="Home"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 设置标题，如果没有特殊情况，标题叫这样了。 -->
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/os/${entityName}.js"/>
</head>

<body>
<div class="col-md-4">
    <h1 class="home-title">最近发布的信息：</h1>
    <table class="home-title">
        <g:each in="${messageToMe}" var="item">
            <tr>
                <td>${item.speakTo}</td>
                <td>${item.getMessage()}</td>
            </tr>
        </g:each>
    </table>
</div>

<div class="col-md-4">
    <h1 class="home-title">最近的交流信息：</h1>
    <table class="home-title">
        <g:each in="${messageToMe}" var="item">
            <tr>
                <td>${item.speaker}</td>
                <td>${item.speakTo}</td>
                <td>${item.getMessage()}</td>
            </tr>
        </g:each>
    </table>
</div>
</body>
</html>
