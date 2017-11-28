package p06.pickup;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
class App {

    static main(args) {
	    new SpringApplicationBuilder()
	            .sources(App.class)
	            .run();
    }
}