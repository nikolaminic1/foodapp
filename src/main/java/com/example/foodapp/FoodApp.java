package com.example.foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
//@EnableJpaRepositories("com.example.foodapp")
//@EntityScan("com.example.foodapp.auth.user.UserProfiles")
public class FoodApp {

	public static void main(String[] args) {
		SpringApplication.run(FoodApp.class, args);
	}

}
