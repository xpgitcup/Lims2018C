<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!--meta name="layout" content="main"/-->
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

    <g:set var="entityName" value="Dictionary"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>
</head>

<body>

<div class="nav">
    <ul>
        <li>
            <a href="#">
                当前字典：
            </a>
        </li>
        <li>
            <a href="#">
                <div id="currentDictionary"></div>
            </a>
        </li>
        <li>
            <a href="#">
                当前模型：
            </a>
        </li>
        <li>
            <a href="#">
                <div id="currentDataKey"></div>
            </a>
        </li>
        <li>
            <a href="javascript: createDataDictionary()" class="create">
                新数据字典
            </a>
        </li>
        <li>
            <a href="javascript: createDataKey()" class="create">
                新数据模型
            </a>
        </li>
        <li>
            <a href="javascript: createDataItem()" class="create">
                新数据
            </a>
        </li>
    </ul>
</div>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="operation4DataDiv" class="easyui-tabs">
    <div id="maintainDataKeyDiv" title="数据模型维护">
        <div class="container">
            <div class="col-md-4">

                <div class="nav">
                    <ul>
                        <li>
                            <a class="list" href="javascript: turnToDisplay()">
                                返回
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div class="easyui-tree" id="displayDataKeyTreeDiv"></div>
                    <div class="easyui-pagination" id="paginationDataKeyDiv"></div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="create" href="javascript: createDataKey(0)">新建根节点</a></li>
                        <li><a id="createDataKey" class="create" href="#">新建子节点</a></li>
                    </ul>
                </div>

                <div class="easyui-panel" id="editDataKeyDiv"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
