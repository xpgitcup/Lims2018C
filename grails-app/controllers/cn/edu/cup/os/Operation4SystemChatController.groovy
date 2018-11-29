package cn.edu.cup.os

import cn.edu.cup.system.SystemChat
import cn.edu.cup.system.SystemChatController
import cn.edu.cup.system.SystemUser
import grails.converters.JSON

class Operation4SystemChatController extends SystemChatController {

    /*
    * 标记已读
    * */

    def checkIt(SystemChat systemChat) {
        systemChat.haveRead = true
        //systemChat.save(true)
        systemChatService.save(systemChat)
        redirect(action: "index")
    }

    /*
    * 统计记录个数
    * */

    def count() {
        def user = session.systemUser.userName
        def count = 0
        def title = params.title
        if (title) {
            switch (title) {
                case "我在听":
                    count = SystemChat.countBySpeakToAndHaveRead(user, false)
                    break;
                case "我在说":
                    count = SystemChat.countBySpeakerAndHaveRead(user, false)
                    break;
                case "整体显示":
                    count = SystemChat.countByHaveRead(false)
                    break;
                case "对话":
                    count = SystemChat.countBySpeakerOrSpeakTo(user, user)
                    break;
            }
        } else {
            count = SystemChat.countBySpeakerAndHaveRead(user,false)
        }
        println("统计结果：${count} ${user}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列出要求的记录
    * */

    def list() {
        def user = session.systemUser.userName
        println("用户${user}的对话信息：")
        def systemChatList
        def title = params.title
        if (title) {
            switch (title) {
                case "我在听":
                    systemChatList = SystemChat.findAllBySpeakToAndHaveRead(user, false, params)
                    break;
                case "我在说":
                    systemChatList = SystemChat.findAllBySpeakerAndHaveRead(user, false, params)
                    break;
                case "整体显示":
                    systemChatList = SystemChat.findAllByHaveRead(false, params)
                    break;
                case "对话":
                    systemChatList = SystemChat.findAllBySpeakerOrSpeakTo(user, user, params)
                    break;
            }
        } else {
            systemChatList = SystemChat.findAllBySpeakerAndHaveRead(user,false, params)
        }
        systemChatList.sort({ startTime: 'desc' })
        println("${systemChatList}")
        if (request.xhr) {
            render(template: 'listSystemChat', model: [systemChatList: systemChatList])
        } else {
            respond systemChatList
        }
    }

    /*
    * 列出我的发言
    * */

    def listSystemChatISay() {
        def user = session.systemUser.userName
        def systemChatList = SystemChat.findAllBySpeakerAndHaveRead(user, false, params)
        systemChatList.sort({ startTime: 'desc' })
        if (request.xhr) {
            render(template: 'listSystemChatISay', model: [systemChatList: systemChatList])
        } else {
            respond systemChatList
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
    * 列出我在听
    * */

    def listSystemChatListening() {
        println("我在听：${params}")
        def user = session.systemUser.userName
        def systemChatList = SystemChat.findAllBySpeakToAndHaveRead(user, false, params)
        if (request.xhr) {
            render(template: 'listSystemChatListening', model: [systemChatList: systemChatList])
        } else {
            respond systemChatList
        }
    }

    /*
    * 列出对象
    * */

    def listSystemChat() {
        def systemChatList = SystemChat.list(params)
        if (request.xhr) {
            render(template: 'listSystemChat', model: [systemChatList: systemChatList])
        } else {
            respond systemChatList
        }
    }

    /*
    * 创建对象
    * */

    def createSystemChat(SystemChat systemChat) {
        def user = session.systemUser.userName
        def userList = []
        SystemUser.list().each { e ->
            userList.add(e.userName)
        }
        def k = userList.indexOf(user)
        println("查找...${k}")
        if (k > -1) {
            println("删除${k}")
            userList.remove(k)
        }
        def newSystemChat = new SystemChat(speaker: user)
        if (systemChat) {
            newSystemChat.upTopic = systemChat
            newSystemChat.speakTo = systemChat.speaker
        }
        if (request.xhr) {
            render(template: 'createSystemChat', model: [systemChat: newSystemChat, userList: userList])
        } else {
            respond newSystemChat
        }
    }

    /*
    * 保存对象
    * */

    def updateSystemChat(SystemChat systemChat) {
        println("准备更新：${systemChat}")
        //systemChat.save flush:true
        systemChatService.save(systemChat)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editSystemChat(SystemChat systemChat) {
        if (request.xhr) {
            render(template: 'editSystemChat', model: [systemChat: systemChat])
        } else {
            respond SystemChat
        }
    }


    /*
    * 获取当前id对应的对象
    * */

    def getSystemChat(SystemChat systemChat) {
        def theModel = [systemChat: systemChat]
        println("${systemChat}")
        if (request.xhr) {
            render(template: "showSystemChat", model: theModel)
        } else {
            theModel
        }
    }

    def index() {}
}
