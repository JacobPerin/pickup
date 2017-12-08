package web;

import org.bson.types.ObjectId;
import grails.persistence.*;

@Entity
class User {
	ObjectId id;
	String firstName;
	String lastName;

	static constraints = {
		firstName blank:false
		lastName blank:false
	}
}