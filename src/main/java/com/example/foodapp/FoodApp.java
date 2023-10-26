package com.example.foodapp;

import com.example.foodapp.business.model.Business;
import com.example.foodapp.business.model.TimeOpenedDay;
import com.example.foodapp.business.model.TimeOpenedWeek;
import com.example.foodapp.business.repo.BusinessWorkingTimeRepo;
import com.example.foodapp.business.repo.TimeOpenedDayRepo;
import com.example.foodapp.business.service.admin_service.AdminBusinessService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public CommandLineRunner commandLineRunner(
			AdminBusinessService adminBusinessService,
			TimeOpenedDayRepo timeOpenedDayRepo,
			BusinessWorkingTimeRepo timeOpenedWeekRepo
	) {
		return args -> {
//			var restaurant = new Business();
//			restaurant.setName("Restaurant 1");
//			restaurant.setDescription("Description");
//			restaurant.setActive(true);
//
//			var timeOpened = new TimeOpenedWeek();
//
//			var timeOpenedMonday = new TimeOpenedDay();
//			timeOpenedMonday.setTimeOpen("08:00");
//			timeOpenedMonday.setTimeClose("22:00");
//
//			timeOpened.setTimeOpenedDayMonday(timeOpenedMonday);
//
//			var timeOpenedTuesday = new TimeOpenedDay();
//			timeOpenedTuesday.setTimeOpen("08:00");
//			timeOpenedTuesday.setTimeClose("22:00");
//
//			timeOpened.setTimeOpenedDayTuesday(timeOpenedTuesday);
//
//
//			restaurant.setTimeOpened(timeOpened);
//			timeOpenedDayRepo.save(timeOpenedMonday);
//
//			timeOpenedDayRepo.save(timeOpenedTuesday);
//
//			timeOpenedWeekRepo.save(timeOpened);
//
//			adminBusinessService.create(restaurant);
		};
	}

}
