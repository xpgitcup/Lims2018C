package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import grails.gorm.transactions.Transactional

@Transactional
class Operation4DictionaryService {

    def commonService

    String[] getListFromProperties(Properties properties, key) {
        def tmp = properties.getProperty(key)
        def ttmp = tmp.split(",")
        ttmp
    }

    def functionConfigFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "commonData" + "/dataKey_${dataKey.id}.config"
    }

    def uploadFile4Import(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "/uploads4Import/${dataItem.dataKey.id}"
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
