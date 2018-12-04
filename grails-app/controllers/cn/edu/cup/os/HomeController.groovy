package cn.edu.cup.os

import cn.edu.cup.system.JsFrame
import cn.edu.cup.system.SystemChat
import cn.edu.cup.system.SystemLog
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemTitle
import cn.edu.cup.system.SystemUser
import grails.converters.JSON
import groovy.json.JsonOutput

class HomeController {

    def systemCommonService
    def treeViewService
    def operation4DictionaryService

    /*
    * 列出日志对象
    * */

    def listSystemLog() {
        def systemLogList = SystemLog.list(params)
        println("${systemLogList}")
        if (request.xhr) {
            render(template: 'listSystemLog', model: [systemLogList: systemLogList])
        } else {
            respond systemLogList
        }
    }

    /*
    * 列出会话对象
    * */

    def listSystemChat() {
        def systemChatList = SystemChat.list(params)
        println("${systemChatList}")
        if (request.xhr) {
            render(template: 'listSystemChat', model: [systemChatList: systemChatList])
        } else {
            respond systemChatList
        }
    }

    /*
    * 获取系统菜单
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
    * 获取系统菜单
    * */

    def private getSystemMenuTreeMap(SystemMenu systemMenu) {
        def data = systemMenu.menuItems
        println("查询---菜单${data}")
        params.context = "hrefContext"
        params.subItems = "menuItems"
        params.attributes = "id"    //
        params.useMethod = true
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        return result
    }

    /*
    * 列出系统菜单
    * */

    private void listSystemMenu() {
        //根据用户的属性，设置菜单
        params.user = session.systemUser
        def systemMenuList = systemCommonService.getAllTopLevelMenus(params)

        //新思路
        def systemMenuListAtHome = []
        systemMenuList.each { item ->
            def arrayItem = [:]

            //第一层
            arrayItem.put("panelName", item.menuContext)

            //第二层
            def itemName = "systemMenuTree${item.id}"
            arrayItem.put("treeDivName", itemName)

            //具体的树内容
            //arrayItem.put("treeData", item.id)
            arrayItem.put("treeData", getSystemMenuTreeMap(item))
            systemMenuListAtHome.add(arrayItem)
            //println("${arrayItem}")
        }
        //--------------------------------------------------------------------------------------------------------------
        //输出json字符串
        def jsonOutput = new JsonOutput()
        def json = jsonOutput.toJson(systemMenuListAtHome)
        session.systemMenuListAtHome = json
        //println("session: ${session.systemMenuListAtHome}")
    }

    /*
    * 退出登录
    * */

    def logout() {
        def pscontext = request.session.servletContext
        Map serviceMap = pscontext.getAttribute("systemUserList")
        if (session.systemUser) {
            systemCommonService.recordLog(session, request, params)
            serviceMap.remove(session.systemUser.userName)
            println("${session.systemUser.userName}退出...")
        }
        session.onlineCount = serviceMap.size()
        def logoutUser = session.systemUser.userName
        session.invalidate()
        //redirect(uri: "/")
        println("拜拜...${logoutUser}")
        model:
        [logoutUser: logoutUser]
    }

    /*
    * 登录
    * */

    def login() {
        String userName = params.userName;
        String p = params.password.encodeAsMD5()
        def systemUser = SystemUser.findByUserNameAndPassword(userName, p)
        if (systemUser) {
            println("找到了：${systemUser}")
            session.systemUser = systemUser
            //初始化用户菜单
            listSystemMenu()
            //查找真实的用户名
            session.realName = operation4DictionaryService.getRealName(systemUser)
            //在会话中登记用户
            registeUserInSession(systemUser)
            systemCommonService.recordLog(session, request, params)
            //设置应用程序的布局
            def layout = SystemTitle.last().applicationLayout
            if (layout) {
                session.layout = layout
            }
            redirect(uri: "/home")
        } else {
            flash.message = "用户名或密码错误！"
            println("用户名或密码错误！")
            redirect(uri: "${createLink(uri: '/')}")
        }
    }

    /*
    * 显示登录界面
    * */

    def loginUI() {}

    /*
    * 登记登录用户
    * */

    def registeUserInSession(user) {
        def pscontext = request.session.servletContext
        Map serviceMap = pscontext.getAttribute("systemUserList")
        if (!serviceMap) {
            def systemUserList = new HashMap()
            pscontext.putAt("systemUserList", systemUserList)
            serviceMap = systemUserList
        }
        //登记用户
        serviceMap.putAt(user.userName, session)

        systemCommonService.updateSystemUserList(request)
    }

    def index() {
        def messageToMe = SystemChat.findAllBySpeakTo("${session.systemUser}", max: 5)
        println("交流信息：  ${messageToMe}")
        model:
        [
                messageToMe: messageToMe
        ]
    }
}
