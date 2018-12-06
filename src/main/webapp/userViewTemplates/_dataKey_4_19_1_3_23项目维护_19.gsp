<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div id="list-dataItem" class="content scaffold-list" role="main">
    <h1>项目管理列表：</h1>
    <table>
        <thead>
        <th>Id</th>
        <th>项目</th>
        <th>参与教师</th>
        <th>参与学生</th>
        <th>进展情况</th>
        </thead>
        <tbody>
        <g:each in="${dataItemList}" status="i" var="item">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.id}</td>
                <td>
                    ${item?.dataValue}
                </td>
                <td>${item?.subDataItems[0].dataValue}</td>
                <td>${item?.subDataItems[1].dataValue}</td>
                <td>${item?.subDataItems[2].dataValue}</td>
                <td>${item?.subDataItems[3].dataKey.dataTag}</td>
                <td>${item?.subDataItems[3].dataValue}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>
