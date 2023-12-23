package com.example


import grails.dev.commands.GrailsApplicationCommand

class CreateJobCommand implements GrailsApplicationCommand {


    /*  String getName() {
          return "create-job"
      }*/

    String getDescription() {
        return "Creates a new Quartz scheduled job"
    }
    // Define a descrição e a sintaxe do comando
//    static description = ""
//    static usage = "grails create-job [JOB NAME]"

    // Implementa o método 'handle' para processar o comando

    boolean handle() {
        def args = executionContext.commandLine.rawArguments
        // Obtém o nome do job a partir dos argumentos do comando
        String jobName = args.length > 0 ? args[1] : null

        if (!jobName) {
            println "Usage: grails create-job [JOB NAME]"
            return false
        }

        // Processa o nome do job
        jobName = trimTrailingJobFromJobName(jobName)

        // Cria o modelo para o template
        def model = [jobName: jobName]
        String packageName = 'com.example.jobs'
        String packageNameBarra = packageName.replace(".","/")

        String jobContent = generateJobContent(jobName, packageName)
        // Escreve o conteúdo no arquivo de destino
        new File("grails-app/jobs/${packageNameBarra}/${jobName}Job.groovy").text = jobContent


        /* render(template: "Job2.groovy",
                 destination: file("grails-app/jobs/${jobName}Job.groovy"),
                 model: model)*/

        return true
    }

    /**
     * Se 'Job2' já existir no final do nome do job, ele é removido.
     */
    private String trimTrailingJobFromJobName(String jobName) {
        if (jobName.endsWith("Job")) {
            return jobName.substring(0, jobName.length() - 3)
        }
        return jobName
    }

    private String generateJobContent(String jobName, String packageName) {
        // Aqui, você pode definir o conteúdo do seu job Quartz
        // Exemplo básico:
        return """package ${packageName}

class ${jobName}Job {
    static triggers = {
        simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        // execute job
    }
}

"""
    }
}
