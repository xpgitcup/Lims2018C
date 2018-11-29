package cn.edu.cup.os

import cn.edu.cup.system.SystemUser
import cn.edu.cup.system.SystemUserController
import grails.converters.JSON

class Operation4SystemUserController extends SystemUserController {

    /*
    * 列出对象
    * */
    def listSystemUser() {
        def systemUserList = SystemUser.list(params)
        if (request.xhr) {
            render(template: 'listSystemUser', model: [systemUserList: systemUserList])
        } else {
            respond newSystemUser
        }
    }

    /*
    * 创建对象
    * */
    def createSystemUser(SystemUser systemUser) {
        def newSystemUser = new SystemUser()
        if (request.xhr) {
            render(template: 'editSystemUser', model: [systemUser: newSystemUser])
        } else {
            respond newSystemUser
        }
    }

    /*
    * 保存对象
    * */
    def updateSystemUser(SystemUser systemUser) {
        println("准备更新：${systemUser}")
        //systemUser.save flush:true
        systemUserService.save(systemUser: systemUser)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */
    def editSystemUser(SystemUser systemUser) {
        if (request.xhr) {
            render(template: 'editSystemUser', model: [systemUser: systemUser])
        } else {
            respond SystemUser
        }
    }

    /*
    * 统计根属性
    * */
    def countSystemUser() {
        def count = SystemUser.count()    //这是必须调整的
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
    def getSystemUser(SystemUser systemUser) {
        def theModel = [systemUser: systemUser]
        println("${systemUser}")
        if (request.xhr) {
            render(template: "showSystemUser", model:theModel)
        } else {
            theModel
        }
    }

    def index() { }
}
