<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

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
    <g:set var="entityName" value="SystemLog"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
</head>

<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-12 column">
            <div class="panel panel-default">
                <div class="nav">
                    <ul>
                        <li>
                            <a class="list">
                                系统用户维护——(重新登录后，更新)
                            </a>
                        </li>
                        <li>
                            <a href="operation4SystemLog/clearSystemLog?clearType=keepLast&n=10">只保留最近的10条记录</a>
                        </li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div id="listSystemLogDiv" class="easyui-tree"></div>

                    <div id="paginationSystemLogDiv" class="easyui-pagination"></div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
