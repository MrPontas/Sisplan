package sisplan

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PlantioServiceSpec extends Specification {

    PlantioService plantioService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Plantio(...).save(flush: true, failOnError: true)
        //new Plantio(...).save(flush: true, failOnError: true)
        //Plantio plantio = new Plantio(...).save(flush: true, failOnError: true)
        //new Plantio(...).save(flush: true, failOnError: true)
        //new Plantio(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //plantio.id
    }

    void "test get"() {
        setupData()

        expect:
        plantioService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Plantio> plantioList = plantioService.list(max: 2, offset: 2)

        then:
        plantioList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        plantioService.count() == 5
    }

    void "test delete"() {
        Long plantioId = setupData()

        expect:
        plantioService.count() == 5

        when:
        plantioService.delete(plantioId)
        sessionFactory.currentSession.flush()

        then:
        plantioService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Plantio plantio = new Plantio()
        plantioService.save(plantio)

        then:
        plantio.id != null
    }
}
