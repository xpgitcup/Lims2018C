<%@ page contentType="text/html;charset=UTF-8" %>

<table class="home-chat">
    <thead>
    <td>IP</td>
    <td>用户</td>
    <td>时间</td>
    </thead>
    <tbody>
    <g:each in="${systemLogList}" status="i" var="item">
        <tr>
            <td>${item.hostIP}</td>
            <td>${item.userName}</td>
            <td>${item.actionDate}</td>
        </tr>
    </g:each>
    </tbody>
</table>
