package web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/event")
class EventController {

	@RequestMapping("list")
	def list() {
		new ModelAndView('views/event/list', [events: Event.list()])
	}


	@PostMapping(value="/getEvent")
	public @ResponseBody Event getEvent(@RequestBody String id ) {
		println(id)
		Event event = Event.get(id);
		println(event.username)
		if(event){
			return event
		}
	}

	// @PostMapping(value="/editEvent")
	// public @ResponseBody String editEvent(@RequestBody Event event ) {

	// 	Event event = Event.get(id);
	// 	if(event){
	// 		event.username = locUser.username;
	// 		event.addToAttendingUsers(locUser.username)
	// 		locUser.addToEvents(event)
	// 		locUser.save(flush:true)
	// 	}
	// 	return event.id;
	// }

	@RequestMapping("add")
	def add() {
		new ModelAndView('views/event/edit', [user: new User()])
	}

	@RequestMapping("{username}")
	def view(@PathVariable("username") String username) {
		new ModelAndView("views/event/edit", "user", User.get(id))
	}
}