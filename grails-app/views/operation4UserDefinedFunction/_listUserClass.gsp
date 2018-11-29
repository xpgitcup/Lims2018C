<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'userClass.label', default: 'UserClass')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-userClass" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div id="list-userClass" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
<!--f:table collection="${userClassList}" /-->
    <table>
        <thead>
        <th>id</th>
        <th>类名</th>
        <th>说明</th>
        <th>关联模型</th>
        <th>方法数</th>
        <th>所属库</th>
        </thead>
        <tbody>
        <g:each in="${userClassList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.id}</td>
                <td><a href="operation4UserDefinedFunction/testClass/${item.id}">${item.name}</a></td>
                <td>${item.description}</td>
                <td>
                    <g:if test="${!item.baseOn}">
                        <g:form action="saveUserClass" method="post">
                            <div class="nav">
                                <ul>
                                    <li>
                                        <input type="hidden" name="id" value="${item.id}"/>
                                        <input type="hidden" name="name" value="${item.name}"/>
                                    </li>
                                    <li>
                                        <g:select from="${cn.edu.cup.dictionary.DataKey.list()}" name="baseOn" optionKey="id" optionValue="dataTag"/>
                                    </li>
                                    <li>
                                        <input type="submit" value="update">
                                    </li>
                                </ul>
                            </div>
                        </g:form>
                    </g:if>
                    <g:else>
                        ${item.baseOn}
                    </g:else>
                </td>
                <td>${item?.userClassMethod.size()}</td>
                <td>${item.userClassLibrary}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>