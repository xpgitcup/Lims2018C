<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataDictionary.label', default: 'DataDictionary')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-dataDictionary" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="edit-dataDictionary" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.dataDictionary}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.dataDictionary}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <!--g:form resource="${this.dataDictionary}" method="PUT"-->
            <g:form controller="operation4Data" action="updateDataDictionary" id="${this.dataDictionary?.id}">
                <g:hiddenField name="version" value="${this.dataDictionary?.version}" />
                <fieldset class="form">
                    <f:all bean="dataDictionary"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
