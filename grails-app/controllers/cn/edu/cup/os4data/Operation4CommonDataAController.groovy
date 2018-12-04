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
        def view = session.currentView
        println("采用${view}")
        if (request.xhr) {
            render(template: view, model: [dataItemList: dataItemList])
        } else {
            respond dataItemList
        }
    }

    def index() {
        def currentFunction
        def commonTabList = []
        def commonIdList = []
        def jsFileName = ""
        println("index ${params}")
        if (params.dataKey) {
            def dataKey = DataKey.get(params.dataKey)
            session.commonDataKey = params.dataKey
            currentFunction = params.function
            jsFileName = "dataKey_${params.dataKey}"
            //def tabNameListFile = servletContext.getRealPath("/") + "commonData" + "/dataKey_${dataKey.id}.config"
            def tabNameListFile = operation4DictionaryService.functionConfigFileName(dataKey)
            def dataKeyFile = new File(tabNameListFile)
            if (dataKeyFile.exists()) {
                Properties properties = new Properties()
                def infs = new FileInputStream(dataKeyFile)
                properties.load(new InputStreamReader(infs, "utf-8"))
                if (properties) {
                    println(properties)
                    // 标签页的配置
                    commonTabList = operation4DictionaryService.getListFromProperties(properties, currentFunction + ".tabList")
                    commonIdList = operation4DictionaryService.getListFromProperties(properties, "${currentFunction}.idList")
                    println("${commonTabList} ${commonIdList}")
                    // 视图
                    def viewName = properties.getProperty("${currentFunction}.view")
                    session.currentView = "/userViewTemplates/${dataKey.id}/${viewName}.gsp"
                } else {
                    flash.message = "找不到配置信息..."
                }
            } else {
                flash.message = "找不到文件${tabNameListFile}。"
            }
        }
        model:
        [
                jsFileName   : jsFileName,
                ids          : commonIdList,
                commonTabList: com.alibaba.fastjson.JSON.toJSONString(commonTabList),
                commonIdList : com.alibaba.fastjson.JSON.toJSONString(commonIdList)
        ]
    }

}
