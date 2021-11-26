package com.greedy.TravelWithGuid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TravelWithGuidApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelWithGuidApplication.class, args);
	}

}
