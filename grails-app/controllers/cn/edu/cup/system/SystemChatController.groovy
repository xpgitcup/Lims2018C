package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemChatController {

    SystemChatService systemChatService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemChatService.list(params), model:[systemChatCount: systemChatService.count()]
    }

    def show(Long id) {
        respond systemChatService.get(id)
    }

    def create() {
        respond new SystemChat(params)
    }

    def save(SystemChat systemChat) {
        if (systemChat == null) {
            notFound()
            return
        }

        try {
            systemChatService.save(systemChat)
        } catch (ValidationException e) {
            respond systemChat.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), systemChat.id])
                redirect systemChat
            }
            '*' { respond systemChat, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemChatService.get(id)
    }

    def update(SystemChat systemChat) {
        if (systemChat == null) {
            notFound()
            return
        }

        try {
            systemChatService.save(systemChat)
        } catch (ValidationException e) {
            respond systemChat.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), systemChat.id])
                redirect systemChat
            }
            '*'{ respond systemChat, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemChatService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemChat.label', default: 'SystemChat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
