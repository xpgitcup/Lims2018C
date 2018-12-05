<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

    <g:set var="entityName" value="CommonDataA"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/commondata/${entityName}.js"/>
</head>

<body>
<div id="commonTabList" class="hidden">${commonTabList}</div>

<div id="commonIdList" class="hidden">${commonIdList}</div>

<div class="nav">
    <ul>
        <g:each in="${ids}" status="i" var="item">
            <li>
                <a href="#">
                    <div>${item}</div>
                </a>
            </li>
            <li>
                <a href="#">
                    <div id="${item}">${item}</div>
                </a>
            </li>
        </g:each>
        <g:each in="${commonFunctionList}" status="i" var="item">
            <li>
                <a href="javascript: ${item[1]}">
                    <div>${item[0]}</div>
                </a>
            </li>
        </g:each>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="operation4CommonDataADiv" class="easyui-tabs">
</div>
</body>
</html>
