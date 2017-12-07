package pickup;

import User
import com.mongodb.BasicDBObject;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;
import grails.mongodb.geo.*;

@RestController
class ModelController {
	
	@RequestMapping("/")
	List index() {
		User.list().collect { [name: it.name] }
	}

	@PostConstruct
	void populateUsers() {
		User.withTransaction{
			User.collection.remove(new BasicDBObject())
			User.saveAll(
				[
				new User(firstName:"Collin",
							lastName:"McElvain")
				]
				)
		}
	}
}