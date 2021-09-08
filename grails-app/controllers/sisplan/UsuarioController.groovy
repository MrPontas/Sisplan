package sisplan

class UsuarioController {

    def index() { }
    
    def login(){}

    def validarSenha(String login, String senha){
        def usuarioInstance = Usuario.findByLoginAndSenha(login,senha);
        if (usuarioInstance){
            session.usuario=usuarioInstance

            flash.message="Bem vindo: "+usuarioInstance.login;

            redirect(controller:"selecionado")
            return
        } else {
            flash.message="Usuário ou senha incorretos";
        }

        redirect(action:"login")
        return
    }

    def logOut(){
        session.invalidate()
        redirect(action:"login")
        return
    }
}
