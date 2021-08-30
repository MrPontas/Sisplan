<head>
    <asset:stylesheet src="form.css"/>
</head>
<div class="fieldcontain required ${hasErrors(bean: compra, field: 'cliente', 'error')} ">
    <div class="entrada">
        <label for="nome" style="width: 130px">
            <g:message code="nome.label" default="Nome do plantio" />
            <span class='required-indicator'>*</span>
        </label>
        <input name="nome" value="${plantio?.nome}"/>
    </div>
    <div class="entrada">
        <label for="umidade" style="width: 130px">
            <g:message code="umidade.label" default="Umidade" />
            <span class='required-indicator'>*</span>
        </label>
        <input name="umidade" type="range" min="0" max="100" step="1" value="${plantio?.umidade}"
            onChange="ajaxPost(this, '${createLink(action:"atualizaUmidade")}', 'umidadeLabel', false);"
        />
        <g:render template="umidadeLabel"/>
    </div>

    <div class="entrada">
        <label for="erro" style="width: 130px">
            <g:message code="erro.label" default="Margem de erro" />
            <span class='required-indicator'>*</span>
        </label>
        <input name="erro" type="range" min="0" max="10" step="1"
            onChange="ajaxPost(this, '${createLink(action:"atualizaErro")}', 'erroLabel', false);"
        />
        <g:render template="erroLabel"/>
    </div>
</div>