package com.idealo.shopservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages="com.idealo.*")
@EntityScan("com.idealo.models")
@EnableJpaRepositories("com.idealo.repository")
public class ShopServiceApplication {
	
	public static void main(String[] args) {
	
		SpringApplication.run(ShopServiceApplication.class, args);
		
	}

}

