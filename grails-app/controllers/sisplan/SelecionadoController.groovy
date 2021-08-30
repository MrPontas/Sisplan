package sisplan

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SelecionadoController {

    SelecionadoService selecionadoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond selecionadoService.list(params), model:[selecionadoCount: selecionadoService.count()]
    }

    def show(Long id) {
        respond selecionadoService.get(id)
    }

    def create() {
        respond new Selecionado(params)
    }

    def save(Selecionado selecionado) {
        if (selecionado == null) {
            notFound()
            return
        }

        try {
            selecionadoService.save(selecionado)
        } catch (ValidationException e) {
            respond selecionado.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'selecionado.label', default: 'Selecionado'), selecionado.id])
                redirect selecionado
            }
            '*' { respond selecionado, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond selecionadoService.get(id)
    }

    def update(Selecionado selecionado) {
        if (selecionado == null) {
            notFound()
            return
        }

        try {
            selecionadoService.save(selecionado)
        } catch (ValidationException e) {
            respond selecionado.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'selecionado.label', default: 'Selecionado'), selecionado.id])
                redirect selecionado
            }
            '*'{ respond selecionado, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        selecionadoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'selecionado.label', default: 'Selecionado'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'selecionado.label', default: 'Selecionado'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
