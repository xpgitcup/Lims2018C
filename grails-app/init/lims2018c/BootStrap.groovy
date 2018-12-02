package lims2018c

import org.omg.CORBA.Environment

import java.util.logging.Logger

class BootStrap {

    def initService
    def grailsApplication
    def commonService

    def init = { servletContext ->
        commonService.webRootPath = servletContext.getRealPath("/")

        Logger LOG = Logger.getLogger(this.class.getName());
        environments {
            development {
                println("开发环境...")
                initService.configureForDevelopment(servletContext);
            }
            production {
                println("发布环境...")
                initService.configureForDevelopment(servletContext);
            }
        }
    }

    def destroy = {
    }
}
