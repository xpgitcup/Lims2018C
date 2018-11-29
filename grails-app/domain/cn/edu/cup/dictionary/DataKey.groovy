package cn.edu.cup.dictionary

import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

class DataKey {

    String dataTag                  //数据标签
    String dataUnit = '无量纲'      //数据单位
    String appendParameter = ''     //附加参数
    DataKeyType dataKeyType = DataKeyType.dataKeyNormal     //数据关键字类型
    String columnSeperator = ","   //列分割副
    int columnNumber = 1           //列数

    int orderNumber = 0         //顺序

    DataKey upDataKey

    static belongsTo = [dictionary: DataDictionary]

    static hasMany = [subDataKeys: DataKey]

    static mapping = {
        subDataKeys sort: 'orderNumber', 'id'
    }

    static constraints = {
        dataTag()
        dataUnit(nullable: true)
        appendParameter(nullable: true)
        dataKeyType()
        columnNumber()
        columnSeperator()
        orderNumber()
        upDataKey(nullable: true)
    }

    String toString() {
        //return "${dictionary}.${dataTag}.${subDataKeys?.size()}"
        if (subDataKeys) {
            return "${dictionary}.${dataTag}/(模型)"
        } else {
            return "${dictionary}.${dataTag}/(数据项)"
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //触发器
    def beforeInsert() {
        if (upDataKey) {
            dictionary = upDataKey.dictionary
            orderNumber = upDataKey.orderNumber
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /*
    * DataKey 输出成 数据表,
    * */
    def DataKey2DataTableSimple() {
        def dataTable = []
        subDataKeys.each { e->
            def item = []
            item.add(e.dataTag)
            item.add(e.dataKeyType)
            item.add(e.dataUnit)
            item.add("请在这里输入数据")
            dataTable.add(item)
        }
        return dataTable
    }

    //返回数据列数
    def enumItems() {
        def enumItems
        if (dataKeyType == DataKeyType.dataKeyEnum) {
            enumItems = this.appendParameter.split(columnSeperator)
        } else {
            enumItems = []
        }
        return enumItems
    }

    def columnCount() {
        def c = 0
        subDataKeys.each { e ->
            if (!e.isDataModel()) {
                c += e.columnNumber
            }
        }
        return c
    }

    //返回关键字的超类列表
    def superKeys() {
        def s = []
        def p = upDataKey
        while (p) {
            s.add(p)
            p = p.upDataKey
        }
        return s.reverse()
    }

    def isDataModel() {
        return this.subDataKeys.size() > 0
    }

    /**
     * 生成数据模板
     * */
    def createTemplate(String path) {

        def fileName = "${path}/${this.dataTag}_${this.id}.xls"

        try {
            //  打开文件
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${this.dataTag}", 0);

            /*
            * 整体的策略：
            *
            * 1.数据按列分布
            * 2.如果遇到数组，数组的标题就是appendParameter,逗号分隔
            * 3.
            * */
            Label label
            Label labelUnit
            def colIndex = 0
            if (isDataModel()) {
                //先处理继承的问题
                def ms = []
                def pe = this.upDataKey
                while (pe) {
                    ms.add(pe)
                    if (pe.isDataModel()) {
                        pe = pe.upDataKey
                    } else {
                        pe = null
                    }
                }
                println("${ms}")
                ms.reverse().each { me ->
                    me.subDataKeys.each() { it ->
                        if (!it.isDataModel()) {
                            colIndex = createCell4Field(it, colIndex, sheet)
                        }
                    }
                }
                //再处理本身
                this.subDataKeys.eachWithIndex() { e, i ->
                    println("${e}")
                    if (!e.isDataModel()) {
                        //只有具体的数据属性才出现在模板中
                        colIndex = createCell4Field(e, colIndex, sheet)
                    }
                }
            } else {
                label = new Label(0, 0, "这不是一个数据模型，无法生成模板")
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

    private int createCell4Field(DataKey e, int colIndex, Sheet sheet) {
        Label labelUnit
        Label label

        label = new Label(colIndex, 0, e.dataTag)
        labelUnit = new Label(colIndex, 1, e.dataUnit)
        sheet.addCell(label)
        sheet.addCell(labelUnit)

        if (e.refDataModel) {
            labelUnit = new Label(colIndex, 1, "{ref: ${e.refDataModel.id}}")
            sheet.addCell(labelUnit)
        }

        if (e.isEnumeration) {
            labelUnit = new Label(colIndex, 1, "{${e.appendParameter}}")
            sheet.addCell(labelUnit)
        }

        if (e.dimension > 1) {
            def ss = e.appendParameter.split(",")
            for (int i = 0; i < ss.size(); i++) {
                labelUnit = new Label(colIndex + i, 1, "{${ss[i]}}")
                sheet.addCell(labelUnit)
            }
        }

        colIndex += e.dimension

        return colIndex
    }

    /*
    * 导入数据
    * */

    def importFromExcelFile(File sf) {

        def message = []

        try {
            //  打开文件
            Workbook book = Workbook.getWorkbook(sf);
            //  首先查找对应的sheet
            Sheet sheet = book.getSheet("${this.dataTag}")

            if (sheet) {
                importDataFromSheet(sheet, message)
            } else {
                message.add("找不到对应的[${this.dataTag}]sheet.")
            }
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return message
    }

    /*
    * 从sheet中导入数据
    * */

    def importDataFromSheet(sheet, message) {
        def dataItem = new DataItem(dataKey: this)
        dataItem.save(true)
    }

}
