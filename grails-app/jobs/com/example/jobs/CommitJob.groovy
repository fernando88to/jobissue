package com.example.jobs

class CommitJob {
    static triggers = {
//        simple repeatInterval: 5000l, startDelay: 5000l*5 // execute job once in 5 seconds
        simple repeatInterval: 5000l

    }


    def execute() {
        log.info('iniciou')

        log.info('terminou')

    }
}