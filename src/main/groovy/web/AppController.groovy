package web;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/*
References :: 
https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#mvc
	-- Chapter 22 :: @Controller, @RequestMapping, @ResponseBody
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications
	-- Chapter 11 :: Creating Spring application
*/

@Controller
class AppController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}