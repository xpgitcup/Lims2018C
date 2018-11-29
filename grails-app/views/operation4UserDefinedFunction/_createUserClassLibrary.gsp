<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'userClassLibrary.label', default: 'UserClassLibrary')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>

    <g:javascript>
        function updateUploadFileName() {
            var aainput = document.getElementById("afile");
            var aafile = document.getElementById("ainput");
            var fn = aainput.value
            var k = fn.lastIndexOf('/')
            console.info(aainput);
            console.info(aainput.files.length);
            console.info(aafile);
            aafile.value = aainput.files[0].name;
            console.info(aainput.value);
        }
</g:javascript>
</head>

<body>
<div id="create-userClassLibrary" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.userClassLibrary}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.userClassLibrary}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.userClassLibrary}" method="POST"-->
    <g:uploadForm controller="operation4UserDefinedFunction" action="saveUserClassLibrary"
                  id="${this.userClassLibrary.id}">
        <fieldset class="form">
            <!--f:all bean="userClassLibrary"/-->
            <table>
                <tr>
                    <td><label>Name</label></td>
                    <td><input type="text" name="name"/></td>
                    <td><label>Description</label></td>
                    <td><input type="text" name="description"/></td>
                </tr>
                <tr>
                    <td><label>Developer</label></td>
                    <td><input type="text" name="developer"/></td>
                    <td><label>Upload Date</label></td>
                    <td><input type="text" name="uploadDate"/></td>
                </tr>
                <tr>
                    <td><label>File Name</label></td>
                    <td><input type="text" name="fileName" id="ainput" value=""/></td>
                    <td><input type="file" name="uploadedFile" id="afile" onchange="updateUploadFileName()"/></td>
                </tr>
                <tr>
                    <td><label>Usr Defined Function</label></td>
                    <td><input type="text" name="userDefinedFunction" value="${this.userClassLibrary.userDefinedFunction.id}"/></td>
                </tr>
            </table>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
</div>
</body>
</html>
