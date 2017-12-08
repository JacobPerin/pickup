@Grab("org.grails:gorm-mongodb-spring-boot:1.0.0.RC1")
package web;

import grails.persistence.*;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import javax.annotation.PostConstruct;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView


/*
References :: 
https://docs.spring.io/spring/docs/4.3.12.RELEASE/spring-framework-reference/htmlsingle/#mvc
	-- Chapter 22 :: @Controller, @RequestMapping, @ResponseBody
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-developing-web-applications
	-- Chapter 11 :: Creating Spring application
*/

@RestController
class AppController {

    @RequestMapping("/")
// <<<<<<< HEAD
//     List index() {
//     	User.list().collect { [firstName: it.firstName]}
// =======
    def home() {
        new ModelAndView("views/home")
    }


    @PostConstruct
    void populateUsers() {
    	User.withTransaction{
    		User.collection.remove(new BasicDBObject())
    		User.saveAll([
    			new User(firstName:"Collin", lastName:"McElvain")
    			])
    	}
    }

}