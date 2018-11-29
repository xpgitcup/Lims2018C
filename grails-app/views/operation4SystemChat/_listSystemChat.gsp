<%@ page contentType="text/html;charset=UTF-8" %>

<table class="home-chat">
    <thead>
    <th>speaker</th>
    <th>speakTo</th>
    <th>message</th>
    <th>状态</th>
    <th>时间</th>
    </thead>
    <g:each in="${systemChatList}" var="item" status="i">
        <tr>
            <td>${item.speaker}</td>
            <td>${item.speakTo}</td>
            <td>${item.message}</td>
            <td>
                <g:if test="${!item.haveRead}">
                    <a href="operation4SystemChat/checkIt/${item.id}">标记已读</a>
                </g:if>
                <g:else>
                    ${item.haveRead}
                </g:else>
            </td>
            <td>${item.startTime}</td>
        </tr>
    </g:each>
</table>
