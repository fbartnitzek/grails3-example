package grails3.example

import org.sample.*

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        // spring security
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def testUser = new User(username: 'me', password: 'password').save()

        UserRole.create testUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 2
        assert UserRole.count() == 1


        // Request Maps:

//        new Permission(url: '/', configAttribute: 'permitAll').save(failOnError: true)
//        new Permission(url: '/**', configAttribute: 'permitAll').save(failOnError: true)
        for (String url in [
                '/', '/error', '/index',
                '/home/**', 'index_login.gsp', '/index.gsp', '/shutdown',
                '/assets/**', '/**/js/**', '/**/css/**', '/**/images/**', '/**/favicon.ico',
                '/login', '/login/**', '/logout', '/logout/**'
        ]) {
            new Permission(url: url, configAttribute: 'permitAll').save(failOnError: true)
        }

        for (String url in [
                '/dbconsole/**',                                            // web DB Console for h2
                '/status**',                                                // special h2 page
                '/homeSec/**',                                              // sec home page
                '/race/**', '/runner/**', '/registration/**',               // data domains
                '/user/**', '/role/**', '/userRole/**', '/permission/**'    // spring security domains
        ]) {
            Permission.findByUrl(url) ?: new Permission(url: url, configAttribute: 'ROLE_ADMIN').save(failOnError: true)
        }

        // also forward error...
//        new Permission(url: '/*', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save();
//        new Permission(url: '/logout/**', configAttribute: 'IS_AUTHENTICATED_REMEMBERED,IS_AUTHENTICATED_FULLY').save();
//        new Permission(url: '/homeSec/**', configAttribute: 'IS_AUTHENTICATED_REMEMBERED,IS_AUTHENTICATED_FULLY').save();
//        new Permission(url: '/login/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save()
//        new Permission(url: '/index/**', configAttribute: 'IS_AUTHENTICATED_ANONYMOUSLY').save();

        println("... spring bootstrapped")


        // data
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

        println("... data bootstrapped")

    }
    def destroy = {
    }
}
