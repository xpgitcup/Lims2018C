package cn.edu.cup.dictionary

import javax.xml.crypto.Data

class DataItem {

    DataKey dataKey
    String dataValue

    static belongsTo = [upDataItem: DataItem]

    static hasMany = [subDataItems: DataItem]

    static mapping = {
        subDataItems sort: 'dataKey'
    }

    static constraints = {
        dataKey()
        dataValue(nullable: true)
        //upDataItem(nullable: true)
    }

    String toString() {
        if (dataKey?.dataUnit?.equals("无量纲")) {
            return "${dataKey}=${dataValue}"
        } else {
            return "${dataKey}=${dataValue}${dataKey?.dataUnit}"
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    def beforeInsert() {
        if (subDataItems) {
            dataValue = subDataItems[0].dataValue
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    def importFromDataTable(List dataTable) {
        subDataItems = []
        if (dataKey.subDataKeys.size() == dataTable.size()) {
            println("导入：${dataTable}")
            dataTable.eachWithIndex { Object entry, int i ->
                def subItem = new DataItem(dataKey: dataKey.subDataKeys[i], dataValue: entry, upDataItem: this)
                //subItem.save()
                subDataItems.add(subItem)
            }
        } else {
            println("${dataKey} ${dataTable} 不匹配！")
        }
    }
}
