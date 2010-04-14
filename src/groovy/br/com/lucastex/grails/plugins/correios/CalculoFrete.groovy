package br.com.lucastex.grails.plugins.correios

class CalculoFrete {
	
	def versao //ok
	def tipoPostagem //ok
	def codigoTipoPostagem //ok
	def origem = [:] //ok
	def destino = [:] //ok
	def peso //ok
	def maoPropria //ok
	def tarifaMaoPropria //ok
	def avisoRecebimento //ok
	def tarifaAvisoRecebimento //ok
	def valorDeclarado //ok
	def tarifaValorDeclarado //ok
	
	@Delegate def valorFrete //ok
}
