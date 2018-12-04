package cn.edu.cup.common

import grails.util.Environment
import org.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

import javax.servlet.ServletContext

class CommonService {

    def grailsApplication
    def webRootPath = ""        // 公用的变量

    def applicationName() {
        println("${grailsApplication}")
        return grailsApplication.metadata.getApplicationName()
    }

    List getQuotationList(list) {
        def tmp = []
        list.each { e ->
            tmp.add("\'${e}\'")
        }
        tmp
    }

    /*
    * 数据导出服务：
    * 将对象导出到Map
    * */

    def exportObjectList2DataTable(Object object) {
        def head = []
        def data = []
        if (object instanceof List) {
            println("${object} 是List类型。")
            if (object.size() > 0) {
                object[0].properties.each { e ->
                    head.add(e.key)
                }
                object.each { e ->
                    def row = []
                    e.properties.each { ee ->
                        row.add(ee.value)
                    }
                    data.add(row)
                }
            }
        } else {
            println("${object}不是List")
            object.properties.each { e ->
                head.add(e.key)
                data.add(e.value)
            }
        }
        def model = [head: head, data: data]
        return model
    }

    /*
    * 上传文件
    * */

    File upload(params) {
        println("service: ${params}")
        println("service: ${params.uploadedFile}")
        println("service: ${params.uploadedFile.originalFilename}")
        if (params.uploadedFile && params.destDir) {
            def uploadedFile = params.uploadedFile
            def destDir = params.destDir
            def userDir = new File(destDir)
            if (!userDir.exists()) {
                userDir.mkdirs()
            }
            def destFile = new File(userDir, uploadedFile.originalFilename)
            uploadedFile.transferTo(destFile)
            println "upload ${destFile}"
            return destFile
        }
    }

    /*
    * 下载文件
    * */

    def downLoadFile(params) {
        def hasError = []
        if (params.downLoadFileName) {
            def filename = params.downLoadFileName
            def sf = new File(filename)
            println "download: ${sf} -- ${filename}"
            if (sf.exists()) {
                println "begin download......"
                def fName = sf.getName()
                // 处理中文乱码
                def name = URLEncoder.encode(fName, "UTF-8");
                def response = getResponse()
                response.setHeader("Content-disposition", "attachment; filename=" + name)
                response.contentType = "application/x-rarx-rar-compressed"
                //response.contentType = ""

                def out = response.outputStream
                def inputStream = new FileInputStream(sf)
                byte[] buffer = new byte[1024]
                int i = -1
                while ((i = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, i)
                }
                out.flush()
                out.close()
                inputStream.close()
            } else {
                hasError.add("文件不存在.")
            }
        } else {
            hasError.add("缺少downLoadFileName参数.")
        }
    }

    /*
    * 菜单配置文件
    * */

    def menuConfigFileName() {
        def fileName = "${webRootPath}systemConfig/systemMenu.json"
        return fileName
    }

    //Getting the Request object
    def getRequest() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getCurrentRequest()
    }

    //Getting the Response object
    def getResponse() {
        def webUtils = WebUtils.retrieveGrailsWebRequest()
        webUtils.getCurrentResponse()
    }

    //Getting the ServletContext object
    def getServletContext() {
        //def webUtils = WebUtils.retrieveGrailsWebRequest()
        //webUtils.getServletContext()
        return grailsApplication.getMainContext().servletContext
    }

    //Getting the Session object
    def getSession() {
        RequestContextHolder.currentRequestAttributes().getSession()
    }

    //获取当前程序名称
    def getApplicationName() {
        return grails.util.Metadata.current.'app.name'
    }

    /*
    * 根据环境检查文件路径
    * */

    def checkFilePath4Enviroment(pathString) {
        def webRoot = getWebRootDir()
        def result
        switch (Environment.current) {
            case Environment.DEVELOPMENT:
                result = pathString
                break
            case Environment.PRODUCTION:
                result = "${webRoot}/WEB-INF/${pathString}"
                break
        }
        return result
    }

}
