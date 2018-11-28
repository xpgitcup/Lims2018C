package cn.edu.cup.system

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SystemCarouselController {

    SystemCarouselService systemCarouselService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond systemCarouselService.list(params), model:[systemCarouselCount: systemCarouselService.count()]
    }

    def show(Long id) {
        respond systemCarouselService.get(id)
    }

    def create() {
        respond new SystemCarousel(params)
    }

    def save(SystemCarousel systemCarousel) {
        if (systemCarousel == null) {
            notFound()
            return
        }

        try {
            systemCarouselService.save(systemCarousel)
        } catch (ValidationException e) {
            respond systemCarousel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), systemCarousel.id])
                redirect systemCarousel
            }
            '*' { respond systemCarousel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond systemCarouselService.get(id)
    }

    def update(SystemCarousel systemCarousel) {
        if (systemCarousel == null) {
            notFound()
            return
        }

        try {
            systemCarouselService.save(systemCarousel)
        } catch (ValidationException e) {
            respond systemCarousel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), systemCarousel.id])
                redirect systemCarousel
            }
            '*'{ respond systemCarousel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        systemCarouselService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'systemCarousel.label', default: 'SystemCarousel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
