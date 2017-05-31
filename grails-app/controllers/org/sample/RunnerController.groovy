package org.sample

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RunnerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 15, 100)
        respond Runner.list(params), model:[runnerCount: Runner.count()]
    }

    def show(Runner runner) {
        respond runner
    }

    def create() {
        respond new Runner(params)
    }

    @Transactional
    def save(Runner runner) {
        if (runner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (runner.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond runner.errors, view:'create'
            return
        }

        runner.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'runner.label', default: 'Runner'), runner.id])
                redirect runner
            }
            '*' { respond runner, [status: CREATED] }
        }
    }

    def edit(Runner runner) {
        respond runner
    }

    @Transactional
    def update(Runner runner) {
        if (runner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (runner.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond runner.errors, view:'edit'
            return
        }

        runner.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'runner.label', default: 'Runner'), runner.id])
                redirect runner
            }
            '*'{ respond runner, [status: OK] }
        }
    }

    @Transactional
    def delete(Runner runner) {

        if (runner == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        runner.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'runner.label', default: 'Runner'), runner.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'runner.label', default: 'Runner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
