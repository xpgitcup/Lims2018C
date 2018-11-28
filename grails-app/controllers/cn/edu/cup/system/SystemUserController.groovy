package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemUserController {

    SystemUserService systemUserService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemUserService.list(params), model:[systemUserCount: systemUserService.count()]
    }

    def show(Long id) {
        respond systemUserService.get(id)
    }

    def create() {
        respond new SystemUser(params)
    }

    def save(SystemUser systemUser) {
        if (systemUser == null) {
            notFound()
            return
        }

        try {
            systemUserService.save(systemUser)
        } catch (ValidationException e) {
            respond systemUser.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), systemUser.id])
                redirect systemUser
            }
            '*' { respond systemUser, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemUserService.get(id)
    }

    def update(SystemUser systemUser) {
        if (systemUser == null) {
            notFound()
            return
        }

        try {
            systemUserService.save(systemUser)
        } catch (ValidationException e) {
            respond systemUser.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), systemUser.id])
                redirect systemUser
            }
            '*'{ respond systemUser, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemUserService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemUser.label', default: 'SystemUser'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
