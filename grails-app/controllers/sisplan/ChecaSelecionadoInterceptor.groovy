package sisplan


class ChecaSelecionadoInterceptor {

    ChecaSelecionadoInterceptor() {
        matchAll().excludes(controller:"usuario", action:"login").excludes(controller:"usuario", action:"validarSenha")
    }

    boolean before() { 

        if (!session.usuario){
            redirect(controller:"usuario", action:"login")
            false
        }

        true 
    }
}
