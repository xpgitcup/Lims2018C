<g:set var="entityName" value="SystemChat"/>
<div id="show-systemChat" class="content scaffold-show" role="main">
    <h1>${entityName}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <f:display bean="systemChat"/>
<!--g:form resource="${this.systemChat}" method="DELETE"-->
    <g:form id="${this.systemChat?.id}" method="DELETE" action="delete" controller="operation4SystemChat">
        <fieldset class="buttons">
            <!--g:link class="edit" action="edit" resource="${this.systemChat}"-->
            <!--/g:link-->
            <a href="javascript: editSystemChat(${this.systemChat.id})">
                <g:message code="default.button.edit.label" default="Edit"/>
            </a>
            <input class="delete" type="submit"
                   value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                   onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
