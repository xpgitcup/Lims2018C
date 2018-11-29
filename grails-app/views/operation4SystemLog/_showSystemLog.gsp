<g:set var="entityName" value="SystemLog"/>
<div id="show-systemLog" class="content scaffold-show" role="main">
    <h1>${entityName}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="systemLog"/>
<!--g:form resource="${this.systemLog}" method="DELETE"-->
    <g:form id="${this.systemLog?.id}" method="DELETE" action="delete" controller="operation4SystemLog">
        <fieldset class="buttons">
            <!--g:link class="edit" action="edit" resource="${this.systemLog}"-->
            <!--/g:link-->
            <a href="javascript: editSystemLog(${this.systemLog.id})">
                <g:message code="default.button.edit.label" default="Edit"/>
            </a>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
