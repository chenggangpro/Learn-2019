package pro.chenggang.learn.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class ReactorDemoApplication {

	public static void main(String[] args) {
		Hooks.onOperatorDebug();
		SpringApplication.run(ReactorDemoApplication.class, args);
	}

}

