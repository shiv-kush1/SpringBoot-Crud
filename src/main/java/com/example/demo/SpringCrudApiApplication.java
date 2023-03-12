package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan("com")
@EntityScan("com.entity")
@EnableJpaRepositories("com.repository")
@SpringBootApplication
public class SpringCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudApiApplication.class, args);
	}

}
