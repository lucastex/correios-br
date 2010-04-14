import br.com.lucastex.grails.plugins.correios.CalculoFrete
import static br.com.lucastex.grails.plugins.correios.CalculoFrete.*

class FreteController {

	def correiosService

	def index = {

	}
	
	def calcula = {
	
		def freteParams = [:]
		freteParams.servico = postagem["${params.servico}"]
		freteParams.cepOrigem = params.cepOrigem
		freteParams.cepDestino = params.cepDestino
		freteParams.peso = params.peso
		freteParams.valorDeclarado = params.valorDeclarado
		freteParams.avisoRecebimento = params.avisoRecebimento == "on"
		freteParams.maoPropria = params.maoPropria == "on"
		
		try {
			def calculoFrete = correiosService.calculoFrete(freteParams)
			[calculoFrete: calculoFrete]
		} catch (Exception e) {
			[erro: e.message]
		}
	}
}