package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView
import org.springframework.http.HttpStatus
import grails.mongodb.geo.*
import org.springframework.transaction.annotation.Transactional

@Controller
@Transactional
@RequestMapping("/user")
class UserController {

	@RequestMapping("register")
	def register(){
		return 'views/_registration'
	}

	@RequestMapping("addEvent")
	def addEvent(User user) {

		def locUser = User.findByUsername("StankyStarfish")
		if(locUser){
			Point point = new Point(70, 80)
			def event = new Event(username: "StankyStarfish", location: point, description: "IWorkBitch")
			locUser.addToEvents(event)
			locUser.save(flush:true)
		}
	}

	@RequestMapping("login")
	def login(User user) {

		def locUser = User.findByUsernameAndPassword(user.username, user.password)
		if(locUser) {
			new ModelAndView("views/_main", "user", locUser)
		}
		else{
			return
		}
		
	}

	@RequestMapping("list")
	def getEvents(User user) {
		println("made it bish")
		def locUser = User.findByUsername(user.username)
		new ModelAndView('views/_main', [users: locUser.events])
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