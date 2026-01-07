package com.teamsynk.canteenpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CanteenposApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanteenposApplication.class, args);
	}

}
