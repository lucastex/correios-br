class CorreiosBrGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.1.2 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Lucas Frare Teixeira"
    def authorEmail = "lucastex@gmail.com"
    def title = "Correios BR Grails Plugin"
    def description = "Plugin para cálculo de frete usando servico dos Correios - Brasil"

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-br"

    def doWithSpring = {
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def doWithWebDescriptor = { xml ->
    }

    def doWithDynamicMethods = { ctx ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
