<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div id="list-dataItem" class="content scaffold-list" role="main">
    <h1>学生信息列表：管理</h1>
    <table>
        <thead>
        <th>Id</th>
        <th>姓名</th>
        <th>学号</th>
        <th>类型</th>
        <th>年级</th>
        <th>导师</th>
        <th>专业</th>
        </thead>
        <g:each in="${dataItemList}" status="i" var="item">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.id}</td>
                <td>
                    <a href="javascript: selectCurrentKey(${item.id})">
                        ${item?.subDataItems[0].dataValue}
                    </a>
                </td>
                <td>${item?.subDataItems[1].dataValue}</td>
                <td>${item?.subDataItems[2].dataValue}</td>
                <td>${item?.subDataItems[3].dataValue}</td>
                <td>${item?.subDataItems[5].dataValue}</td>
                <td>${item?.subDataItems[4].dataValue}</td>
            </tr>
        </g:each>
    </table>
</div>
</body>
</html>
