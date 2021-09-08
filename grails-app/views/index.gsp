<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Início</title>
    <asset:stylesheet src="inicio.css" />
    <%-- <script type="text/javascript">
        function salvarPlantio(){
            $.ajax({
                type: "POST",
                url:"${createLink(controller:'selecionado', action:'setPlantioSelecionado')}",
                data: $("form").serialize()
                // success: success,
                // dataType: dataType
            });
        }
    </script> --%>
</head>
<body class="bodyInicio">
    <div class="content">
        <g:form>
            <g:select 
                from="${sisplan.Plantio.list()}"
                name="plantio.id"
                optionKey="id"
                optionValue="nome"
                onchange="ajaxPost(this, '${createLink(controller: "selecionado", action:"setPlantioSelecionado")}', 'divContent', false);"
                />
        </g:form>

    </div>

</body>
</html>
