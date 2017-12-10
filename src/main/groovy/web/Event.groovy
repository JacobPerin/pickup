package web

import grails.persistence.*
import grails.mongodb.geo.*
import org.bson.types.ObjectId

@Entity
class Event {
	ObjectId id
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

	static belongsTo = [user:User]

	
}