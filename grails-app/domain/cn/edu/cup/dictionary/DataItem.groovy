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
}
