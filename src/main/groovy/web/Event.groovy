package web

import grails.persistence.*
import grails.mongodb.geo.*

class Event {
	String username
	Point location
	String description

	static constraints = {
		username blank:false
		description blank:false
		location nullable:false
	}

	static mapping = {
		location geoIndex:'2dsphere'
	}

	
}