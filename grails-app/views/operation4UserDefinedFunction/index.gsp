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

    <g:set var="entityName" value="UserDefinedFunction"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/userdef/${entityName}.js"/>
</head>

<body>
<div class="nav">
    <ul>
        <li><a href="#">当前功能：</a></li>
        <li><a href="#"><div id="currentUserDefinedFunction"></div></a></li>
        <li><a href="#">当前类库：</a></li>
        <li><a href="#"><div id="currentLibrary"></div></a></li>
        <li><a href="#">当前类：</a></li>
        <li><a href="#"><div id="currentClass"></div></a></li>
        <li><a href="#">当前方法：</a></li>
        <li><a href="#"><div id="currentMethod"></div></a></li>
        <li><a href="javascript: createUserDefinedFunction()" class="create">新功能</a></li>
        <li><a href="javascript: createUserClassLibrary()" class="create">新类库</a></li>
    </ul>
</div>

<div id="operation4UserDefinedFunctionDiv" class="easyui-tabs">
</div>
</body>
</html>
