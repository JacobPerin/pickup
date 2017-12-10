package web

import grails.persistence.*
import grails.mongodb.geo.*
import org.bson.types.ObjectId

@Entity
class Event {
	ObjectId id
	String username
	String lat
	String lng
	String description
	String attendingUsers
	String maxUsers
	String title

	static constraints = {
		username blank:false
		description blank:false
	}

	static belongsTo = [user:User]

	
}