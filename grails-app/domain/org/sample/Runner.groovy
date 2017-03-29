package org.sample

class Runner {
	String firstName
	String lastName
	String address
	String city
	String state
	String zipCode
	
	static hasMany = [registrations:Registration]

    static constraints = {
		firstName(blank:false)
		lastName(blank:false)
		address[:]
		city[:]
		state[:]

		zipCode[:]
    }
	
	String toString(){
		"${lastName}, ${firstName}"
	}
}
