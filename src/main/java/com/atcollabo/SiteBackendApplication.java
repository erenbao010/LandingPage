package com.atcollabo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SiteBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(SiteBackendApplication.class, args);

	}
}

