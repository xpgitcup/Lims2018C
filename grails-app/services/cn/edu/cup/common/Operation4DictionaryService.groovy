package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import grails.gorm.transactions.Transactional

@Transactional
class Operation4DictionaryService {

    def commonService

    def uploadPath(DataItem dataItem) {
        return "????"
    }

    /*
    * 模型列表模板文件名
    * */

    def dataKeyImportTemplateFileName(DataKey dataKey) {
        def nowPath = this.class.getResource("/").getPath()
        def controllerName = "userImportTemplates"//this.controllerName
        return "${nowPath}${controllerName}/${dataKey.id}/dataKey_Import_${dataKey.id}.xls"
    }

    def dataKeyListViewFileName(DataKey dataKey) {
        def nowPath = this.class.getResource("/").getPath()
        def controllerName = "userViewTemplates"//this.controllerName
        return "${nowPath}${controllerName}/${dataKey.id}/_dataKey_List_${dataKey.id}.gsp"
    }

    def dataKeyListViewTemplateName(DataKey dataKey) {
        def controllerName = "userViewTemplates"//this.controllerName
        return "/${controllerName}/${dataKey.id}/dataKey_List_${dataKey.id}.gsp"
    }

    /*
    * 模型输入模板文件名
    * */

    def dataKeyInputViewFileName(DataKey dataKey) {
        def nowPath = this.class.getResource("/").getPath()
        def controllerName = "userViewTemplates"//this.controllerName
        return "${nowPath}${controllerName}/${dataKey.id}/_dataKey_${dataKey.id}.gsp"
    }


}
