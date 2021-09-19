<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Sisplan</title>
    <asset:stylesheet src="bootstrap.css" />

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>

<body>
    <div class="content">
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
        <div class="tenor-gif-embed" data-postid="17305985" data-share-method="host" data-aspect-ratio="1.51659" data-width="100%"></div>
    </div>
    <script type="text/javascript">
    

        function onChangeSelect(selectObj){
            // document.getElementById('divContent').innerHTML = "Carregando..."
            document.getElementById('divContent').innerHTML = "<div class='tenor-gif-embed' data-postid='17305985' data-share-method='host' data-aspect-ratio='1.51659' data-width='100%'><a href='https://tenor.com/view/spinning-loading-buffering-circles-gif-17305985'>Spinning Loading GIF</a>from <a href='https://tenor.com/search/spinning-gifs'>Spinning GIFs</a></div> <script type='text/javascript' async src='https://tenor.com/embed.js'></script>"
            // document.getElementById('divContent').innerHTML = "<div class='spinner-border text-primary' role='status'><span class='sr-only'>Loading...</span></div>"
            ajaxPost(selectObj, '${createLink(controller: "selecionado", action:"setPlantioSelecionado")}', 'divContent', false);
        }
    </script>
</body>
</html>