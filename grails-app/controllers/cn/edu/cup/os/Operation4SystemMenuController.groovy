package cn.edu.cup.os

import cn.edu.cup.system.JsFrame
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemMenuController
import grails.converters.JSON

class Operation4SystemMenuController extends SystemMenuController {

    def treeViewService
    def commonService

    /*
    * 导出文件json
    * 如果是空，导出整个数据表
    * 如果不是，导出这一枝
    * */

    def exportToJsonFile(SystemMenu systemMenu) {

        def fileName = commonService.menuConfigFileName()

        def systemMenuList = []
        if (systemMenu) {
            addMenuItemToList(systemMenu, systemMenuList)
        } else {
            SystemMenu.findAllByUpMenuItemIsNull().each { e -> addMenuItemToList(e, systemMenuList) }
        }
        println(systemMenuList)

        def fjson = com.alibaba.fastjson.JSON.toJSONString(systemMenuList)
        println("FastJson:")
        println(fjson)

        def printer = new File(fileName).newPrintWriter('utf-8')    //写入文件
        printer.println(fjson)
        printer.close()

        redirect(action: 'index')
    }

    private void addMenuItemToList(SystemMenu systemMenu, List systemMenuList) {
        def item = [:]
        if (systemMenu.menuContext != "底层管理") {
            item.menuContext = systemMenu.menuContext
            item.menuAction = systemMenu.menuAction
            item.menuDescription = systemMenu.menuDescription
            item.upMenuItem = systemMenu.upMenuItem?.menuContext
            item.roleAttribute = systemMenu.roleAttribute
            item.layout = systemMenu.layout
            item.menuOrder = systemMenu.menuOrder
            if (systemMenu.menuItems) {
                def subList = []
                item.menuItems = subList
                systemMenu.menuItems.each { e ->
                    addMenuItemToList(e, subList)
                }
            }
            systemMenuList.add(item)
        }
    }

    /*
    * 创建对象
    * */

    def createSystemMenu(SystemMenu systemMenu) {
        def newSystemMenu = new SystemMenu(upMenuItem: systemMenu)
        if (request.xhr) {
            render(template: 'editSystemMenu', model: [systemMenu: newSystemMenu])
        } else {
            respond newSystemMenu
        }
    }

    /*
    * 保存对象
    * */

    def updateSystemMenu(SystemMenu systemMenu) {
        println("准备更新：${systemMenu}")
        //systemMenu.save flush:true
        systemMenuService.save(systemMenu)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editSystemMenu(SystemMenu systemMenu) {
        if (request.xhr) {
            render(template: 'editSystemMenu', model: [systemMenu: systemMenu])
        } else {
            respond systemMenu
        }
    }

    /*
    * 统计根属性
    * */

    def countSystemMenu() {
        def count = SystemMenu.countByUpMenuItemIsNull()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
        //return count //就是不行
    }

    /*
    * 获取当前id对应的对象
    * */

    def getSystemMenu(SystemMenu systemMenu) {
        def theModel = [systemMenu: systemMenu]
        if (request.xhr) {
            render(template: "showSystemMenu", model: theModel)
        } else {
            theModel
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getSystemMenuTree(SystemMenu systemMenu) {
        def data = systemMenu.menuItems
        println("查询---菜单${data}")
        params.context = "hrefContext"
        params.subItems = "menuItems"
        params.attributes = "id"    //
        params.useMethod = true
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 获取json格式的树形结构数据
    * */

    def getTreeSystemMenu() {
        def data = SystemMenu.findAllByUpMenuItemIsNull(params)     //这是必须调整的
        params.context = "menuContext"
        params.subItems = "menuItems"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() {}
}
