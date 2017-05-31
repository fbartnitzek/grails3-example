package org.sample

class Race {
	String name
    Date startDate
    String city
    String state
    BigDecimal distance
    BigDecimal cost
    Integer maxRunners = 100_000
    String attr1
    String attr2
    String attr3
    String attr4
    String attr5

    static constraints = {
        name(blank: false, maxSize: 50, unique: ['startDate', 'city'])
        startDate[:]
        city[:]
        state(inList:['GA', 'NC', 'SC', 'VA'])
        distance(min: 0.0)
        cost(min: 0.0, max: 100.0)
        maxRunners(min: 0, max: 100_000)
        attr1[:]
        attr2[:]
        attr3[:]
        attr4[:]
        attr5[:]
    }
	
	static hasMany = [registrations:Registration]

    static mapping = {
        sort "startDate"
    }

    BigDecimal inMiles(){
        return distance * 0.6214
    }
	
	String toString(){
		return "${name}, ${startDate == null ? '' : startDate.format('dd.MM.yyyy')}"
	}

    /*def beforeDelete(){
        withNewSession { session ->
            def registrations = Registration.findAllByRace(this)
            registrations.each {
                it.delete()
            }
        }
    }*/

}
