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


        String classNameFull = args?.size() >= 1 ? args.first() : 'Teste'



        Model model = model(classNameFull)

//        def modelConfigurado = [packageName: model.packageName, className: model.simpleName,
//                                simpleName : model.simpleName, packagePath: model.packagePath]


        String pathDestinationFile = "grails-app/jobs/$model.packagePath/${trimTrailingJobFromJobName(model.simpleName)}Job.groovy"
        File fileDestination = file(pathDestinationFile)
        render template: "MyJob.groovy",
                destination: fileDestination,
                model: model
        return true
    }

    /**
     * Se 'Job' já existir no final do nome do job, ele é removido.
     */
    private String trimTrailingJobFromJobName(String jobName) {
        if (jobName.endsWith("Job")) {
            return jobName.substring(0, jobName.length() - 3)
        }
        return jobName
    }

}
