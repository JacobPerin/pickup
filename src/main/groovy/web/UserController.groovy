package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.http.HttpStatus
import grails.mongodb.geo.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody

@Controller
@Transactional
@RequestMapping("/user")
class UserController {

	@RequestMapping("register")
	def register(){
		return 'views/_registration'
	}

	@RequestMapping(value="addEvent", method=RequestMethod.POST)
	def addEvent(@RequestBody User user) {

		def locUser = User.get(user.userId)
		if(locUser){
			Point point = new Point(user.lat, user.lng)
			def event = new Event(username: locUser.username, location: point, description: user.description, attendingUsers: 1, title: user.event, maxUsers: user.people)
			locUser.addToEvents(event)
			locUser.save(flush:true)
			return true;
		}
		return false;
	}

	@RequestMapping("login")
	def login(User user) {

		def locUser = User.findByUsernameAndPassword(user.username, user.password)
		if(locUser) {
			ModelAndView mod = new ModelAndView("views/_main");
			mod.addObject("events", locUser.events)
			mod.addObject("userId", locUser.id)
			return mod
		}
		else{
			return
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

		}
		else if(user.id) {
			def u = user.get(user.id)
			u.with {
				firstName = user.firstName
				lastName = user.lastName
				save(flush:true)
			}
		}
		//Create new user
		else{
			new User(lastName: user.lastName, email: user.email, username: user.username, 
				firstName: user.firstName, password: user.password).save(flush:true)
		}
		new ModelAndView("views/_main")
	}
}