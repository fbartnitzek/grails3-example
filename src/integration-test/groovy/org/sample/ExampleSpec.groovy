package org.sample


import grails.test.mixin.integration.Integration
import grails.transaction.*
import groovy.sql.Sql
import spock.lang.*

import javax.sql.DataSource

@Integration
@Rollback
class ExampleSpec extends Specification {

    def DataSource dataSource
    def Sql sql

    def setup() {
        sql = new Sql(dataSource)
    }

    def cleanup() {
        sql = null
    }

    void "test race domain"() {
		expect:
			Race.count() == 1
        when:
            def race = new Race(name: "TheRace", startDate: new Date(),
                    city: "someCity", state: 'GA', cost: 10, distance: 10)
                    .save(flush:true, failOnError: true)
        then:
            race != null
            Race.count() == 2
    }

    void "test table query"() {
		expect:
			sql.rows('SELECT * FROM g3_race').size() == 1

        when:
            new Race(name: "TheRace", startDate: new Date(),
                    city: "someCity", state: 'GA', cost: 10, distance: 10)
                    .save(flush:true, failOnError: true)
        then:
            Race.count() == 2
            sql.rows('SELECT * FROM g3_race').size() == 2
    }

    void "test delete"(){
        when:
            def race = new Race(name: "OtherRace", startDate: new Date(),
                    city: "someCity", state: 'GA', cost: 10, distance: 10)
                    .save(flush:true, failOnError: true)
            def runner = new Runner(firstName: "Mr", lastName: "Bean", address: "SomeWay",
                    city: "London", state: "England", zipCode: "1234")
                .save(flush: true, failOnError: true)
        then:
            Race.count == 2
            Runner.count == 2
            race.city.equals("someCity")

        when:
            def registration = new Registration(race: race, runner: runner, paid: true,
                    dateCreated: new Date()).save(flush: true, failOnError: true)
        then:
            Registration.count() == 2

//        when:
//            race.delete(flush: true)    // fails... TODO
//        then:
//            Race.count() == 1

    }
}
