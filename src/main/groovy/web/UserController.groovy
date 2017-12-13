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
import org.springframework.web.bind.annotation.*

@Controller
@Transactional
@RequestMapping("/user")
class UserController {

	@RequestMapping("register")
	def register(){
		new ModelAndView('views/_registration')
	}

	@PostMapping(value="/addEvent")
	public @ResponseBody String addEvent(@RequestBody Event event ) {

		def locUser = User.get(event.username)
		if(locUser){
			event.username = locUser.username;
			event.addToAttending(locUser.username)
			locUser.addToEvents(event)
			locUser.save(flush:true)
		}
		return event.id;
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
			ModelAndView mod = new ModelAndView("views/_login")
			mod.addObject("logError", "Invalid Username or password")
			return mod
		}
		
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
			mod.addObject("logError", "Username already exists")
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