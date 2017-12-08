package web;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration
@ComponentScan
class App {

    static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args)
    }

    @Bean
    MongoMappingContext springDataMongoContext() {
    	new MongoMappingContext()
    }
}