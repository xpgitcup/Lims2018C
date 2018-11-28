package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemSponserController {

    SystemSponserService systemSponserService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemSponserService.list(params), model:[systemSponserCount: systemSponserService.count()]
    }

    def show(Long id) {
        respond systemSponserService.get(id)
    }

    def create() {
        respond new SystemSponser(params)
    }

    def save(SystemSponser systemSponser) {
        if (systemSponser == null) {
            notFound()
            return
        }

        try {
            systemSponserService.save(systemSponser)
        } catch (ValidationException e) {
            respond systemSponser.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), systemSponser.id])
                redirect systemSponser
            }
            '*' { respond systemSponser, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemSponserService.get(id)
    }

    def update(SystemSponser systemSponser) {
        if (systemSponser == null) {
            notFound()
            return
        }

        try {
            systemSponserService.save(systemSponser)
        } catch (ValidationException e) {
            respond systemSponser.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), systemSponser.id])
                redirect systemSponser
            }
            '*'{ respond systemSponser, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemSponserService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemSponser.label', default: 'SystemSponser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
