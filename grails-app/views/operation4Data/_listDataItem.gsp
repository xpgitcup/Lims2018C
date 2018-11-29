<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItem.label', default: 'DataItemA')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-dataItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div id="list-dataItem" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--f:table collection="${dataItemList}"/-->
    <table>
        <thead>
        <th>id</th>
        <th>Name</th>
        <th>子项</th>
        </thead>
        <tbody>
        <g:each in="${dataItemList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.id}</td>
                <td>${item.dataKey}</td>
                <td>${item?.subDataItems.size()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>