package sisplan

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class ChecaSelecionadoInterceptorSpec extends Specification implements InterceptorUnitTest<ChecaSelecionadoInterceptor> {

    def setup() {
    }

    def cleanup() {

    }

    void "Test checaSelecionado interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"checaSelecionado")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
