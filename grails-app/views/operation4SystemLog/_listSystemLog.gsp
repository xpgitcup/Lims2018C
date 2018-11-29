<table>
    <thead>
    <th>会话</th>
    <th>IP</th>
    <th>用户</th>
    <th>时间</th>
    <th>行为</th>
    </thead>
    <tbody>
    <g:each in="${systemLogList}" status="i" var="item">
        <tr>
            <td>
                <a href="javascript: showSystemLog(${item.id})">
                    ${item.sessionId}
                </a>
            </td>
            <td>${item.hostIP}</td>
            <td>${item.userName}</td>
            <td>${item.actionDate}</td>
            <td>${item.doing}</td>
        </tr>
    </g:each>
    </tbody>
</table>
