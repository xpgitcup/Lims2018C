package cn.edu.cup.os4data

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import cn.edu.cup.system.SystemUser
import grails.converters.JSON

class Operation4CommonDataBController {

    def dataKeyService
    def commonService
    def systemUserService
    def operation4DictionaryService

    def removeFromUserGrade() {
        def dataKey = dataKeyService.get(params.dataKey)
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
                        users.each { eu ->
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
        redirect(action: "index", params: [dataKey: "${session.dataKey}", function: "${session.function}"])
    }

    /*
    *  添加到用户
    * */

    def import2systemUserGrade() {
        def dataKey = dataKeyService.get(params.dataKey)
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
                        if (SystemUser.countByUserName(student.subDataItems[1].dataValue) < 1) {
                            def nuser = new SystemUser(
                                    userName: student.subDataItems[1].dataValue,
                                    password: "12345678",
                                    roleAttribute: "学生服务",
                                    appendAttribute: "${student.id}"
                            )
                            systemUserService.save(nuser)
                            c++
                        } else {
                            zc++
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
        redirect(action: "index", params: [dataKey: "${session.dataKey}", function: "${session.function}"])
    }

    /*
    * 列表
    * */

    def list() {
        println("listDataItem: ${params}")
        def dataKey = dataKeyService.get(params.dataKey)
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
        //def view = "/userViewTemplates/${dataKey.id}/${params.functionName}.gsp"
        def view = session.viewName
        println("采用${view}")
        if (request.xhr) {
            render(template: view, model: [dataItemList: dataItemList])
        } else {
            respond dataItemList
        }
    }

    /*
    * 统计
    * */

    def count() {
        println("listDataItem: ${params}")
        def dataKey = dataKeyService.get(params.dataKey)
        def count = DataItem.countByDataKey(dataKey)
        println("统计结果：${count} ${dataKey}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    *  根据关键字列表，给出tab列表，当前记录列表，相应的功能列表
    * */

    def index() {
        //println("${this.class.name}:  ${params}")
        def tabNameList = []
        def topHrefList
        def idList = []
        def entityName = "dataKey"
        def configName
        def dataKeys = params.dataKey
        if (params.dataKey) {
            def keys = params.dataKey
            if (keys.getClass().array) {
                keys.each { e ->
                    def d = dataKeyService.get(e)
                    if (d) {
                        entityName += "_${d.id}"
                        tabNameList.add(d.dataTag)
                    } else {
                        flash.message += "${e}关键字找不到。"
                    }
                }
            } else {
                def dk = dataKeyService.get(keys)
                if (dk) {
                    entityName = "dataKey_${dk.id}"
                    tabNameList.add(dk.dataTag)
                } else {
                    flash.message = "找不到关键字：${keys}"
                }
            }
        } else {
            flash.message = "请设置正确的dataKey!"
        }
        // 最终形成每个功能的关键字
        entityName += params.function
        configName = commonService.webRootPath + "commonData/" + entityName + ".config"
        topHrefList = operation4DictionaryService.loadMapFromFile(configName)
        topHrefList.each { e ->
            e.each { ee->
                if (ee.value=='#') {
                    idList.add(ee.key)
                }
            }
        }
        //记录在session中
        session.dataKey = params.dataKey
        session.function = params.function
        session.viewName = "/userViewTemplates/" + entityName + ".gsp"

        // 输出，返回
        println("${this.class.name} ${entityName} ${configName} ${tabNameList} ${topHrefList}")
        model:
        [
                entityName  : entityName,
                configName  : configName,
                tabNameList : com.alibaba.fastjson.JSON.toJSONString(tabNameList),
                topHrefList : topHrefList,
                idList      : com.alibaba.fastjson.JSON.toJSONString(idList),
                dataKeys    : com.alibaba.fastjson.JSON.toJSONString(dataKeys),
                functionName: params.function
        ]
    }
}
