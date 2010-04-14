package br.com.lucastex.grails.plugins.correios

class CorreiosService {

    boolean transactional = false
	final String url = "http://www.correios.com.br/encomendas/precos/calculo.cfm?"

    def freteSedex(params = [:]) {
		params.servico = '40010'
		return calculate(params)
    }

	def calculate(params) {
		
		params.resposta = 'xml'		
		params.maoPropria = params.maoPropria ? "1" : "0"
		params.avisoRecebimento = params.avisoRecebimento ? "1" : "0"
		
		def queryString = params.collect { "${it.key}=${it.value}" }.join("&")
		def responseText = "${url}${queryString}".toURL().getText("ISO-8859-1")		
		def response = new XmlSlurper().parseText(responseText)
		
		println responseText
		println "****************************************"
		if (response.erro.codigo.toString() != "0") {
			throw new Exception("Erro ${response.erro.codigo} - ${response.erro.descricao}")
		}
		
		def calculoFrete = new CalculoFrete()
		calculoFrete.with {
			
			versao = response.versao_arquivo
			tipoPostagem = response.dados_postais.servico_nome
			codigoTipoPostagem = response.dados_postais.servico
			peso = response.dados_postais.peso.toString() as double
			maoPropria = (response.dados_postais.mao_propria.toString() as double) > 0
			tarifaMaoPropria = response.dados_postais.mao_propria.toString() as double
			avisoRecebimento = (response.dados_postais.aviso_recebimento.toString() as double) > 0
			tarifaAvisoRecebimento = response.dados_postais.aviso_recebimento.toString() as double
			valorDeclarado = response.dados_postais.valor_declarado.toString() as double
			tarifaValorDeclarado = response.dados_postais.tarifa_valor_declarado.toString() as double
			valorFrete = response.dados_postais.preco_postal.toString() as double
			
			origem.uf = response.dados_postais.uf_origem
			origem.local = response.dados_postais.local_origem
			origem.cep = response.dados_postais.cep_origem
			
			destino.uf = response.dados_postais.uf_destino
			destino.local = response.dados_postais.local_destino
			destino.cep = response.dados_postais.cep_destino
		}
		
		return calculoFrete
	}
}

/*
	COD. DE SERVICOS:
	41106 - PAC
	40010 - SEDEX
	40215 - SEDEX 10
	40290 - SEDEX HOJE
	81019 - e-SEDEX
	44105 - MALOTE
*/
//http://www.correios.com.br/encomendas/precos/calculo.cfm?resposta=xml&servico=40010&cepOrigem=85851150&cepDestino=01331001&peso=0.31&avisoRecebimento=1&maoPropria=1&valorDeclarado=100

/*
	<?xml version="1.0" encoding="ISO-8859-1" ?>
	<calculo_precos>										
		<versao_arquivo>1.0</versao_arquivo>
		<dados_postais>
			<servico>40010</servico>
			<servico_nome>SEDEX</servico_nome>
			<uf_origem>PR</uf_origem>
			<local_origem>Interior</local_origem>
			<cep_origem>85851150</cep_origem>
			<uf_destino>SP</uf_destino>
			<local_destino>Capital</local_destino>
			<cep_destino>01331001</cep_destino>
			<peso>0.31</peso>
			<mao_propria>0</mao_propria>
			<aviso_recebimento>0</aviso_recebimento>
			<valor_declarado>0</valor_declarado>
			<tarifa_valor_declarado>0</tarifa_valor_declarado>
			<preco_postal>26.9</preco_postal>
		</dados_postais>
		<erro>
			<codigo>0</codigo>
			<descricao></descricao>
		</erro>
	</calculo_precos>
*/