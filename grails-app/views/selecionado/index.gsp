<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sisplan</title>
    <asset:stylesheet src="bootstrap.css" />

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>

<body>
    <div class="tela-selecionado">
        <div class="configura-plantio">
            <div class="content-plantio">
                <h1>Selecione o plantio de operação: </h1>
                <g:form >
                    <div>
                        <g:select
                            from="${sisplan.Plantio.list()}"
                            name="plantio.id"
                            optionKey="id"
                            optionValue="nome"
                            value="${plantio?.id}"
                            noSelection="['':'']"
                            onchange="onChangeSelect(this);"
                            />
                    </div>
                </g:form>

            </div>
            <div id="divContent" class="divContent">
                <g:render template="divContent" model="[plantio:plantio]" />
            </div>
        </div>
        <div id="mostra-umidade" class="mostra-umidade">
            <g:render template="umidadeDiv" model="[umidadeAtual:umidadeAtual]" />
        </div>
    </div>
    <script type="text/javascript">
    

        function onChangeSelect(selectObj){
            document.getElementById("infoPlantio").innerHTML = '<asset:image id="spinner-img" class="spinner-img" src="spinner.gif" />';
            document.getElementById("spinner-img").style.visibility = "visible";
            document.getElementById("spinner-img").style.height = "105px";
            
            ajaxPost(selectObj, '${createLink(controller: "selecionado", action:"setPlantioSelecionado")}', 'divContent', false);
        }
    </script>
</body>
</html>