package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataKey
import grails.gorm.transactions.Transactional

@Transactional
class Operation4DictionaryService {

    def commonService

    def functionConfigFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        //def tabNameListFile = servletContext.getRealPath("/") +
        return webRootPath + "commonData" + "/dataKey_${dataKey.id}.config"
    }

    def uploadFile4Import(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        return webRootPath + "/uploads4Import/${dataItem.dataKey.id}"
    }

    def uploadFilePath4DataItem(DataItem dataItem) {
        //def destDir = servletContext.getRealPath("/") + "uploads" + "/${dataItem.dataKey.id}"
        def webRootPath = commonService.webRootPath
        return webRootPath + "/uploads/${dataItem.dataKey.id}"
    }

    /*
    * 模型列表模板文件名
    * */

    def dataKeyImportTemplateFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        //def nowPath = this.class.getResource("/").getPath()
        //def controllerName = "userImportTemplates"//this.controllerName
        //return "${nowPath}${controllerName}/${dataKey.id}/dataKey_Import_${dataKey.id}.xls"
        return webRootPath + "userImportTemplates/${dataKey.id}/dataKey_Import_${dataKey.id}.xls"
    }

    def dataKeyListViewFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        //def nowPath = this.class.getResource("/").getPath()
        //def controllerName = "userViewTemplates"//this.controllerName
        //return "${nowPath}${controllerName}/${dataKey.id}/_dataKey_List_${dataKey.id}.gsp"
        return webRootPath + "userViewTemplates/${dataKey.id}/_dataKey_List_${dataKey.id}.gsp"
    }

    def dataKeyListViewTemplateName4Function(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        //def controllerName = "userViewTemplates"//this.controllerName
        //return "/${controllerName}/${dataKey.id}/dataKey_List_${dataKey.id}.gsp"
        return "/userViewTemplates/${dataKey.id}/dataKey_List_${dataKey.id}${session.fun}.gsp"
    }

    def dataKeyListViewTemplateName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        //def controllerName = "userViewTemplates"//this.controllerName
        //return "/${controllerName}/${dataKey.id}/dataKey_List_${dataKey.id}.gsp"
        return "/userViewTemplates/${dataKey.id}/dataKey_List_${dataKey.id}.gsp"
    }

    /*
    * 模型输入模板文件名
    * */

    def dataKeyInputViewFileName(DataKey dataKey) {
        def webRootPath = commonService.webRootPath
        //def nowPath = this.class.getResource("/").getPath()
        //def controllerName = "userViewTemplates"//this.controllerName
        //return "${nowPath}${controllerName}/${dataKey.id}/_dataKey_${dataKey.id}.gsp"
        return webRootPath + "userViewTemplates/${dataKey.id}/_dataKey_${dataKey.id}.gsp"
    }


}
