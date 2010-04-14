package br.com.lucastex.grails.plugins.correios

import org.apache.commons.lang.StringEscapeUtils

class CorreiosService {

    boolean transactional = false
	final String url = "http://www.correios.com.br/encomendas/precos/calculo.cfm?"

    def calculoFrete(params = [:]) {
		return calculate(params)
    }

	def calculate(params) {
		
		params.resposta = 'xml'		
		params.maoPropria = params.maoPropria ? "1" : "0"
		params.avisoRecebimento = params.avisoRecebimento ? "1" : "0"
		
		def queryString = params.collect { "${it.key}=${it.value}" }.join("&")
		def requestUrl = "${url}${queryString}"
		
		def responseText = requestUrl.toURL().getText("ISO-8859-1")		
		responseText = StringEscapeUtils.unescapeHtml(responseText)
		
		def response = new XmlSlurper().parseText(responseText)
		if (response.erro.codigo.toString() != "0") {
			throw new Exception("${response.erro.descricao}")
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