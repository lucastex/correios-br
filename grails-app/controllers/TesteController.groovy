import br.com.lucastex.grails.plugins.correios.CalculoFrete

class TesteController {

	def correiosService

	def index = {

		//cepOrigem
		//cepDestino
		//peso
		//maoPropria

		def calculoFrete = correiosService.freteSedex(cepOrigem:"01331001", cepDestino:"85851150", peso:10, maoPropria: false)
		def calculoFreteMaoPropria = correiosService.freteSedex(cepOrigem:"01331001", cepDestino:"85851150", peso:10, maoPropria: true)
		def calculoFreteAvisoRecebimento = correiosService.freteSedex(cepOrigem:"01331001", cepDestino:"85851150", peso:10, maoPropria: false, avisoRecebimento: true)
		def calculoFreteAvisoRecebimentoMaoPropria = correiosService.freteSedex(cepOrigem:"01331001", cepDestino:"85851150", peso:10, maoPropria: true, avisoRecebimento: true)
		def calculoFreteValorDeclarado = correiosService.freteSedex(cepOrigem:"01331001", cepDestino:"85851150", peso:10, valorDeclarado:1000)
		
		def writer = new StringWriter()   
		def builder = new groovy.xml.MarkupBuilder(writer) 
		builder.html { 
			head { 
				title("Teste plugin correios")
			} 
			body { 
				p("${calculoFrete.properties}") 
				p("${calculoFreteMaoPropria.properties}") 
				p("${calculoFreteAvisoRecebimento.properties}") 
				p("${calculoFreteAvisoRecebimentoMaoPropria.properties}") 
				p("${calculoFreteValorDeclarado.properties}") 
			} 
		} 
		render writer.toString() 
	}
}