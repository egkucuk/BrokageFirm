package com.springboot.brokagefirm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.springboot.brokagefirm.controller","com.springboot.brokagefirm.service"})
@EntityScan("com.springboot.brokagefirm.entity")
@EnableJpaRepositories("com.springboot.brokagefirm.repository")
@SpringBootApplication
public class BrokagefirmApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrokagefirmApplication.class, args);
	}

}
