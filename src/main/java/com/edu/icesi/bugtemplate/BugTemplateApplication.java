package com.edu.icesi.bugtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BugTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BugTemplateApplication.class, args);
	}

}
