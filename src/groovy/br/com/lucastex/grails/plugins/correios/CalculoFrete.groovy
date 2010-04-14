package br.com.lucastex.grails.plugins.correios

class CalculoFrete {

	static def postagem = [:]
	static {
		postagem.PAC = '41106'
		postagem.SEDEX = '40010'
		postagem.SEDEX_10 = '40215'
		postagem.SEDEX_HOJE = '40290'
		postagem.SEDEX_A_COBRAR = '40045'
	}
	
	def versao
	def tipoPostagem
	def codigoTipoPostagem
	def origem = [:]
	def destino = [:]
	def peso
	def maoPropria
	def tarifaMaoPropria
	def avisoRecebimento
	def tarifaAvisoRecebimento
	def valorDeclarado
	def tarifaValorDeclarado
	
	@Delegate def valorFrete
}
