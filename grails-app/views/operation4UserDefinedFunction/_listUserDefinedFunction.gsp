<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'userDefinedFunction.label', default: 'UserDefinedFunction')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-userDefinedFunction" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

<div id="list-userDefinedFunction" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <!--f:table collection="${userDefinedFunctionList}"/-->
    <table>
        <thead>
        <th>id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Size</th>
        </thead>
        <tbody>
        <g:each in="${userDefinedFunctionList}" status="i" var="item">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><a href="javascript: selectAndTurnToNext(${item.id})">${item.id}</a></td>
                <td><a href="javascript: selectAndTurnToNext(${item.id})">${item.name}</a></td>
                <td>${item.description}</td>
                <td>${item.userClassLibrary?.size()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>