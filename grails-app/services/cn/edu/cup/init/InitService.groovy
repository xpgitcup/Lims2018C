package cn.edu.cup.init

import grails.gorm.transactions.Transactional

import javax.servlet.ServletContext

@Transactional
class InitService {

    def dataSource
    def dataDictionaryService
    def systemMenuService
    def dataKeyService
    def dataItemService
    def commonService

    /**
     * 初始化代码__开发环境下的初始化代码
     */
    def configureForDevelopment(ServletContext servletContext) {
        println "这是开发环境..."
        def webRootDir = servletContext.getRealPath("/")
        def configFileName = "${webRootDir}config.ini"
        def configFile = new File(configFileName)
        def config = new Properties()
        if (configFile.exists()) {
            println("读取配置文件.")
            def inf = new FileInputStream(configFile)
            def reader = new InputStreamReader(inf, "UTF-8")
            config.load(reader)
            def scriptsTemp = config.getProperty("scripts")
            println(scriptsTemp)
            if (scriptsTemp) {
                println("脚本文件：" + scriptsTemp)
                def scripts = scriptsTemp.split(",")
                scripts.each { e ->
                    loadScripts("${webRootDir}${e}")
                }
            }
        } else {
            def of = new FileOutputStream(configFile, false)
            def ow = new OutputStreamWriter(of, "utf-8")
            config.setProperty("scripts", "文件1,文件2")
            //config.storeToXML(of, "系统配置文件", "utf-8")
            config.store(ow, "系统配置文件")
            println("创建配置文件.")
        }
    }

    /**
     * 发布后的初始化代码
     */
    def configureForProduction(ServletContext servletContext) {
        println "这是发布环境..."
        def webRootDir = servletContext.getRealPath("/")
        def scriptPath = "${webRootDir}scripts/system"
        println "BootStrap ${webRootDir}"
        initService.loadScripts(scriptPath)
    }

    /*
    * 加载数据库初始化脚本
    * */

    def loadScripts(String dir) {
        File sf = new File(dir)
        if (sf.exists()) {
            println "load scripts ${dir}"
            if (sf.isDirectory()) {
                sf.eachFile { f ->
                    if (f.isFile()) {
                        executeScript(f)
                    }
                }
            } else {
                println("执行${sf}...")
                executeScript(sf)
            }
        }
    }

    /**
     * 执行附加脚本
     * */

    def executeScript(File sf) {
        //def File sf = new File(fileName)
        println "init - ${sf}"
        if (sf) {
            def db
            def sql = sf.text
            db = new groovy.sql.Sql(dataSource)
            //println "init - ${sql}"
            def lines = sql.split(";")
            lines.each() { it ->
                //println "line: ${it}"
                it = it.trim()
                if (!it.isEmpty()) {
                    db.executeUpdate(it)
                }
            }
        }
    }

}
