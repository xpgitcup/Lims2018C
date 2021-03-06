<html>
  <head>
    <meta name="layout" content="main" />
  </head>
  <body>
    <div id="list-dataItem" class="content scaffold-list" role="main">
      <h1>教师信息列表：</h1>
      <table>
        <g:each in="${dataItemList}" status="i" var="item">
          <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>
              <table>
                <tr>
                  <td>${item.id}</td>
                  <td>${item.dataKey.dataTag}</td>
                  <td>${item?.subDataItems?.size()}</td>
                </tr>
                <tr>
                  <td>${item?.subDataItems[0].id}</td>
                  <td>${item?.subDataItems[0].dataKey.dataTag}</td>
                  <td>${item?.subDataItems[0].dataValue}</td>
                </tr>
                <tr>
                  <td>${item?.subDataItems[1].id}</td>
                  <td>${item?.subDataItems[1].dataKey.dataTag}</td>
                  <td>${item?.subDataItems[1].dataValue}</td>
                </tr>
                <tr>
                  <td>${item?.subDataItems[2].id}</td>
                  <td>${item?.subDataItems[2].dataKey.dataTag}</td>
                  <td>${item?.subDataItems[2].dataValue}</td>
                </tr>
              </table>
            </td>
          </tr>
        </g:each>
      </table>
    </div>
  </body>
</html>
