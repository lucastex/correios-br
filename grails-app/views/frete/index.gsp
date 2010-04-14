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
		<g:form controller="frete" action="calcula">
			<fieldset>
				<p>
					<label for="servico">Tipo de envio:</label> <br />
					<g:radioGroup 	name="servico" 
									labels="['PAC','SEDEX','SEDEX_10','SEDEX_HOJE','SEDEX_A_COBRAR']"
									values="['PAC','SEDEX','SEDEX_10','SEDEX_HOJE','SEDEX_A_COBRAR']" 
									value="SEDEX">
						${it.radio} ${it.label} <br />
					</g:radioGroup>
				</p>
				<p>
					<label for="cepOrigem">CEP Origem (obrigatório):</label>
					<input type="text" size="8" maxlength="8" name="cepOrigem" />
				</p>
				<p>
					<label for="cepDestino">CEP Destino (obrigatório):</label>
					<input type="text" size="8" maxlength="8" name="cepDestino" />
				</p>
				<p>
					<label for="peso">Peso (default 1kg):</label>
					<input type="text" size="8" maxlength="8" name="peso" />
				</p>
				<p>
					<label for="valorDeclarado">Valor declarado (não obrigatório):</label>
					<input type="text" size="8" maxlength="8" name="valorDeclarado" />
				</p>
				<p>
					<input type="checkbox" name="maoPropria" /> <label for="maoPropria">Entrega em mãos?</label>
				</p>
				<p>
					<input type="checkbox" name="avisoRecebimento" /> <label for="avisoRecebimento">Com aviso de recebimento</label>
				</p>
				<p>
					<input type="submit" value="Calcular" />
				</p>
			</fieldset>
		</g:form>
	</body>
</html>