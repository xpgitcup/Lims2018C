<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'systemChat.label', default: 'systemChat')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<a href="#edit-systemChat" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div id="edit-systemChat" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.systemChat}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.systemChat}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.systemChat}" method="PUT"-->
    <g:form action="updateSystemChat" controller="operation4SystemChat">
        <!--这是增加的一行-->
        <g:hiddenField name="id" value="${this.systemChat?.id}" />
        <g:hiddenField name="version" value="${this.systemChat?.version}" />
        <fieldset class="form">
            <f:all bean="systemChat"/>
        </fieldset>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
