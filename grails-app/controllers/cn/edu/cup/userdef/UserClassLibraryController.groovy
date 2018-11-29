package cn.edu.cup.userdef

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserClassLibraryController {

    UserClassLibraryService userClassLibraryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userClassLibraryService.list(params), model:[userClassLibraryCount: userClassLibraryService.count()]
    }

    def show(Long id) {
        respond userClassLibraryService.get(id)
    }

    def create() {
        respond new UserClassLibrary(params)
    }

    def save(UserClassLibrary userClassLibrary) {
        if (userClassLibrary == null) {
            notFound()
            return
        }

        try {
            userClassLibraryService.save(userClassLibrary)
        } catch (ValidationException e) {
            respond userClassLibrary.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userClassLibrary.label', default: 'UserClassLibrary'), userClassLibrary.id])
                redirect userClassLibrary
            }
            '*' { respond userClassLibrary, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userClassLibraryService.get(id)
    }

    def update(UserClassLibrary userClassLibrary) {
        if (userClassLibrary == null) {
            notFound()
            return
        }

        try {
            userClassLibraryService.save(userClassLibrary)
        } catch (ValidationException e) {
            respond userClassLibrary.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userClassLibrary.label', default: 'UserClassLibrary'), userClassLibrary.id])
                redirect userClassLibrary
            }
            '*'{ respond userClassLibrary, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userClassLibraryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userClassLibrary.label', default: 'UserClassLibrary'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userClassLibrary.label', default: 'UserClassLibrary'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
