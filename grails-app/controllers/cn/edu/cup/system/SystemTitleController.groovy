package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemTitleController {

    SystemTitleService systemTitleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemTitleService.list(params), model:[systemTitleCount: systemTitleService.count()]
    }

    def show(Long id) {
        respond systemTitleService.get(id)
    }

    def create() {
        respond new SystemTitle(params)
    }

    def save(SystemTitle systemTitle) {
        if (systemTitle == null) {
            notFound()
            return
        }

        try {
            systemTitleService.save(systemTitle)
        } catch (ValidationException e) {
            respond systemTitle.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), systemTitle.id])
                redirect systemTitle
            }
            '*' { respond systemTitle, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemTitleService.get(id)
    }

    def update(SystemTitle systemTitle) {
        if (systemTitle == null) {
            notFound()
            return
        }

        try {
            systemTitleService.save(systemTitle)
        } catch (ValidationException e) {
            respond systemTitle.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), systemTitle.id])
                redirect systemTitle
            }
            '*'{ respond systemTitle, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemTitleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemTitle.label', default: 'SystemTitle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
