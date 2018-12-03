package cn.edu.cup.os4data

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import grails.converters.JSON

class Operation4CommonDataAController {

    def operation4DictionaryService

    def count() {
        def dataKey = DataKey.get(session.commonDataKey)
        def count = DataItem.countByDataKey(dataKey)
        println("统计结果：${count} ${dataKey}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def list() {
        println("listDataItem: ${params}")
        def dataKey = DataKey.get(session.commonDataKey)
        def count = DataItem.countByDataKey(dataKey)
        def dataItemList
        def offset = Integer.parseInt(params.offset)
        if (count > offset) {
            dataItemList = DataItem.findAllByDataKey(dataKey, params)
        } else {
            dataItemList = DataItem.findAllByDataKey(dataKey)
        }
        println("查询结果：${dataKey} -- ${count}:  ${dataItemList}")
        //--------------------------------------------------------------------------------------------------------------
        def view = operation4DictionaryService.dataKeyListViewTemplateName4Function(dataKey)
        println("采用${view}")
        if (request.xhr) {
            render(template: view, model: [dataItemList: dataItemList])
        } else {
            respond dataItemList
        }
    }

    def index() {
        def commonTabList = []
        def commonIdList = []
        println("${params}")
        if (params.dataKey) {
            def dataKey = DataKey.get(params.dataKey)
            session.commonDataKey = params.dataKey
            session.fun = params.fun
            //def tabNameListFile = servletContext.getRealPath("/") + "commonData" + "/dataKey_${dataKey.id}.config"
            def tabNameListFile = operation4DictionaryService.functionConfigFileName(dataKey)
            def dataKeyFile = new File(tabNameListFile)
            if (dataKeyFile.exists()) {
                def tmp = dataKeyFile.text.split(" ")
                commonTabList.addAll(tmp)
                tmp.each { e->
                    commonIdList.add("id" + e)
                }
                println("${commonTabList} ${commonIdList}")
            }
        }
        model:[
                commonTabList: com.alibaba.fastjson.JSON.toJSONString(commonTabList),
                commonIdList: com.alibaba.fastjson.JSON.toJSONString(commonIdList)
        ]
    }
}
