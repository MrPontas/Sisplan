package sisplan

import grails.gorm.services.Service

@Service(Plantio)
interface PlantioService {

    Plantio get(Serializable id)

    List<Plantio> list(Map args)

    Long count()

    void delete(Serializable id)

    Plantio save(Plantio plantio)

}