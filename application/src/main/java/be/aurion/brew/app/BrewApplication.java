package be.aurion.brew.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BrewApplication {
@Autowired
	public BrewApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(BrewApplication.class, args);
	}
}
