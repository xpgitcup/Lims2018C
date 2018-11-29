package cn.edu.cup.userdef

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserDefinedFunctionController {

    UserDefinedFunctionService userDefinedFunctionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userDefinedFunctionService.list(params), model:[userDefinedFunctionCount: userDefinedFunctionService.count()]
    }

    def show(Long id) {
        respond userDefinedFunctionService.get(id)
    }

    def create() {
        respond new UserDefinedFunction(params)
    }

    def save(UserDefinedFunction userDefinedFunction) {
        if (userDefinedFunction == null) {
            notFound()
            return
        }

        try {
            userDefinedFunctionService.save(userDefinedFunction)
        } catch (ValidationException e) {
            respond userDefinedFunction.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userDefinedFunction.label', default: 'UserDefinedFunction'), userDefinedFunction.id])
                redirect userDefinedFunction
            }
            '*' { respond userDefinedFunction, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userDefinedFunctionService.get(id)
    }

    def update(UserDefinedFunction userDefinedFunction) {
        if (userDefinedFunction == null) {
            notFound()
            return
        }

        try {
            userDefinedFunctionService.save(userDefinedFunction)
        } catch (ValidationException e) {
            respond userDefinedFunction.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userDefinedFunction.label', default: 'UserDefinedFunction'), userDefinedFunction.id])
                redirect userDefinedFunction
            }
            '*'{ respond userDefinedFunction, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userDefinedFunctionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userDefinedFunction.label', default: 'UserDefinedFunction'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userDefinedFunction.label', default: 'UserDefinedFunction'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
