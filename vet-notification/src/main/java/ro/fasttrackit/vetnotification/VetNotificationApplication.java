package ro.fasttrackit.vetnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VetNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetNotificationApplication.class, args);
	}
}
