package web

import grails.persistence.*
import grails.mongodb.geo.*
import org.bson.types.ObjectId
import com.fasterxml.jackson.annotation.*



@Entity
class Event {

	ObjectId id
	String username
	String lat
	String lng
	String description
	List attending
	String maxUsers
	String title


	static hasMany = [attending: String]

	static constraints = {
		username blank:false
		description blank:false
	}


	static belongsTo = [user:User]

	
}