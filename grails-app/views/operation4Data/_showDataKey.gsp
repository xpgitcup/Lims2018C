<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataKey.label', default: 'DataKey')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-dataKey" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="show-dataKey" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="dataKey" />
            <!--g:form resource="${this.dataKey}" method="DELETE" controller="operation4Data"-->
            <g:form controller="operation4Data" action="deleteDataKey" id="${this.dataKey.id}">
                <fieldset class="buttons">
                    <a href="javascript: editDataKey(${this.dataKey.id})">Edit</a>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    <a href="/operation4Data/index" class="home">返回</a>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
