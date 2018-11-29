package cn.edu.cup.userdef

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserClassController {

    UserClassService userClassService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userClassService.list(params), model:[userClassCount: userClassService.count()]
    }

    def show(Long id) {
        respond userClassService.get(id)
    }

    def create() {
        respond new UserClass(params)
    }

    def save(UserClass userClass) {
        if (userClass == null) {
            notFound()
            return
        }

        try {
            userClassService.save(userClass)
        } catch (ValidationException e) {
            respond userClass.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userClass.label', default: 'UserClass'), userClass.id])
                redirect userClass
            }
            '*' { respond userClass, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userClassService.get(id)
    }

    def update(UserClass userClass) {
        if (userClass == null) {
            notFound()
            return
        }

        try {
            userClassService.save(userClass)
        } catch (ValidationException e) {
            respond userClass.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userClass.label', default: 'UserClass'), userClass.id])
                redirect userClass
            }
            '*'{ respond userClass, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userClassService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userClass.label', default: 'UserClass'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userClass.label', default: 'UserClass'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
