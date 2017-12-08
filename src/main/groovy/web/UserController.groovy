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



	// @RequestMapping("{username}")
	// def view(@PathVariable("username") String username) {
	// 	new ModelAndView("views/user/edit", "user", User.get(id))
	// }

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(User user) {
		if(user.username) {

		}
		//Create new user
		else{
			user.save()
		}
		new ModelAndView("redirect:/")
	}
}