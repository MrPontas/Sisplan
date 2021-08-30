package sisplan

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SelecionadoServiceSpec extends Specification {

    SelecionadoService selecionadoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Selecionado(...).save(flush: true, failOnError: true)
        //new Selecionado(...).save(flush: true, failOnError: true)
        //Selecionado selecionado = new Selecionado(...).save(flush: true, failOnError: true)
        //new Selecionado(...).save(flush: true, failOnError: true)
        //new Selecionado(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //selecionado.id
    }

    void "test get"() {
        setupData()

        expect:
        selecionadoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Selecionado> selecionadoList = selecionadoService.list(max: 2, offset: 2)

        then:
        selecionadoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        selecionadoService.count() == 5
    }

    void "test delete"() {
        Long selecionadoId = setupData()

        expect:
        selecionadoService.count() == 5

        when:
        selecionadoService.delete(selecionadoId)
        sessionFactory.currentSession.flush()

        then:
        selecionadoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Selecionado selecionado = new Selecionado()
        selecionadoService.save(selecionado)

        then:
        selecionado.id != null
    }
}
