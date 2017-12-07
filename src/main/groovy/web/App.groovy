package pickup;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@EnableAutoConfiguration
@ComponentScan
class App {

    static main(args) {
	    new SpringApplicationBuilder()
	            .sources(App.class)
	            .run();
    }

    @Bean
    MongoMappingContext springDataMongoContext() {
    	new MongoMappingContext()
    }
}