<%@ page contentType="text/html;charset=UTF-8" %>

<table class="home-chat">
    <thead>
    <td>speaker</td>
    <td>speakTo</td>
    <td>message</td>
    </thead>
    <g:each in="${systemChatList}" var="item" status="i">
        <tr>
            <td>${item.speaker}</td>
            <td>${item.speakTo}</td>
            <td>${item.message}</td>
        </tr>
    </g:each>
</table>
