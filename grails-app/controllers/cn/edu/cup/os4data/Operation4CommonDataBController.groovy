package cn.edu.cup.os4data

class Operation4CommonDataBController {

    def dataKeyService
    def commonService

    /*
    *  根据关键字列表，给出tab列表，当前记录列表，相应的功能列表
    *
    * */

    def index() {
        println("${this.class.name}:  ${params}")
        def tabNameList = []
        def topHrefList = []
        def entityName = "dataKey"
        def configName
        if (params.dataKey) {
            def keys = params.dataKey
            if (keys.getClass().array) {
                def dataKeys = []
                keys.each { e ->
                    def d = dataKeyService.get(e)
                    if (d) {
                        dataKeys.add(d)
                        entityName += "_${d.id}"
                        tabNameList.add("${d.dataTag}")
                    } else {
                        flash.message += "${e}关键字找不到。"
                    }
                }
            } else {
                def dataKey = dataKeyService.get(keys)
                if (dataKey) {
                    entityName = "dataKey_${dataKey.id}"
                } else {
                    flash.message = "找不到关键字：${keys}"
                }
            }
        } else {
            flash.message = "请设置正确的dataKey!"
        }

        configName = commonService.webRootPath + "commonData/" + entityName + ".config"
        println("${this.class.name} ${entityName} ${configName} ${tabNameList} ${topHrefList}")
        model:
        [
                entityName : entityName,
                configName : configName,
                tabNameList: tabNameList,
                topHrefList: topHrefList
        ]
    }
}
