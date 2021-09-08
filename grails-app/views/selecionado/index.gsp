<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sisplan</title>
    <asset:stylesheet src="bootstrap.css" />

</head>

<body>
    <div class="content">
        <h1>Selecione o plantio de operação: </h1>
        <g:form>
            <g:select
                class="form-select" 
                from="${sisplan.Plantio.list()}"
                name="plantio.id"
                optionKey="id"
                optionValue="nome"
                value="${plantio?.id}"
                noSelection="['':'']"
                onchange="ajaxPost(this, '${createLink(controller: "selecionado", action:"setPlantioSelecionado")}', 'divContent', false);"
                />
        </g:form>

    </div>
    <div id="divContent" class="divContent">
        <g:render template="divContent" model="[plantio:plantio]" />
    </div>
</body>
</html>
