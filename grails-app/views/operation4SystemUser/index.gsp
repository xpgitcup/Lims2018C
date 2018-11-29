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
    <g:set var="entityName" value="SystemUser"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/system/${entityName}.js"/>
</head>

<body>
<div class="container">
    <div class="row-fluid">
        <div class="col-md-4 column">
            <div class="panel panel-default">
                <div class="nav">
                    <ul>
                        <li>
                            <a class="list">
                                系统用户维护——(重新登录后，更新)
                            </a>
                        </li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div id="listSystemUserDiv" class="easyui-tree"></div>

                    <div id="paginationSystemUserDiv" class="easyui-pagination"></div>
                </div>
            </div>
        </div>

        <div class="col-md-8 column">
            <div class="panel panel-default">
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="create" href="javascript: createSystemUser(0)">新建</a></li>
                    </ul>
                </div>

                <div class="easyui-panel">
                    <div id="showSystemUserDiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
