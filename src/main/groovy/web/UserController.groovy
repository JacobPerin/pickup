package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.http.HttpStatus
import grails.mongodb.geo.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
@Transactional
@RequestMapping("/user")
class UserController {

	@RequestMapping("register")
	def register(){
		new ModelAndView('views/_registration')
	}

	@RequestMapping(value="getEvent", method=RequestMethod.GET)
	def getEvent( ){

	}

	// Cleaner notation :: Collin refer to GetMapping + annotion insert
	@PostMapping(value="/addEvent")
	def addEvent(@RequestBody Event event) {
		
		println('---')
		println(event.title)
		println('---')
		println(event.maxUsers)
		println('---')
		println(event.description)
		println('---')

		//This is the userId, had to do this for event to get to backend.
		//println("Before user")
		// def locUser = User.get(event.username)
		// if(locUser){
		// 	println("im here")
		// 	def userEvent = new Event(username: locUser.username, lat: event.lat, lng: event.lng, description: event.description, attendingUsers: 1, title: event.event, maxUsers: event.maxUsers)
		// 	locUser.addToEvents(event)
		// 	locUser.save(flush:true)
		// 	return true;
		// }
		// return false;
	}

	@RequestMapping("login")
	def login(User user) {

		def locUser = User.findByUsernameAndPassword(user.username, user.password)
		println("here")
		if(locUser) {
			ModelAndView mod = new ModelAndView("views/_main");
			mod.addObject("events", locUser.events)
			mod.addObject("userId", locUser.id)
			return mod
		}
		else{
			println("here")
			ModelAndView mod = new ModelAndView("views/_login")
			mod.addObject("error", "Invalid Username or password")
			return mod
		}
		
	}

	@RequestMapping(value='/error', method = RequestMethod.GET)
	public ModelAndView error() {
		return ModelAndView()
	}

	@RequestMapping("{id}")
	def view(@PathVariable("id") Long id) {
		new ModelAndView("views/_main", "user", User.get(id))
	}

	// @RequestMapping(method = RequestMethod.GET)
	// public ModelAndView login(User user){
	// 	if(user.get(user.username) && user.get(user.password)){
	// 		new ModelAndView("views/_main")
	// 	}
	// 	new ModelAndView("views/_main")
	// }

	@RequestMapping("save")
	public ModelAndView save(User user) {

		if(User.findByUsername(user.username)){
			ModelAndView mod = new ModelAndView("views/_registration")
			mod.addObject("error", "Username already exists")
			return mod
		}
		//Create new user
		else{
			User locUser = new User(lastName: user.lastName, email: user.email, username: user.username, 
				firstName: user.firstName, password: user.password).save(flush:true)

			ModelAndView mod = new ModelAndView("views/_main")
			mod.addObject("events", "")
			mod.addObject("userId", locUser.id)
			return mod
		}
		
	}
}