package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemProcedureController {

    SystemProcedureService systemProcedureService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemProcedureService.list(params), model:[systemProcedureCount: systemProcedureService.count()]
    }

    def show(Long id) {
        respond systemProcedureService.get(id)
    }

    def create() {
        respond new SystemProcedure(params)
    }

    def save(SystemProcedure systemProcedure) {
        if (systemProcedure == null) {
            notFound()
            return
        }

        try {
            systemProcedureService.save(systemProcedure)
        } catch (ValidationException e) {
            respond systemProcedure.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), systemProcedure.id])
                redirect systemProcedure
            }
            '*' { respond systemProcedure, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemProcedureService.get(id)
    }

    def update(SystemProcedure systemProcedure) {
        if (systemProcedure == null) {
            notFound()
            return
        }

        try {
            systemProcedureService.save(systemProcedure)
        } catch (ValidationException e) {
            respond systemProcedure.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), systemProcedure.id])
                redirect systemProcedure
            }
            '*'{ respond systemProcedure, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemProcedureService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemProcedure.label', default: 'SystemProcedure'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
