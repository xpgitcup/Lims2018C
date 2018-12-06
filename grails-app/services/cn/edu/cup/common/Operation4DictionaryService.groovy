package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import cn.edu.cup.system.SystemUser
import com.alibaba.fastjson.JSON
import grails.gorm.transactions.Transactional

@Transactional
class Operation4DictionaryService {

    def commonService
    def dataKeyConfig
    def dataItemService

    def loadMapFromFile(fileName) {
        def r = []
        def file = new File(fileName)
        if (file.exists()) {
            r.clear()
            r = JSON.parse(file.text)
        } else {
            r.add(["id1":"操作1"])
            r.add(["id2":"操作2"])
            def printWriter = new PrintWriter(file, "utf-8")
            printWriter.write(JSON.toJSONString(r))
            printWriter.close()
        }
        return r
    }

    def getRealName(SystemUser systemUser) {
        def name = ""
        def id = 0
        println("查找真实姓名....")
        if (!systemUser.appendAttribute.isEmpty()) {
            println("配置信息：${dataKeyConfig}")
            id = systemUser.appendAttribute.toInteger()
            if (id) {
                def dataItem = dataItemService.get(id)
                println("找到数据项：${dataItem} ${dataItem.dataKey.id}")
                def keyId = dataKeyConfig.get(dataItem.dataKey.id)
                println("对应：${keyId}")
                name = dataItem.subDataItems[keyId].dataValue
            }
        }
        return name
    }

    def getFunctionListFromProperties(Properties properties, key) {
        def fs = []
        def tmp = properties.getProperty(key)
        def ttmp
        if (tmp) {
            ttmp = tmp.split(",")
            ttmp.each { e->
                def ffs = e.split(":")
                fs.add(ffs)
            }
        }
        return fs
    }

    String[] getListFromProperties(Properties properties, key) {
        def tmp = properties.getProperty(key)
        def ttmp = []
        if (tmp) {
            ttmp = tmp.split(",")
        }
        return ttmp
    }

    def functionConfigFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "commonData" + "/dataKey_${dataKey.id}.config"
    }

    def uploadFile4Import(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "/uploads4Import/${dataKey.id}"
    }

    def uploadFilePath4DataItem(DataItem dataItem) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "/uploads/${dataItem.dataKey.id}"
    }

    /*
    * 模型列表模板文件名
    * */

    def dataKeyImportTemplateFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "userImportTemplates/${dataKey.id}/dataKey_Import_${dataKey.id}.xls"
    }

    def dataKeyListViewFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "userViewTemplates/${dataKey.id}/_dataKey_List_${dataKey.id}.gsp"
    }

    def dataKeyListViewTemplateName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return "/userViewTemplates/${dataKey.id}/dataKey_List_${dataKey.id}.gsp"
    }

    /*
    * 模型输入模板文件名
    * */

    def dataKeyInputViewFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "userViewTemplates/${dataKey.id}/_dataKey_${dataKey.id}.gsp"
    }


}
