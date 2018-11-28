package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemMenuController {

    SystemMenuService systemMenuService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemMenuService.list(params), model:[systemMenuCount: systemMenuService.count()]
    }

    def show(Long id) {
        respond systemMenuService.get(id)
    }

    def create() {
        respond new SystemMenu(params)
    }

    def save(SystemMenu systemMenu) {
        if (systemMenu == null) {
            notFound()
            return
        }

        try {
            systemMenuService.save(systemMenu)
        } catch (ValidationException e) {
            respond systemMenu.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), systemMenu.id])
                redirect systemMenu
            }
            '*' { respond systemMenu, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemMenuService.get(id)
    }

    def update(SystemMenu systemMenu) {
        if (systemMenu == null) {
            notFound()
            return
        }

        try {
            systemMenuService.save(systemMenu)
        } catch (ValidationException e) {
            respond systemMenu.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), systemMenu.id])
                redirect systemMenu
            }
            '*'{ respond systemMenu, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemMenuService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemMenu.label', default: 'SystemMenu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
