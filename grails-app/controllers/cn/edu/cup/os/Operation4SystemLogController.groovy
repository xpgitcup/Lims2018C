package cn.edu.cup.os

import cn.edu.cup.system.SystemLog
import cn.edu.cup.system.SystemLogController
import grails.converters.JSON

class Operation4SystemLogController extends SystemLogController{

    /*
    * 清理日志
    * */
    def clearSystemLog(params) {
        println("${params}")
        def clearType = params.clearType
        def k = Integer.parseInt(params.n)
        //println(clearType)
        switch (clearType) {
            case "keepLast":
                def maxId = SystemLog.last().id
                println("---清理日志：  ${maxId}")
                def kk = maxId - k
                def sqlResult = SystemLog.executeUpdate("delete SystemLog s where s.id < ${kk}")

                println("sql 执行结果： ${sqlResult}")
                break;
            default:
                println("清理日志。。。。")
                break
        }
        redirect(action: 'index')
    }

    /*
    * 删除超过某个时间的日志
    * */

    def deleteSystemLogOldThan() {
        println("${params}")
        Calendar rightNow = Calendar.getInstance();
        /*
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR,-1);//日期减1年
        rightNow.add(Calendar.MONTH,3);//日期加3个月
        rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        */
        def timeUnit = params.timeUnit
        def timeNumber = params.timeNumber
        def now = new Date()
        rightNow.setTime(now)
        def v
        switch (timeUnit) {
            case "second":
                rightNow.add(Calendar.SECOND, -timeNumber)
                break
            case "minute":
                rightNow.add(Calendar.MINUTE, -timeNumber)
                break
            case "hour":
                rightNow.add(Calendar.HOUR, -timeNumber)
                break
            case "day":
                rightNow.add(Calendar.DATE, -timeNumber)
                now.set(day: v)
                break
            case "month":
                rightNow.add(Calendar.MONTH, -timeNumber)
                break
        }
        SystemLog.executeQuery("delete where actionDate > ?", rightNow)
        redirect(action: 'index')
    }

    /*
    * 列出对象
    * */

    def listSystemLog() {
        def systemLogList = SystemLog.list(params)
        if (request.xhr) {
            render(template: 'listSystemLog', model: [systemLogList: systemLogList])
        } else {
            respond newSystemLog
        }
    }

    /*
    * 创建对象
    * */

    def createSystemLog(SystemLog systemLog) {
        def newSystemLog = new SystemLog()
        if (request.xhr) {
            render(template: 'editSystemLog', model: [SystemLog: newSystemLog])
        } else {
            respond newSystemLog
        }
    }

    /*
    * 保存对象
    * */
    def updateSystemLog(SystemLog systemLog) {
        println("准备更新：${systemLog}")
        //systemLog.save flush: true
        systemLogService.save(systemLog)
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editSystemLog(SystemLog systemLog) {
        if (request.xhr) {
            render(template: 'editSystemLog', model: [systemLog: systemLog])
        } else {
            respond SystemLog
        }
    }

    /*
    * 统计根属性
    * */

    def countSystemLog() {
        def count = SystemLog.count()    //这是必须调整的
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

    def getSystemLog(SystemLog systemLog) {
        def theModel = [systemLog: systemLog]
        println("${systemLog}")
        if (request.xhr) {
            render(template: "showSystemLog", model: theModel)
        } else {
            theModel
        }
    }

    def index() { }
}
