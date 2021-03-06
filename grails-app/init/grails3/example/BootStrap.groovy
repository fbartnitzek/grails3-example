package grails3.example

import org.sample.*

class BootStrap {

    def grailsApplication

    def springSecurityService

    def init = { servletContext ->

        // spring security
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole
        UserRole.create testUser, userRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 2
        assert UserRole.count() == 2


        // Request Maps:
        for (String url in [
                '/', '/error',
                '/index', 'index.gsp', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**', '/**/favicon.ico', '/fonts/**',
                '/login/**', '/logout/**',
                '/home/**'
        ]) {
            Permission.findByUrl(url) ?: new Permission(url: url, configAttribute: 'permitAll').save(failOnError: true, flush: true)
        }

        for (String url in [
                '/dbconsole/**',                                            // web DB Console for h2
                '/race/**', '/runner/**', '/registration/**',               // data domains
                '/user/**', '/role/**', '/userRole/**', '/permission/**'    // spring security domains
        ]) {
            Permission.findByUrl(url) ?: new Permission(url: url, configAttribute: 'ROLE_ADMIN').save(failOnError: true, flush: true)
        }
        springSecurityService.clearCachedRequestmaps()

        println("... spring bootstrapped")


        // data
        def jane = new Runner(firstName: "Jane", lastName: "Doe", gender: 'F', address: "SomeWay 1", city: "SomeTown",
                state: "SomeState", zipCode: "123", email: "jane.doe@some.com",
                attr1: "attr1", attr2: "attr2", attr3: "attr3", attr4: "attr4", attr5: "attr5")
                .save(flush: true, failOnError: true)

        for (int i = 0; i < 20; ++i) {
            new Runner(firstName: "Jane" + i, lastName: "Doe" + i, gender: 'F', address: "SomeWay " + i, city: "SomeTown" + i,
                    state: "SomeState" + i, zipCode: "123" + i, email: "jane" + i + ".doe@some.com",
                    attr1: "attr1" +i, attr2: "attr2" +i, attr3: "attr3" +i, attr4: "attr4" + i, attr5: "attr5" + i)
                    .save(flush: true, failOnError: true)
        }

        grailsApplication.parentContext.getResource("classpath:Race.csv").inputStream
                .toCsvReader(['charset': 'UTF-8', 'separatorChar': ';', 'skipLines': 1]).eachLine { tokens ->
            new Race(name: tokens[0].trim(),
                    startDate: new Date(),
                    city: tokens[1].trim(),
                    state: tokens[2].trim(),
                    distance: tokens[3].trim(),
                    cost: tokens[4].trim(),
                    maxRunners: tokens[5].trim(),
                    attr1: "attr1", attr2: "attr2", attr3: "attr3", attr4: "attr4", attr5: "attr5").save(flush: true, failOnError: true)
        }

        new Registration(paid: true,
                race: Race.all.get(0), runner: jane).save(flush: true, failOnError: true)

        println("... data bootstrapped")

    }
    def destroy = {
    }
}
