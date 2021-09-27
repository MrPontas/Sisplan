package sisplan

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class SelecionadoController {

    SelecionadoService selecionadoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def mqtt = new Mqtt();

    def index() {
        mqtt.conectaClienteMqtt();
        mqtt.subscribe();

        def selecionado = Selecionado.get(1)
        if(selecionado){
            def plantio = mqttPublish(selecionado);
            render(view:"index", model:[plantio:plantio, umidadeAtual: mqtt.getUmidadeAtual(), umidadePadrao: plantio.umidade])
        }
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

    def setPlantioSelecionado(){
        def selecionado;

        if(!params.plantio){ 
            render(template:"divContent")
            return
        }
        if(sisplan.Selecionado.list().size() == 0)
            selecionado = new Selecionado(params);
        else {
            selecionado = Selecionado.list()[0]
            selecionado.properties = params
        }
        if (!selecionado.save(flush: true)) {
            print "não salvou"
            return
        }
        if(selecionado){
            mqttPublish(selecionado);

        }
        def plantio = Plantio.get(selecionado.plantioId)
        render(template:"divContent", model:[plantio:plantio])

        return
    }

    def mqttPublish(def selecionado){
        // def mqtt = new Mqtt();

        def plantio = Plantio.get(selecionado.plantioId)

        mqtt.conectaClienteMqtt();
        mqtt.publicaMensagem(selecionado.plantio.erro.toString(), "erro");
        mqtt.publicaMensagem(selecionado.plantio.umidade.toString(), "umidade");


        return plantio;

    }

    def getUmidadeAtual(){
        def selecionado = Selecionado.list()[0]
        def plantio = Plantio.get(selecionado.plantioId)
        
        String umidadeAtual = mqtt.getUmidadeAtual();

        println "controle " + umidadeAtual
        render(template: "umidadeDiv", model:[umidadeAtual:umidadeAtual, umidadePadrao: plantio.umidade]);
    }
}
