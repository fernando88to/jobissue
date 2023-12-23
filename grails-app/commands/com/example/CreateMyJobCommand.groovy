package com.example

import grails.codegen.model.Model
import grails.dev.commands.GrailsApplicationCommand

class CreateMyJobCommand implements GrailsApplicationCommand {


    String getName() {
        return "create-my-job"
    }

    String getDescription() {
        return "Creates a new Quartz scheduled job"
    }


    // Implementa o método 'handle' para processar o comando

    boolean handle() {


        String className = args?.size() >= 1 ? args.first() : 'Teste'
        String packagePath = ''


        Model model = model('com.examples.Teste')

        def modelConfigurado = [packageName: getDefaultPackage(), className: className,
                                simpleName : model.simpleName, packagePath: packagePath]


        render template: "MyJob.groovy",
                destination: file("grails-app/jobs/$modelConfigurado.packagePath/${trimTrailingJobFromJobName(modelConfigurado.simpleName)}Job.groovy"),
                model: modelConfigurado
        return true
    }

    /**
     * Se 'Job' já existir no final do nome do job, ele é removido.
     */
    private String trimTrailingJobFromJobName(String jobName) {
        String type = "Job"
        String processedName = name
        Integer lastIndexOfJOBInJobName = name.lastIndexOf(type)
        if (lastIndexOfJOBInJobName == (name.length() - type.length())) {
            processedName = name.substring(0, lastIndexOfJOBInJobName)
        }
        return processedName
    }

}
