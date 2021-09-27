    <head><asset:stylesheet src="inicio.css" /></head>
    <h1 class="titulo-div">${plantio?"Plantio selecionado:":"Nenhum plantio selecionado"}</h1>
    <g:if test="${ plantio }">
        <div id="infoPlantio" class="infoPlantio">
            <p>${plantio.nome}</p>
            <p>Umidade: ${plantio?.umidade}%</p>
            <p>Margem de erro: ${plantio?.erro}%</p>
        </div>
        
        
    </g:if>