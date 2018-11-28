package lims2018c

import org.omg.CORBA.Environment

class BootStrap {

    def initService
    def grailsApplication

    def init = { servletContext ->
        environments { e->
            switch (Environment.name) {
                case grails.util.Environment.DEVELOPMENT:
                    break
                case grails.util.Environment.PRODUCTION:
                    break
            }
        }
    }
    def destroy = {
    }
}
