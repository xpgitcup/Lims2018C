package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemLogController {

    SystemLogService systemLogService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemLogService.list(params), model:[systemLogCount: systemLogService.count()]
    }

    def show(Long id) {
        respond systemLogService.get(id)
    }

    def create() {
        respond new SystemLog(params)
    }

    def save(SystemLog systemLog) {
        if (systemLog == null) {
            notFound()
            return
        }

        try {
            systemLogService.save(systemLog)
        } catch (ValidationException e) {
            respond systemLog.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect systemLog
            }
            '*' { respond systemLog, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemLogService.get(id)
    }

    def update(SystemLog systemLog) {
        if (systemLog == null) {
            notFound()
            return
        }

        try {
            systemLogService.save(systemLog)
        } catch (ValidationException e) {
            respond systemLog.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), systemLog.id])
                redirect systemLog
            }
            '*'{ respond systemLog, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemLogService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemLog.label', default: 'SystemLog'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
