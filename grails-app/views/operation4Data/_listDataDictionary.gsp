<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataDictionary.label', default: 'DataDictionary')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-dataDictionary" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-dataDictionary" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--f:table collection="${dataDictionaryList}" /-->
            <table>
                <thead>
                <th>id</th>
                <th>字典名称</th>
                <th>字典维护</th>
                <th>数据模型数</th>
                </thead>
                <tbody>
                <g:each in="${dataDictionaryList}" var="item" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${item.id}</td>
                        <td>
                            <a href="javascript: selectCurrentDictionary(${item.id})">
                                ${item.name}
                            </a>
                        </td>
                        <td>
                            <a href="javascript: showDataDictionary(${item.id})">
                                编辑
                            </a>
                            <a href="javascript: maintainDataDictionary(${item.id})">
                                维护
                            </a>
                        </td>
                        <td>${item.datakeys?.size()}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>