package sisplan

import grails.gorm.services.Service

@Service(Selecionado)
interface SelecionadoService {

    Selecionado get(Serializable id)

    List<Selecionado> list(Map args)

    Long count()

    void delete(Serializable id)

    Selecionado save(Selecionado selecionado)

}