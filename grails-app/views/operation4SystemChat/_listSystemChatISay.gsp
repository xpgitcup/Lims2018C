<%@ page contentType="text/html;charset=UTF-8" %>

<table class="home-chat">
    <thead>
    <th>speaker</th>
    <th>speakTo</th>
    <th>message</th>
    <th>状态</th>
    <th>反馈</th>
    </thead>
    <g:each in="${systemChatList}" var="item" status="i">
        <tr>
            <td>${item.speaker}</td>
            <td>${item.speakTo}</td>
            <td>${item.message}</td>
            <td>
                ${item.haveRead}
            </td>
            <td>${item.subTopics}</td>
        </tr>
    </g:each>
</table>
