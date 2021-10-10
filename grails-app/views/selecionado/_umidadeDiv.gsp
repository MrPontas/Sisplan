

<i class="bi bi-moisture"></i>
<div class="umidade-content">
    <h1 class="titulo-div">Umidade atual do solo:</h1>
    <div class="atualizar-div">
        <div class="porcentage-umidade">${umidadeAtual ? umidadeAtual : umidadePadrao}%</div>
    </div>
    <div class="status-irrigacao">
        <%-- <h1 style="margin: 0">Status: </h1> --%>
        <%-- <g:if test="${ status == "ativo" }">
            <i class="bi bi-check-circle-fill" style="color: #157347; margin-left: 20px; margin-right: 10px"></i>
            <p>Ligado </p>
        </g:if>
        <g:else>
            <i class="bi bi-dash-circle-fill" style="color: #bb2d3b; margin-left: 20px; margin-right: 10px"></i>
            <p>Desligado </p>
        </g:else> --%>
    </div>
    <%-- <button 
        class="btn btn-primary"
        style="margin-top: 20px; font-size: 20px"
        onclick="ajaxPost(this, '${createLink(action:"getUmidadeAtual")}', 'mostra-umidade', false);"
        >Atualizar</button> --%>
</div>