package org.sample

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class RunnerController {

    static scaffold = Runner

}
