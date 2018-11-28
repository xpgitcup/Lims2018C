package cn.edu.cup.init

import cn.edu.cup.system.SystemAttribute
import cn.edu.cup.system.SystemCarousel
import cn.edu.cup.system.SystemMenu
import cn.edu.cup.system.SystemSponser
import cn.edu.cup.system.SystemTitle
import cn.edu.cup.system.SystemUser
import grails.gorm.transactions.Transactional

import javax.servlet.ServletContext

@Transactional
class InitService {

    def grailsApplication
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
        println(grailsApplication.metadata.getApplicationName())
        processConfigFile(servletContext)
        // 初始化用户--
        initSystemUsers()
        // 初始化底层管理--控制器列表
        def domains = grailsApplication.controllerClasses
        initSystemMenuItems(domains)
        //属性数据
        fileSampleAttributes()
        //程序标题
        fillSampleTitle()
    }

    def fillSampleTitle() {
        println("初始化系统标题......")
        if (SystemTitle.count() < 1) {
            def systemTitle = new SystemTitle(
                    applicationTitle: "Lims 2018 实验室信息管理系统",
                    applicationLogo: "cuplogoA.png",
                    applicationLayout: "mainEasyUI"
            )
            systemTitle.save(true)
            //----------------------------------------------------------------------------------------------------------
            if (SystemSponser.countBySystemTitle(systemTitle) < 1) {
                newSponser(systemTitle, "中国石油大学", "cuplogoA.png")
                //newSponser(systemTitle, "中海油", "logo_cnooc.png")
                //newSponser(systemTitle, "中联煤", "logo_cbm.png")
            }
            //----------------------------------------------------------------------------------------------------------
            if (SystemCarousel.countBySystemTitle(systemTitle) < 1) {
                newCarousel(systemTitle, "课题组", "课题组.jpg")
                newCarousel(systemTitle, "多相流", "多相流.png")
                newCarousel(systemTitle, "抽油机", "u68.jpg")
            }
        }
    }

    private void newSponser(systemTitle, name, logo) {
        def ns = new SystemSponser(systemTitle: systemTitle, name: name, logo: logo)
        ns.save(true)
    }

    private void newCarousel(systemTitle, name, png) {
        def nc = new SystemCarousel(systemTitle: systemTitle, name: name, imageName: png)
        nc.save(true)
    }


    /*
    * 初始化系统菜单
    * 定义一个文件格式--jsong格式
    * 先输出一个输出的，然后定义导入的
    * */

    def initSystemMenuItems(domains) {
        // 首先从配置文件中导入
        def fileName = commonService.menuConfigFileName()
        //--------------------------------------------------------------------------------------------------------------
        if (SystemMenu.count() < 1) {
            importFromJsonFile(fileName)
        }
        setupDomainMenuItems(domains)
    }

    private SystemMenu setupDomainMenuItems(domains) {
        def m0 = new SystemMenu(
                menuContext: "底层管理",
                menuAction: "#",
                menuDescription: "对系统的菜单结构进行底层维护",
                upMenuItem: null,
                roleAttribute: "底层管理",
                menuOrder: 100
        )
        //m0.save(true)
        systemMenuService.save(m0)
        //----------------------------------------------------------------------------------------------------------
        //创建正对各个域类控制器的菜单
        domains.sort()
        domains.each() { e ->
            def m01 = new SystemMenu(
                    menuContext: "${e.name}",
                    menuAction: "${e.name}/index",
                    menuDescription: "对${e.name}属性进行维护",
                    upMenuItem: m0,
                    roleAttribute: "底层管理",
                    menuOrder: 0
            )
            //m01.save(true)
            systemMenuService.save(m01)
        }
        m0
    }

    /*
    * 从json文件中导入菜单
    * */

    def importFromJsonFile(fileName) {
        def jsonFile = new File(fileName)
        if (jsonFile.exists()) {
            def json = jsonFile.text
            def menuItems = com.alibaba.fastjson.JSON.parse(json)
            println(menuItems)
            menuItems.each { e ->
                def m0 = new SystemMenu(
                        menuContext: e.menuContext,
                        menuAction: e.menuAction,
                        menuDescription: e.menuDescription,
                        upMenuItem: null,
                        roleAttribute: e.roleAttribute,
                        menuOrder: e.menuOrder
                )
                systemMenuService.save(m0)
                if (e.menuItems) {
                    e.menuItems.each { ee ->
                        def mm0 = new SystemMenu(
                                menuContext: ee.menuContext,
                                menuAction: ee.menuAction,
                                menuDescription: ee.menuDescription,
                                upMenuItem: m0,
                                roleAttribute: ee.roleAttribute,
                                menuOrder: ee.menuOrder
                        )
                        systemMenuService.save(mm0)
                    }
                }
            }
        }
    }

    /**
     * 初始化用户数据
     **/
    def initSystemUsers() {
        if (SystemUser.count() < 1) {
            newUser("李晓平", "3764", '底层管理 系统维护 公共服务')
            newUser("宫敬", "2156", '底层管理 系统维护 公共服务')
            newUser("吴海浩", "3181", '底层管理 系统维护 公共服务')
        }
    }

    private void newUser(userName, password, attribute) {
        def u5 = new SystemUser(userName: userName, password: password, roleAttribute: attribute)
        u5.save(true)
    }

    private void fileSampleAttributes() {
        if (SystemAttribute.count() < 1) {
            println("测试性的属性...")
            def attributeA = new SystemAttribute(name: "系统操作权限")
            attributeA.save(true)
            SystemMenu.findAllByUpMenuItemIsNull().each { e->
                def aa = new SystemAttribute(name: e.menuContext, upAttribute: attributeA)
                aa.save(true)
            }
        }
    }

    /*
    * 处理ocnfig.ini文件
    * */
    private void processConfigFile(ServletContext servletContext) {
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
        processConfigFile(servletContext)
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
