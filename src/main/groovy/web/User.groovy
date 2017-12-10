package web

import grails.persistence.*
import org.bson.types.ObjectId

@Entity
class User {
	
	List events;
	ObjectId id;
	String firstName;
	String lastName;
	String email;
	String username;
	String password;
	static hasMany = [events: Event]

	static constraints = {
		firstName blank:false
		lastName blank:false
		email blank:false
		username blank:false
		password blank:false

	}

	static mapping = {
		id index:true, indexAttributes: [unique:true, dropDups: true]
	}


}