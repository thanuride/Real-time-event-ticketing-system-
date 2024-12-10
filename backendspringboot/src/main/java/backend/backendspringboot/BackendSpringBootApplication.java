package backend.backendspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "backend.backendspringboot" )
public class BackendSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendSpringBootApplication.class, args);
	}

}
