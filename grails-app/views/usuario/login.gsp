<!doctype html>
<html>
<head>
    <%-- <meta name="layout" content="main"/>
    <title>Aplicação teste</title> --%>
    <asset:stylesheet src="index.css"/>
</head>
<body>
    <div id="content">
        <g:form action="validarSenha">
            <div id="form-auth">
                <h1>Tela de login</h1>
                <p>Login</p>
                <g:textField id="login" name="login" />
                <p>Senha</p>
                <g:passwordField id="senha" name="senha" type="password"/>
                <button type="submit">Iniciar sessão</button>
            </div>
        </g:form>
    </div>
</body>
</html>
