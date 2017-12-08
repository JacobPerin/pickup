package web

import grails.persistence.*
import org.bson.types.ObjectId

@Entity
class User {
	ObjectId id;
	String firstName;
	String lastName;
	String email;
	String username;
	String password;


	static constraints = {
		firstName blank:false
		lastName blank:false
		email blank:false
		username blank:false
		password blank:false

	}


}