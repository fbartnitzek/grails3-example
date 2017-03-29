package grails3.example

import org.sample.*

class BootStrap {

    def grailsApplication

    def init = { servletContext ->
        def jane = new Runner(firstName: "Jane", lastName: "Doe", gender: 'F', address: "SomeWay 1", city: "SomeTown",
                state: "SomeState", zipCode: "123", email: "jane.doe@some.com").save(flush: true, failOnError: true)

        grailsApplication.parentContext.getResource("classpath:Race.csv").inputStream
                .toCsvReader(['charset': 'UTF-8', 'separatorChar': ';', 'skipLines': 1]).eachLine { tokens ->
            new Race(name: tokens[0].trim(),
                    startDate: new Date(),
                    city: tokens[1].trim(),
                    state: tokens[2].trim(),
                    distance: tokens[3].trim(),
                    cost: tokens[4].trim(),
                    maxRunners: tokens[5].trim()).save(flush: true, failOnError: true)
        }

        new Registration(paid: true,
                race: Race.all.get(0), runner: jane).save(flush: true, failOnError: true)

    }
    def destroy = {
    }
}
