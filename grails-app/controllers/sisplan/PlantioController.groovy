package sisplan

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import org.springframework.transaction.annotation.Transactional;

class PlantioController {

    PlantioService plantioService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond plantioService.list(params), model:[plantioCount: plantioService.count()]
    }

    def show(Long id) {
        respond plantioService.get(id)
    }

    def create() {
        respond new Plantio(params)
    }

    def save(Plantio plantio) {
        if (plantio == null) {
            notFound()
            return
        }

        try {
            plantioService.save(plantio)
        } catch (ValidationException e) {
            respond plantio.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'plantio.label', default: 'Plantio'), plantio.id])
                redirect plantio
            }
            '*' { respond plantio, [status: CREATED] }
        }
    }

    def edit(Long id) {
        def plantio = Plantio.get(id)
        if (!plantio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plantio.label', default: 'Plantio'), id])
            redirect(action: "index")
            return
        }

        [plantio: plantio, erroLabel: plantio.erro, umidadeLabel:plantio.umidade]
    }

    def update(Long id, Long version) {
        def plantio = Plantio.get(id)
        if (!plantio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'plantio.label', default: 'Plantio'), id])
            redirect(action: "index")
            return
        }

        if (version != null) {
            if (plantio.version > version) {
                plantio.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'plantio.label', default: 'Plantio')] as Object[],
                          "Another user has updated this Plantio while you were editing")
                render(view: "edit", model: [plantio: plantio])
                return
            }
        }
        plantio.properties = params
        if (!plantio.save(flush: true)) {
            render(view: "edit", model: [plantio: plantio])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'plantio.label', default: 'Plantio'), plantio.id])
        redirect(action: "index", id: plantio.id)
    }
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        plantioService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'plantio.label', default: 'Plantio'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'plantio.label', default: 'Plantio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def atualizaUmidade() {
        render(template:"umidadeLabel", model: [umidadeLabel: params.umidade])
    }

    def atualizaErro() {
        render(template:"erroLabel", model: [erroLabel: params.erro])
    }
}
