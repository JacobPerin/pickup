package Model;

import grails.persistence.*;
import org.bson.types.ObjectId;

@Entity
class User {
	ObjectId id;
	String firstName;
	String lastName;

	static constraints = {
		firstName blank:false;
		lastName blank:false;

	}

}