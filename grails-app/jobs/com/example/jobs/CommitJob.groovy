package com.example.jobs

class CommitJob {
    static triggers = {
        simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
       log.info('deu certo')
    }
}