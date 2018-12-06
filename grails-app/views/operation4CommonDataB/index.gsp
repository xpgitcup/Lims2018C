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

    <g:set var="entityName" value="${entityName}"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/commondata/${entityName}.js"/>
</head>

<body>
<div id="tabNameList" class="hidden">${tabNameList}</div>
<div id="idList" class="hidden">${idList}</div>
<div id="dataKeys" class="hidden">${dataKeys}</div>
<div id="functionName" class="hidden">${functionName}</div>

<div class="nav">
    <ul>
        <li>
            <h1>当前功能：${session.function}</h1>
        </li>
        <g:each in="${topHrefList}" status="i" var="itemA">
            <g:each in="${itemA}" status="j" var="item">
                <g:if test="${item.value=='#'}">
                    <li>
                        <a>
                            <div>当前${item.key}</div>
                        </a>
                    </li>
                    <li>
                        <a>
                            <div id="${item.key}">${item.key}</div>
                        </a>
                    </li>
                </g:if>
            </g:each>
        </g:each>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="operation4CommonDataBDiv" class="easyui-tabs">
</div>
</body>
</html>
