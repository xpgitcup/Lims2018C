package cn.edu.cup.dictionary

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ChartConfigController {

    ChartConfigService chartConfigService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond chartConfigService.list(params), model:[chartConfigCount: chartConfigService.count()]
    }

    def show(Long id) {
        respond chartConfigService.get(id)
    }

    def create() {
        respond new ChartConfig(params)
    }

    def save(ChartConfig chartConfig) {
        if (chartConfig == null) {
            notFound()
            return
        }

        try {
            chartConfigService.save(chartConfig)
        } catch (ValidationException e) {
            respond chartConfig.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), chartConfig.id])
                redirect chartConfig
            }
            '*' { respond chartConfig, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond chartConfigService.get(id)
    }

    def update(ChartConfig chartConfig) {
        if (chartConfig == null) {
            notFound()
            return
        }

        try {
            chartConfigService.save(chartConfig)
        } catch (ValidationException e) {
            respond chartConfig.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), chartConfig.id])
                redirect chartConfig
            }
            '*'{ respond chartConfig, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        chartConfigService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'chartConfig.label', default: 'ChartConfig'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
