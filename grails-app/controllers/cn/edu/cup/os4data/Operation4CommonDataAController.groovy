package cn.edu.cup.os4data

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import cn.edu.cup.system.SystemUser
import grails.converters.JSON

class Operation4CommonDataAController {

    def operation4DictionaryService
    def systemUserService

    def removeFromUserGrade() {
        def dataKey = DataKey.get(session.commonDataKey)
        if (params.filterKey) {
            def filterKey = DataKey.get(params.filterKey)
            if (filterKey) {
                def filter = params.filter
                def dataItemList = DataItem.findAllByDataKeyAndDataValue(filterKey, filter)
                def c = 0
                dataItemList.each { e ->
                    def student = e.upDataItem
                    if (student.dataKey == dataKey) {
                        def users = SystemUser.findAllByUserName(student.subDataItems[1].dataValue)
                        users.each { eu->
                            systemUserService.delete(eu.id)
                            c++
                        }
                    }
                }
                flash.message = "${c}位同学毕业了！"
            } else {
                flash.message = "筛选关键字错误${params.filterKey}!"
            }
        } else {
            flash.message = "没有筛选关键字!"
        }
        redirect(action: "index", params: [dataKey: "${session.commonDataKey}", function: "${session.currentFunction}"])
    }

    def import2systemUserGrade() {
        def dataKey = DataKey.get(session.commonDataKey)
        if (params.filterKey) {
            def filterKey = DataKey.get(params.filterKey)
            if (filterKey) {
                def filter = params.filter
                def dataItemList = DataItem.findAllByDataKeyAndDataValue(filterKey, filter)
                def c = 0
                def zc = 0
                def zcs = ""
                dataItemList.each { e ->
                    def student = e.upDataItem
                    if (student.dataKey == dataKey) {
                        if (SystemUser.countByUserName(student.subDataItems[1].dataValue)<1) {
                            def nuser = new SystemUser(
                                    userName: student.subDataItems[1].dataValue,
                                    password: "12345678",
                                    roleAttribute: "学生服务",
                                    appendAttribute: "${student.id}"
                            )
                            systemUserService.save(nuser)
                            c++
                        } else {
                            zc ++
                            zcs += student.subDataItems[1].dataValue + "/"
                        }
                    }
                }
                flash.message = "成功导入${c}位同学！${zc} ${zcs}同学已经存在了."
            } else {
                flash.message = "筛选关键字错误${params.filterKey}!"
            }
        } else {
            flash.message = "没有筛选关键字!"
        }
        redirect(action: "index", params: [dataKey: "${session.commonDataKey}", function: "${session.currentFunction}"])
    }

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
        def commonFunctionList
        def jsFileName = ""
        println("index ${params}")
        if (params.dataKey) {
            def dataKey = DataKey.get(params.dataKey)
            session.commonDataKey = params.dataKey
            session.currentFunction = params.function
            currentFunction = params.function
            jsFileName = "dataKey_${params.dataKey}"
            //def tabNameListFile = servletContext.getRealPath("/") + "commonData" + "/dataKey_${dataKey.id}.config"
            def configFileName = operation4DictionaryService.functionConfigFileName(dataKey)
            def dataKeyFile = new File(configFileName)
            if (dataKeyFile.exists()) {
                Properties properties = new Properties()
                def infs = new FileInputStream(dataKeyFile)
                properties.load(new InputStreamReader(infs, "utf-8"))
                if (properties) {
                    println(properties)
                    // 标签页的配置
                    commonTabList = operation4DictionaryService.getListFromProperties(properties, currentFunction + ".tabList")
                    commonIdList = operation4DictionaryService.getListFromProperties(properties, "${currentFunction}.idList")
                    commonFunctionList = operation4DictionaryService.getFunctionListFromProperties(properties, "${currentFunction}.functions")
                    println("${commonTabList} ${commonIdList}")
                    // 视图
                    def viewName = properties.getProperty("${currentFunction}.view")
                    session.currentView = "/userViewTemplates/${dataKey.id}/${viewName}.gsp"
                } else {
                    flash.message = "找不到配置信息..."
                }
            } else {
                flash.message = "找不到文件${configFileName}。"
            }
        }
        model:
        [
                jsFileName        : jsFileName,
                ids               : commonIdList,
                commonFunctionList: commonFunctionList,
                commonTabList     : com.alibaba.fastjson.JSON.toJSONString(commonTabList),
                commonIdList      : com.alibaba.fastjson.JSON.toJSONString(commonIdList)
        ]
    }

}
