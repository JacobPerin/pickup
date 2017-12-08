package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/user")
class UserController {

	// @RequestMapping("add")
	// def add() {
	// 	new ModelAndView('views/login', [user: new User()])
	// }



	@RequestMapping("{id}")
	def view(@PathVariable("id") Long id) {
		new ModelAndView("views/user/edit", "user", User.get(id))
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(User user) {
		if(user.id) {
			def u = user.get(user.id)
			u.with {
				firstName = user.firstName
				lastName = user.lastName
				save()
			}
		}
		//Create new user
		else{
			new User(lastName: user.lastName, email: user.email, username: user.username, 
				firstName: user.firstName, password: user.password).save()
		}
		new ModelAndView("redirect:/")
	}
}