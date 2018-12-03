<html>
  <head>
    <meta name="layout" content="main" />
  </head>
  <body>
    <div id="create-dataItem" class="content scaffold-create" role="main">
      <h1>静态生成的模板</h1>
      <form action="operation4Data/saveDataItem" method="post" enctype="multipart/form-data">
        <fieldset class="form">
          <table>
            <f:with bean="dataItem">
              <tr>
                <td>
                  <label>实验室信息管理/(30).实验信息/(数据项)</label>
                </td>
                <td>
                  <input type="hidden" name="dataKey.id" value="5" />
                </td>
              </tr>
            </f:with>
          </table>
          <table />
        </fieldset>
        <fieldset class="buttons">
          <input type="submit" name="create" class="save" value="Create" />
        </fieldset>
      </form>
    </div>
  </body>
</html>
