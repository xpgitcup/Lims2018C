package cn.edu.cup.userdef

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserClassMethodController {

    UserClassMethodService userClassMethodService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userClassMethodService.list(params), model:[userClassMethodCount: userClassMethodService.count()]
    }

    def show(Long id) {
        respond userClassMethodService.get(id)
    }

    def create() {
        respond new UserClassMethod(params)
    }

    def save(UserClassMethod userClassMethod) {
        if (userClassMethod == null) {
            notFound()
            return
        }

        try {
            userClassMethodService.save(userClassMethod)
        } catch (ValidationException e) {
            respond userClassMethod.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), userClassMethod.id])
                redirect userClassMethod
            }
            '*' { respond userClassMethod, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userClassMethodService.get(id)
    }

    def update(UserClassMethod userClassMethod) {
        if (userClassMethod == null) {
            notFound()
            return
        }

        try {
            userClassMethodService.save(userClassMethod)
        } catch (ValidationException e) {
            respond userClassMethod.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), userClassMethod.id])
                redirect userClassMethod
            }
            '*'{ respond userClassMethod, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userClassMethodService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userClassMethod.label', default: 'UserClassMethod'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
