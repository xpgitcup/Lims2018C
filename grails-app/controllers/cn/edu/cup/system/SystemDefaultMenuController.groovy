package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemDefaultMenuController {

    SystemDefaultMenuService systemDefaultMenuService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemDefaultMenuService.list(params), model:[systemDefaultMenuCount: systemDefaultMenuService.count()]
    }

    def show(Long id) {
        respond systemDefaultMenuService.get(id)
    }

    def create() {
        respond new SystemDefaultMenu(params)
    }

    def save(SystemDefaultMenu systemDefaultMenu) {
        if (systemDefaultMenu == null) {
            notFound()
            return
        }

        try {
            systemDefaultMenuService.save(systemDefaultMenu)
        } catch (ValidationException e) {
            respond systemDefaultMenu.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), systemDefaultMenu.id])
                redirect systemDefaultMenu
            }
            '*' { respond systemDefaultMenu, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemDefaultMenuService.get(id)
    }

    def update(SystemDefaultMenu systemDefaultMenu) {
        if (systemDefaultMenu == null) {
            notFound()
            return
        }

        try {
            systemDefaultMenuService.save(systemDefaultMenu)
        } catch (ValidationException e) {
            respond systemDefaultMenu.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), systemDefaultMenu.id])
                redirect systemDefaultMenu
            }
            '*'{ respond systemDefaultMenu, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemDefaultMenuService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemDefaultMenu.label', default: 'SystemDefaultMenu'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
