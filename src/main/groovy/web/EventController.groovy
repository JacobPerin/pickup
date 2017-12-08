package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/Event")
class EventController {

	@RequestMapping("list")
	def list() {
		new ModelAndView('views/event/list', [events: Event.list()])
	}

	@RequestMapping("add")
	def add() {
		new ModelAndView('views/event/edit', [user: new User()])
	}

	@RequestMapping("{username}")
	def view(@PathVariable("username") String username) {
		new ModelAndView("views/event/edit", "user", User.get(id))
	}
}