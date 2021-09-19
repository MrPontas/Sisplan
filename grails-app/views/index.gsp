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
        <<g:if test="${ sisplan.Plantio.list() > 0 }">
            <g:form>
            <g:select 
                class="form-control"
                from="${sisplan.Plantio.list()}"
                name="plantio.id"
                optionKey="id"
                optionValue="nome"
                onchange="ajaxPost(this, '${createLink(controller: "selecionado", action:"setPlantioSelecionado")}', 'divContent', false);"
                />
        </g:form>
        </g:if>
        <g:else>
           <h1>Ops! Parece que nenhum plantio foi criado. Por favor, insira um plantio para poder defini-lo.</h1>
        </g:else>

    </div>

</body>
</html>
