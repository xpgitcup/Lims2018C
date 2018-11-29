<%@ page import="jxl.write.DateTime" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItemA.label', default: 'DataItemA')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-dataItemA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div id="create-dataItem" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.dataItem}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.dataItem}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:uploadForm controller="operation4Data" action="saveDataItem">
        <fieldset class="form">
            <table>
                <f:with bean="dataItem">
                    <tr>
                        <td>
                            ${dataItem.dataKey}
                            <g:hiddenField name="dataKey.id" value="${dataItem.dataKey.id}"/>
                        </td>
                        <td>
                            <!--f:field property="dataValue"/-->
                        </td>
                    </tr>
                </f:with>
            </table>
            <table>
                <g:each in="${dataItem.subDataItems}" var="subItem" status="i">
                    <tr>
                        <td>
                            ${dataItem.subDataItems[i].dataKey.dataTag}
                            <g:hiddenField name="subDataItems[${i}].dataKey.id"
                                           value="${dataItem.subDataItems[i].dataKey.id}"/>
                            <g:hiddenField name="subDataItems[${i}].upDataItem.id"
                                           value="${dataItem.id}"></g:hiddenField>
                        </td>
                    <!--针对普通类型的-->
                        <g:if test="${subItem.dataKey.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyNormal}">
                            <td>
                                <g:textField name="subDataItems[${i}].dataValue" id="dataValue_${i}"/>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对时间日期类型的-->
                        <g:if test="${subItem.dataKey.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyDate}">
                            <td>
                                <!--g:textField name="subDataItems[${i}].dataValue" id=""/-->
                                <!--g:formatDate format="yyyy-MM-dd" date="${date}"/-->
                                <!--g:datePicker name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                                  value="${new Date()}"/-->
                                <!--input type="date" name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                       value="${new java.util.Date()}"/-->
                                <input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                       value="${new java.util.Date()}" class="datePicker"/>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对时间日期类型的-->
                        <g:if test="${subItem.dataKey.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyDateTime}">
                            <td>
                                <!--g:textField name="subDataItems[${i}].dataValue" id=""/-->
                                <!--g:formatDate format="yyyy-MM-dd" date="${date}"/-->
                                <!--g:datePicker name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                                  dateFmt="yyyy-MM-dd"
                                                  value="${new Date()}"/-->
                                <!--input type="datetime-local" name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                       value="${new java.util.Date()}"/-->
                                <!--input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                       value="${new java.util.Date()}" class="dateTimePicker"/-->
                                <input type="text" name="subDataItems[${i}].dataValue" id="dataValue_${i}"
                                       class="dateTimePicker"/>
                            </td>
                            <td>
                            </td>
                        </g:if>
                    <!--针对枚举类型的-->
                        <g:if test="${subItem.dataKey.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyEnum}">
                            <td>
                                <!--g:textField name="" id="dataValue_${i}"/-->
                                <g:select name="subDataItems[${i}].dataValue" from="${subItem.dataKey?.enumItems()}"
                                          noSelection="['': '-Choose-']"/>
                            </td>
                            <td>
                                <h1>附加信息：${subItem.dataKey.appendParameter}</h1>
                            </td>
                        </g:if>
                    <!--针对文件的-->
                        <g:if test="${subItem.dataKey.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyFile}">
                            <td>
                                <g:textField name="subDataItems[${i}].dataValue" id="file_${i}"/>
                            </td>
                            <td>
                                <g:hiddenField name="uploadedFilePath" value="${subItem.dataKey.appendParameter}"/>
                                <g:hiddenField name="uploadedFileDataKeyId" value="${subItem.dataKey.id}"/>
                                <g:hiddenField name="uploadedFileIndex" value="${i}"/>
                                <input type="file" name="uploadedFile" id="input_${i}"
                                       onchange="updateUploadFileName(${i})"/>
                            </td>
                        </g:if>
                    <!--针对引用类型的-->
                        <g:if test="${subItem.dataKey.dataKeyType == cn.edu.cup.dictionary.DataKeyType.dataKeyRef}">
                            <td>
                                <g:if test="${!subItem.dataKey.appendParameter}">
                                    <g:textField name="subDataItems[${i}].dataValue" id="dataValue_${i}"/>
                                </g:if>
                                <g:else>
                                    <g:select name="subDataItems[${i}].dataValue"
                                              from="${cn.edu.cup.dictionary.DataItem.findAllByDataKey(cn.edu.cup.dictionary.DataKey.get(Integer.parseInt(subItem.dataKey.appendParameter)))}"
                                              optionKey="id"
                                              noSelection="${['null': 'Select One...']}"/>
                                </g:else>
                            </td>
                            <td>
                                <h1>附加信息：${subItem.dataKey.appendParameter}</h1>
                            </td>
                        </g:if>
                    </tr>
                </g:each>
            </table>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
<!--/g:form-->
</div>
<g:javascript>
    processDateTime();
</g:javascript>
</body>
</html>
