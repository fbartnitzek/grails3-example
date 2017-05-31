package org.sample

class Runner {
	String firstName
	String lastName
	String address
	String city
	String state
	String zipCode
	String attr1
	String attr2
	String attr3
	String attr4
	String attr5
	
	static hasMany = [registrations:Registration]

    static constraints = {
		firstName(blank:false)
		lastName(blank:false)
		address[:]
		city[:]
		state[:]
		zipCode[:]
		attr1[:]
		attr2[:]
		attr3[:]
		attr4[:]
		attr5[:]
    }
	
	String toString(){
		"${lastName}, ${firstName}"
	}
}
