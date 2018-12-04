<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div id="list-dataItem" class="content scaffold-list" role="main">
    <h1>项目信息列表：</h1>
    <table>
        <thead>
        <th>Id</th>
        <th>名称</th>
        <th>类别</th>
        <th>甲方单位</th>
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
            </tr>
        </g:each>
    </table>
</div>
</body>
</html>
