package com.example

import com.example.jobs.CommitJob


class IndexController {

    def index() {
        CommitJob.triggerNow()
        render 'foi'

    }
}