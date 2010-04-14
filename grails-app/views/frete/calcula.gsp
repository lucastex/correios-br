<html>
	<head>
		<title>Cálculo de frete</title>
		<meta http-equiv="Content-Type" content="text/html;charset=iso-8859-1" />
		<style>
			* {
				font-family: Verdana;
				font-size: 11px;
			}
		</style>
	</head>
	<body>
		<h1>Cálculo de frete</h1>
				
		<g:if test="${erro}">
			<p style="color: #FF0000">${erro}</p>
		</g:if>
		<g:else>
			<p><strong>Versão do arquivo de resposta:</strong> ${calculoFrete.versao}</p>
			<p><strong>Tipo de postagem:</strong> ${calculoFrete.tipoPostagem} (${calculoFrete.codigoTipoPostagem})</p>
			<p><strong>Peso:</strong> ${calculoFrete.peso}</p>
			<p><strong>Origem:</strong> ${calculoFrete.origem.cep} - ${calculoFrete.origem.local} ${calculoFrete.origem.uf}</p>
			<p><strong>Destino:</strong> ${calculoFrete.destino.cep} - ${calculoFrete.destino.local} ${calculoFrete.destino.uf}</p>
			<g:if test="${calculoFrete.tarifaMaoPropria}">
			<p><strong>Tarifa 'entregue em mãos': </strong>${calculoFrete.tarifaMaoPropria}</p>
			</g:if>
			<g:if test="${calculoFrete.tarifaAvisoRecebimento}">
			<p><strong>Tarifa 'aviso de recebimento': </strong>${calculoFrete.tarifaAvisoRecebimento}</p>
			</g:if>
			<g:if test="${calculoFrete.tarifaValorDeclarado}">
			<p><strong>Tarifa 'valor declarado' (para valor ${calculoFrete.valorDeclarado}): </strong>${calculoFrete.tarifaValorDeclarado}</p>
			</g:if>
			<p><strong>Valor total do frete:</strong> ${calculoFrete.valorFrete}</p>
		</g:else>
		
		<a href="${createLink(controller:'frete', action:'index')}">Calcular novo frete</a>
	
	</body>
</html>